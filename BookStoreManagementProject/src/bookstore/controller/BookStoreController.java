package bookstore.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.InvoiceBuilder;
import bookstore.model.BookStore;
import bookstore.model.FinalRepository;
import bookstore.model.IBookStore;
import bookstore.model.IFinalRepository;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IRepository;
import bookstore.model.ISupplierRepository;
import bookstore.model.InvoiceRepository;
import bookstore.model.SupplierRepository;
import bookstore.view.AddBookFrame;
import bookstore.view.AddInvoicePanel;
import bookstore.view.AddSupplierFrame;
import bookstore.view.BookStoreInterface;
import bookstore.view.BrowseStorePanel;
import bookstore.view.LoadScreen;
import bookstore.view.MyInvoicesPanel;

public class BookStoreController implements ChangeListener, ActionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private BookStoreInterface screen;
	private BrowseStorePanel bsp;
	private LoadScreen ls;
	private AddInvoicePanel aip;
	private AddBookFrame abf;
	private AddSupplierFrame asf;
	private MyInvoicesPanel mip;

	private JFileChooser chooser;
	private FileFilter filter;

	private int resultCode;

	private File saveFile;
	private File libFile;

	private FileInputStream fis;
	private ObjectInputStream in;

	private String fileName;

	private boolean exit;

	private IBookStore bookStore;
	private IInvoice invoice;
	private IInvoiceRepository invoiceRepository;
	private ISupplierRepository supplierRepository;
	private IFinalRepository finalRepository;
	private IInvoiceBuilder invoiceBuilder;

	private BookController bookController;
	private SupplierController supplierController;
	private InvoicePanelController invoicePanelController;
	private MyInvoicesController myInvoicesController;

	public BookStoreController() {
		initEventAttributes();

		initViews();

		initObjects();
	}

	private void initEventAttributes() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Library Files", "ser");
		chooser.addChoosableFileFilter(filter);

		exit = false;

		saveFile = null;
		libFile = null;
	}

	private void initViews() {
		screen = new BookStoreInterface("Book Store");
		bsp = screen.getBrowseStorePanel();
		aip = screen.getAddInvoicePanel();
		mip = screen.getMyInvoicesPanel();

		screen.getTabbedPane().addChangeListener(this);
		aip.addActionListener(this);
		bsp.addActionListener(this);
		mip.addActionListener(this);

		abf = new AddBookFrame("Add New Book");
		abf.addActionListener(this);

		asf = new AddSupplierFrame("Add New Supplier");
		asf.addActionListener(this);

		ls = new LoadScreen("Welcome");
		ls.addActionListener(this);
		ls.setVisible(true);
	}

	private void initObjects() {
		invoiceBuilder = new InvoiceBuilder();
		bookStore = new BookStore();
		supplierRepository = new SupplierRepository();
		invoiceRepository = new InvoiceRepository();
		invoice = invoiceBuilder.buildEmptyInvoice();

		bookController = new BookController();
		supplierController = new SupplierController();
		invoicePanelController = new InvoicePanelController();
		myInvoicesController = new MyInvoicesController();

		finalRepository = new FinalRepository();
		finalRepository.addRepository((IRepository) bookStore);
		finalRepository.addRepository((IRepository) supplierRepository);
		finalRepository.addRepository((IRepository) invoiceRepository);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == ls.getButtonLoad()) {
			loadLibrary();
		} else if (event.getSource() == ls.getButtonNew()) {
			ls.dispose();
			invoicePanelController.setInvoiceNumber(aip);
			invoicePanelController.setCurrentDate(aip);
			screen.setVisible(true);
		} else if (event.getSource() == ls.getButtonExit()) {
			System.exit(0);
		} else if (event.getSource() == aip.getComboBoxBook()) {
			invoicePanelController.setPublisherText(aip, bookStore);
		} else if (event.getSource() == aip.getCheckBoxRebate()) {
			invoicePanelController.setRebatePricing(aip);
		} else if (event.getSource() == aip.getRadioButtonPayBySale()
				|| event.getSource() == aip.getRadioButtonPayOnTerm()) {
			invoicePanelController.setSelectedPayment(aip);
		} else if (event.getSource() == aip.getButtonAddNewSupplier()) {
			asf.setVisible(true);
		} else if (event.getSource() == aip.getButtonAddNewBook()) {
			abf.setVisible(true);
		} else if (event.getSource() == aip.getButtonAddBookToInvoice()) {
			invoicePanelController.addBookToInvoice(screen, aip, invoice);
		} else if (event.getSource() == aip.getButtonRemoveBookFromInvoice()) {
			invoicePanelController.removeBookFromInvoice(screen, aip, invoice);
		} else if (event.getSource() == aip.getButtonSaveInvoice()) {
			myInvoicesController.saveInvoice(aip, mip, invoice, invoiceRepository, bookStore);
			if ((myInvoicesController.getInvoiceError() == false) || (aip.PayBySaleIsSelected())) {
				invoicePanelController.clearInvoicePanel(aip, invoice);
				bookController.updateBookTable(bsp, bookStore);
			}
		} else if (event.getSource() == bsp.getButtonDeleteBook()) {
			bookController.removeBookFromStore(aip, bsp, bookStore);
		} else if (event.getSource() == bsp.getButtonSearchBook()) {
			bookController.searchBook(bsp, bookStore);
		} else if (event.getSource() == bsp.getButtonSellBook()) {
			bookController.sellBook(bsp, bookStore, invoiceRepository);
			myInvoicesController.updateInvoicesTable(mip, invoiceRepository);
		} else if (event.getSource() == bsp.getButtonShowAvailableBooks()) {
			bookController.showAvailableBooks(bsp, bookStore);
		} else if (event.getSource() == bsp.getButtonSave()) {
			save();
		} else if (event.getSource() == bsp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == asf.getButtonAddSupplier()) {
			supplierController.addNewSupplier(asf, aip, supplierRepository);
		} else if (event.getSource() == abf.getButtonAddBook()) {
			bookController.addBookToBookStore(abf, aip, bsp, bookStore);
		} else if (event.getSource() == abf.getButtonSave()) {
			save();
		} else if (event.getSource() == abf.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == mip.getButtonOpenInvoice()) {
			myInvoicesController.openInvoice(screen, mip, invoiceRepository);
		} else if (event.getSource() == mip.getButtonDeleteInvoice()) {
			myInvoicesController.deleteInvoice(mip, invoice, invoiceRepository, bookStore);
			bookController.updateBookTable(bsp, bookStore);
		} else if (event.getSource() == mip.getButtonSave()) {
			save();
		} else if (event.getSource() == mip.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == mip.getButtonReturnBooks()) {
			myInvoicesController.returnBooks(mip, invoiceRepository, bookStore);
			bookController.updateBookTable(bsp, bookStore);
		}
	}

	private void loadLibrary() {
		File myDirectory = new File("E:\\Academy\\bookStoreManagementApp\\BookStoreManagementProject");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(myDirectory);
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			libFile = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(libFile);
				in = new ObjectInputStream(fis);

				finalRepository = (FinalRepository) in.readObject();

				bookStore = (BookStore) finalRepository.getRepository(0);
				supplierRepository = (SupplierRepository) finalRepository.getRepository(1);
				invoiceRepository = (InvoiceRepository) finalRepository.getRepository(2);

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ls.dispose();

			bookStore.sortBookListAlphabetically();
			supplierController.sortSupplierListAlphabetically(supplierRepository.getList());

			myInvoicesController.checkInvoicesToPay(mip, invoiceRepository, bookStore);
			bookController.updateBookTable(bsp, bookStore);
			invoicePanelController.setInvoiceNumber(aip);
			invoicePanelController.setCurrentDate(aip);
			invoicePanelController.populateComboBoxes(aip, bookStore, supplierRepository);
			myInvoicesController.updateInvoicesTable(mip, invoiceRepository);

			screen.setVisible(true);
		}
	}

	private void save() {
		fileName = JOptionPane.showInputDialog(screen, "Enter file name to save as...", "Save",
				JOptionPane.INFORMATION_MESSAGE);
		if (fileName != null) {
			if (!fileName.trim().contentEquals("")) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;
				try {
					saveFile = new File(fileName.trim() + ".ser");
					if (!saveFile.exists()) {
						fos = new FileOutputStream(saveFile);
						out = new ObjectOutputStream(fos);
						out.writeObject(finalRepository);
						fos.close();
						out.close();
						exit = true;
					} else {
						int resultCode = JOptionPane.showConfirmDialog(screen,
								"File name already exist.\nOverwrite it?", "Warning", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);
						if (resultCode == JOptionPane.YES_OPTION) {
							fos = new FileOutputStream(saveFile);
							out = new ObjectOutputStream(fos);
							out.writeObject(finalRepository);
							fos.close();
							out.close();
							exit = true;
						} else {
							abf.appendLog("\n> Save cancelled.");
							exit = false;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				abf.appendLog("\n> Save cancelled.");
				exit = false;
			}
		} else {
			abf.appendLog("\n> Save cancelled.");
			exit = false;
		}
	}

	private void saveAndQuit() {
		save();
		if (exit)
			System.exit(0);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if (screen.getTabbedPane().getSelectedIndex() == 1)
			screen.setSize(510, 510);
		else
			screen.setSize(510, 510);
	}

}
