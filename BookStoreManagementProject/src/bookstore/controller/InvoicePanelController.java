package bookstore.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.IProductBuilder;
import bookstore.builders.ProductBuilder;
import bookstore.model.IBook;
import bookstore.model.IBookStore;
import bookstore.model.IInvoice;
import bookstore.model.IProduct;
import bookstore.model.ISupplierRepository;
import bookstore.model.MyTableModel;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BookStoreInterface;

public class InvoicePanelController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> usedInvoiceNumbers = new ArrayList<Integer>();

	public void setInvoiceNumber(AddInvoicePanel aip) {
		int invoiceNumber;
		Random random = new Random();

		invoiceNumber = random.nextInt(1000000);
		while (usedInvoiceNumbers.contains(invoiceNumber)) {
			invoiceNumber = random.nextInt(1000000);
		}
		usedInvoiceNumbers.add(invoiceNumber);

		aip.setInvoiceNumber(String.valueOf(invoiceNumber));
	}

	public void setCurrentDate(AddInvoicePanel aip) {
		String dateString;
		Date currentDate;
		DateFormat dateFormat;

		currentDate = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		dateString = dateFormat.format(currentDate);
		aip.setTextFieldDate(dateString);
		aip.initTerm(currentDate);
	}

	public void populateComboBoxes(AddInvoicePanel aip, IBookStore bookStore, ISupplierRepository supplierRepository) {
		aip.populateBookComboBox(bookStore.getBookList());
		aip.populateSupplierComboBox(supplierRepository.getList());
	}

	public void setPublisherText(AddInvoicePanel aip, IBookStore bookStore) {
		IBook selectedBook = null;
		if (bookStore.hasBooks()) {
			selectedBook = aip.getSelectedBook();
			aip.setPublisherName(selectedBook.getPublisher());
		}
	}

	public void setInvoiceValue(AddInvoicePanel aip, double totalAmmount) {
		String formattedValue;
		formattedValue = String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(totalAmmount)));
		aip.setTextFieldInvoiceValue(formattedValue);
	}

	public void setRebatePricing(AddInvoicePanel aip) {
		if (aip.rebateIsSelected()) {
			aip.disablePersonalPrice();
		} else {
			aip.enablePersonalPrice();
		}
	}

	public void setSelectedPayment(AddInvoicePanel aip) {
		if (aip.PayBySaleIsSelected()) {
			aip.enableDatePicker(false);
		} else {
			aip.enableDatePicker(true);
		}
	}

	public void addBookToInvoice(BookStoreInterface screen, AddInvoicePanel aip, IInvoice invoice) {
		boolean bookAlreadyExists = false;
		boolean allFieldsAreFilled = false;

		int quantity;
		double supplierPrice;
		double personalPrice;

		IBook book;
		IProduct product;
		IProductBuilder productBuilder;

		if (aip.rebateIsSelected()) {
			if (!aip.getTextFieldSupplierPrice().contentEquals("") && !aip.getTextFieldQuantity().contentEquals("")) {
				allFieldsAreFilled = true;
			}
		} else {
			if (!aip.getTextFieldSupplierPrice().contentEquals("") && !aip.getTextFieldQuantity().contentEquals("")
					&& !aip.getTextFieldPersonalPrice().contentEquals("")) {
				allFieldsAreFilled = true;
			}
		}

		if (allFieldsAreFilled) {
			try {

				book = (IBook) aip.getComboBoxBook().getSelectedItem();
				bookAlreadyExists = invoice.doesBookAlreadyExist(book);

				if (bookAlreadyExists) {
					JOptionPane.showMessageDialog(screen,
							book.getTitle() + " already added!\nPlease select another book!");
				} else {

					quantity = Math.abs(Integer.parseInt(aip.getTextFieldQuantity()));
					supplierPrice = Math.abs(Double.parseDouble(aip.getTextFieldSupplierPrice()));

					if (aip.getCheckBoxRebate().isSelected()) {
						personalPrice = supplierPrice;
					} else {
						personalPrice = Math.abs(Double.parseDouble(aip.getTextFieldPersonalPrice()));
					}

					if (personalPrice < supplierPrice) {
						JOptionPane.showMessageDialog(screen, "Personal price must be higher than supplier price");
					} else {

						productBuilder = new ProductBuilder();
						product = productBuilder.build(book, quantity, supplierPrice, personalPrice);

						invoice.addProductToList(product);

						aip.setTextFieldQuantity("1");
						aip.setTextFieldSupplierPrice("");
						aip.setTextFieldPersonalPrice("");

						updateInvoiceTable(aip, invoice);
						setInvoiceValue(aip, invoice.getValue());
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(screen, "Price must be a number!");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(screen, "No book selected");
			}
		} else {
			JOptionPane.showMessageDialog(screen, "Please fill out price fields");
		}
	}

	public void removeBookFromInvoice(BookStoreInterface screen, AddInvoicePanel aip, IInvoice invoice) {
		int row;
		int titleColumn = 0;
		int publisherColumn = 1;
		String title;
		String publisher;
		IProduct product;

		try {
			row = ((JTable) aip.getBookTable()).getSelectedRow();
			title = ((JTable) aip.getBookTable()).getValueAt(row, titleColumn).toString();
			publisher = ((JTable) aip.getBookTable()).getValueAt(row, publisherColumn).toString();

			product = invoice.getProductByTitleAndPublisher(title, publisher);
			invoice.removeProductFromList(product);
			updateInvoiceTable(aip, invoice);
			setInvoiceValue(aip, invoice.getValue());
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(screen, "No book selected");
		}
	}

	private void updateInvoiceTable(AddInvoicePanel aip, IInvoice invoice) {
		String dataProduct[][];

		while (((MyTableModel) aip.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) aip.getBookTable().getModel()).removeRow(0);
		}
		dataProduct = invoice.toStringVectorProductList();
		for (int i = 0; i < dataProduct.length; i++) {
			((MyTableModel) aip.getBookTable().getModel()).addRow(dataProduct[i]);
		}
	}

	public void clearInvoicePanel(AddInvoicePanel aip, IInvoice invoice) {
		invoice.removeAllProducts();
		invoice.resetValue();
		setInvoiceNumber(aip);
		updateInvoiceTable(aip, invoice);
		aip.setTextFieldInvoiceValue(invoice.getValueAsString());
	}

}
