package bookstore.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

	public void setCurrentDateDate(AddInvoicePanel aip) {
		String date;
		LocalDate localDate = LocalDate.now();
		date = DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate);
		aip.setTextFieldDate(date);
	}

	public void setPublisherText(AddInvoicePanel aip, IBookStore bookStore) {
		IBook selectedBook = null;
		if (bookStore.hasBooks()) {
			selectedBook = aip.getSelectedBook();
			aip.setPublisherName(selectedBook.getPublisher());
		}
	}

	public void populateComboBoxes(AddInvoicePanel aip, IBookStore bookStore, ISupplierRepository supplierRepository) {
		aip.populateBookComboBox(bookStore.getBookList());
		aip.populateSupplierComboBox(supplierRepository.getList());
	}

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

	public void setInvoiceValue(AddInvoicePanel aip, double totalAmmount) {
		String formattedValue;
		formattedValue = String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(totalAmmount)));
		aip.setTextFieldInvoiceValue(formattedValue);
	}

	public void setRebatePricing(AddInvoicePanel aip) {
		if (aip.getCheckBoxRebate().isSelected()) {
			aip.disablePersonalPrice();
		} else {
			aip.enablePersonalPrice();
		}
	}

	public void addBookToInvoice(BookStoreInterface screen, AddInvoicePanel aip, IInvoice invoice) {
		boolean BookAlreadyExists = false;
		boolean AllFieldsAreFilled = false;

		int quantity;
		double supplierPrice;
		double personalPrice;

		IBook book;
		IProduct product;
		IProductBuilder productBuilder;

		if (aip.rebateIsSelected()) {
			if (!aip.getTextFieldSupplierPrice().contentEquals("") && !aip.getTextFieldQuantity().contentEquals("")) {
				AllFieldsAreFilled = true;
			}
		} else {
			if (!aip.getTextFieldSupplierPrice().contentEquals("") && !aip.getTextFieldQuantity().contentEquals("")
					&& !aip.getTextFieldPersonalPrice().contentEquals("")) {
				AllFieldsAreFilled = true;
			}
		}

		if (AllFieldsAreFilled) {
			try {

				book = (IBook) aip.getComboBoxBook().getSelectedItem();
				BookAlreadyExists = invoice.doesBookAlreadyExist(book);

				if (BookAlreadyExists) {
					JOptionPane.showMessageDialog(screen,
							book.getTitle() + " already added!\nPlease select another book!");
				} else {

					quantity = Integer.parseInt(aip.getTextFieldQuantity());
					supplierPrice = Double.parseDouble(aip.getTextFieldSupplierPrice());

					if (aip.getCheckBoxRebate().isSelected()) {
						personalPrice = supplierPrice;
					} else {
						personalPrice = Double.parseDouble(aip.getTextFieldPersonalPrice());
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
		dataProduct = invoice.toStringVector();
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
