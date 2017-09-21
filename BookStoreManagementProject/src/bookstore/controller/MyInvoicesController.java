package bookstore.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.IPaymentFactory;
import bookstore.builders.InvoiceBuilder;
import bookstore.builders.PaymentFactory;
import bookstore.model.IBookStore;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IPayment;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;
import bookstore.model.MyTableModel;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BookStoreInterface;
import bookstore.view.MyInvoicesPanel;

public class MyInvoicesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int REBATE_PERCENT = 15;
	private boolean invoiceError;
	private int selectedInvoiceIndex;

	public void saveInvoice(AddInvoicePanel aip, MyInvoicesPanel mip, IInvoice invoice,
			IInvoiceRepository invoiceRepository, IBookStore bookStore) {

		int invoiceNumber;
		List<IProduct> productList = new ArrayList<IProduct>();
		ISupplier supplier;
		String rebate;
		double invoiceValue;
		IPayment paymentType;
		Date invoiceDate;

		IInvoice newInvoice;
		IInvoiceBuilder invoiceBuilder = new InvoiceBuilder();
		IPaymentFactory paymentFactory = new PaymentFactory();

		try {
			invoiceNumber = Integer.parseInt(aip.getTextFieldInvoiceNumber());
			supplier = aip.getSelectedSupplier();
			invoiceDate = new Date();

			for (IProduct product : invoice.getProductList()) {
				productList.add(product);
			}

			invoiceValue = invoice.getValue();

			if (aip.rebateIsSelected()) {
				invoiceValue = invoiceValue - invoiceValue * REBATE_PERCENT / 100;
				rebate = "Yes";
			} else {
				rebate = "No";
			}

			if (aip.getPaymentType() == "Pay on term") {
				Date term = aip.getPaymentTerm();

				if (term.before(invoiceDate)) {
					JOptionPane.showMessageDialog(aip, "Payment term must be after current date");
					aip.initTerm(invoiceDate);
					throw new NullPointerException();
				} else {
					paymentType = paymentFactory.buildPayOnTermPayment(term);
					invoiceError = false;
				}
			} else {
				paymentType = paymentFactory.buildPayBySalePayment();
			}

			newInvoice = invoiceBuilder.build(invoiceNumber, invoiceDate, supplier, productList, rebate, invoiceValue,
					paymentType);

			if (newInvoice.isEmpty()) {
				JOptionPane.showMessageDialog(aip, "Product list is empty");
			} else {
				invoiceRepository.addInvoiceToList(newInvoice);
			}

			updateInvoicesTable(mip, invoiceRepository);
			setTotalInvoicesValue(mip, invoiceRepository);
			bookStore.addBooksToStock(newInvoice);
			bookStore.setBooksPrice(invoiceRepository);
		} catch (NullPointerException e) {
			invoiceError = true;
		}
	}

	public void updateInvoicesTable(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		String dataInvoice[][];

		while (((MyTableModel) mip.getInvoicesTable().getModel()).getRowCount() > 0) {
			((MyTableModel) mip.getInvoicesTable().getModel()).removeRow(0);
		}
		dataInvoice = invoiceRepository.toStringVector();
		for (int i = 0; i < dataInvoice.length; i++) {
			((MyTableModel) mip.getInvoicesTable().getModel()).addRow(dataInvoice[i]);
		}
		setTotalInvoicesValue(mip, invoiceRepository);
	}

	public void updateBooksTable(MyInvoicesPanel mip, IInvoice invoice) {
		String dataProduct[][];

		while (((MyTableModel) mip.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) mip.getBookTable().getModel()).removeRow(0);
		}
		dataProduct = invoice.toStringVector();
		for (int i = 0; i < dataProduct.length; i++) {
			((MyTableModel) mip.getBookTable().getModel()).addRow(dataProduct[i]);
		}
	}

	private void setTotalInvoicesValue(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		double totalInvoiceValue;
		String formattedValue;
		invoiceRepository.calculateTotalInvoiceValue();
		totalInvoiceValue = invoiceRepository.getTotalInvoiceValue();
		formattedValue = String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(totalInvoiceValue)));
		mip.setTotalInvoiceValue(formattedValue);
	}

	public void openInvoice(BookStoreInterface screen, MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		int row;
		int invoiceNumberColumn = 0;
		String invoiceNumber;

		IInvoice invoice;

		try {
			row = ((JTable) mip.getInvoicesTable()).getSelectedRow();
			invoiceNumber = ((JTable) mip.getInvoicesTable()).getValueAt(row, invoiceNumberColumn).toString();
			invoice = invoiceRepository.getInvoiceByNumber(invoiceNumber);
			selectedInvoiceIndex = invoiceRepository.getInvoiceList().indexOf(invoice);
			updateBooksTable(mip, invoice);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(screen, "No invoice selected");
		}
	}

	public void deleteInvoice(MyInvoicesPanel mip, IInvoice invoice, IInvoiceRepository invoiceRepository,
			IBookStore bookStore) {
		int row;
		int invoiceNumberColumn = 0;
		String invoiceNumber;
		IInvoice selectedInvoice;

		try {
			row = ((JTable) mip.getInvoicesTable()).getSelectedRow();
			invoiceNumber = ((JTable) mip.getInvoicesTable()).getValueAt(row, invoiceNumberColumn).toString();

			selectedInvoice = invoiceRepository.getInvoiceByNumber(invoiceNumber);
			bookStore.removeBooksFromStock(selectedInvoice, bookStore);

			invoiceRepository.removeInvoiceFromList(selectedInvoice);
			updateInvoicesTable(mip, invoiceRepository);
			setTotalInvoicesValue(mip, invoiceRepository);
			selectedInvoice.removeAllProducts();
			updateBooksTable(mip, selectedInvoice);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(mip, "No invoice selected");
		}
	}

	public void checkInvoicesToPay(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository, IBookStore bookStore) {
		List<IInvoice> invoiceList = invoiceRepository.getInvoiceList();
		for (IInvoice invoice : invoiceList) {
			if (invoice.hasPayOnTermPayment()) {
				if (invoice.paymentIsDue()) {
					bookStore.decreaseTotalIncomeBy(invoice.getValue());
					invoice.setPaidStatus(true);
					invoice.resetValue();
					updateInvoicesTable(mip, invoiceRepository);
				}
			}
		}
	}

	public boolean getInvoiceError() {
		return invoiceError;
	}

	public void returnBooks(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository, IBookStore bookStore) {
		IInvoice invoice;
		IProduct product;
		int row;
		int titleColumn = 0;
		int publisherColumn = 1;
		String bookTitle;
		String publisherName;

		try {
			invoice = invoiceRepository.getInvoiceList().get(selectedInvoiceIndex);

			if (invoice.getPaymentAsString() == "Pay by sale") {
				row = ((JTable) mip.getBookTable()).getSelectedRow();
				bookTitle = ((JTable) mip.getBookTable()).getValueAt(row, titleColumn).toString();
				publisherName = ((JTable) mip.getBookTable()).getValueAt(row, publisherColumn).toString();

				product = invoice.getProductByTitleAndPublisher(bookTitle, publisherName);
				invoice.removeProductFromInvoice(product);
				updateBooksTable(mip, invoice);
				bookStore.getBookByTitleAndPublisher(bookTitle, publisherName).removeStock(product.getStoreQuantity());
				invoice.decreaseInvoiceValue(product.getPersonalPrice() * product.getStoreQuantity());
				if (invoice.getValue() == 0) {
					invoice.setPaidStatus(true);
				}
				updateInvoicesTable(mip, invoiceRepository);
				checkInvoicesToPay(mip, invoiceRepository, bookStore);
				bookStore.setBooksPrice(invoiceRepository);
			} else {
				JOptionPane.showMessageDialog(mip, "Invoice must be 'Pay by sale' to return books");
			}

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(mip, "No invoice selected");
		}

		System.out.println(selectedInvoiceIndex);

	}

}
