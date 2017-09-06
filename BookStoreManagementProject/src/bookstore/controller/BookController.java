package bookstore.controller;

import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.BookBuilder;
import bookstore.builders.IBookBuilder;
import bookstore.model.EmptyBook;
import bookstore.model.IBook;
import bookstore.model.IBookStore;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IProduct;
import bookstore.model.MyTableModel;
import bookstore.view.AddBookFrame;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BrowseStorePanel;

public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void addBookToBookStore(AddBookFrame abf, AddInvoicePanel aip, BrowseStorePanel bsp, IBookStore bookStore) {
		boolean ISBNAlreadyExist = false;
		boolean AllFieldsAreFilled = false;

		String title;
		String author;
		String publisher;

		int newBookIndex = 0;
		int isbn = 0;
		int stock = 0;
		double price = 0.0;

		IBook book;
		IBookBuilder bookBuilder;

		if (!abf.getTextFieldIsbn().contentEquals("") && !abf.getTextFieldTitle().contentEquals("")
				&& !abf.getTextFieldAuthor().contentEquals("") && !abf.getTextFieldPublisher().contentEquals("")) {
			AllFieldsAreFilled = true;
		}

		if (AllFieldsAreFilled) {
			try {
				isbn = Integer.parseInt(abf.getTextFieldIsbn());

				ISBNAlreadyExist = bookStore.doesISBNAlreadyExist(isbn);
				if (ISBNAlreadyExist) {
					JOptionPane.showMessageDialog(abf, isbn + " already exist!\nPlease use another isbn!");
				} else {

					title = abf.getTextFieldTitle();
					author = abf.getTextFieldAuthor();
					publisher = abf.getTextFieldPublisher();

					bookBuilder = new BookBuilder();
					book = bookBuilder.build(title, author, publisher, isbn, stock, price);

					bookStore.addBook(book);
					bookStore.sortBookListAlphabetically();

					newBookIndex = bookStore.getBookList().indexOf(book);
					aip.insertBookInComboBox(book, newBookIndex);

					abf.setTextFieldIsbn("");
					abf.setTextFieldTitle("");
					abf.setTextFieldAuthor("");
					abf.setTextFieldPublisher("");
					abf.appendLog("\n> " + book.getTitle() + " has been added to the library!");

					updateBookTable(bsp, bookStore);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(abf, "Isbn is not a number!");
			}
		} else {
			JOptionPane.showMessageDialog(abf, "Please fill out all non-optional fields");
		}
	}

	public void removeBookFromStore(AddInvoicePanel aip, BrowseStorePanel bsp, IBookStore bookStore) {
		IBook book;

		book = getSelectedBook(bsp, bookStore);
		bookStore.removeBook(book);
		updateBookTable(bsp, bookStore);
		aip.removeBookFromComboBox(book);

	}

	public void updateBookTable(BrowseStorePanel bsp, IBookStore bookStore) {
		String dataBook[][];

		while (((MyTableModel) bsp.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) bsp.getBookTable().getModel()).removeRow(0);
		}
		dataBook = bookStore.toStringVector();
		for (int i = 0; i < dataBook.length; i++) {
			((MyTableModel) bsp.getBookTable().getModel()).addRow(dataBook[i]);
		}
	}

	public void searchBook(BrowseStorePanel bsp, IBookStore bookStore) {
		String searchedBook;
		IBook foundBook;
		String dataBook[][];

		searchedBook = bsp.getSearchedBook();

		if (searchedBook.equals("")) {
			updateBookTable(bsp, bookStore);
		} else {
			try {
				foundBook = bookStore.getBookByTitle(searchedBook);

				if (foundBook.getClass().isInstance(new EmptyBook())) {
					foundBook = bookStore.getBookByISBN(searchedBook);
				}

				while (((MyTableModel) bsp.getBookTable().getModel()).getRowCount() > 0) {
					((MyTableModel) bsp.getBookTable().getModel()).removeRow(0);
				}
				dataBook = bookStore.toStringVector(foundBook);
				for (int i = 0; i < dataBook.length; i++) {
					((MyTableModel) bsp.getBookTable().getModel()).addRow(dataBook[i]);
				}

			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(bsp, "Book not found");
				updateBookTable(bsp, bookStore);
			}
		}
	}

	private IBook getSelectedBook(BrowseStorePanel bsp, IBookStore bookStore) {
		int row;
		int isbnColumn;
		String isbn;
		IBook book;
		try {
			row = ((JTable) bsp.getBookTable()).getSelectedRow();
			isbnColumn = 5;
			isbn = ((JTable) bsp.getBookTable()).getValueAt(row, isbnColumn).toString();
			book = bookStore.getBookByISBN(isbn);
			return book;
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(bsp, "No book selected");
		}
		return null;
	}

	public void sellBook(BrowseStorePanel bsp, IBookStore bookStore, IInvoiceRepository invoiceRepository) {
		IBook book;
		IProduct product;
		IInvoice myInvoice;

		book = getSelectedBook(bsp, bookStore);
		myInvoice = invoiceRepository.getInvoiceThatContains(book);
		product = invoiceRepository.getProduct(book);
		book.setPrice(product.getPersonalPrice());

		if (bookStore.hasCopiesOf(book)) {
			bookStore.sell(book);
			product.decreaseStoreQuantity();
			if (product.getStoreQuantity() == 0) {
				myInvoice.removeProductFromList(product);
			}
			updateBookTable(bsp, bookStore);
			updateTotalIncome(bsp, bookStore);
		} else {
			JOptionPane.showMessageDialog(bsp, "No more copies");
		}
	}

	private void updateTotalIncome(BrowseStorePanel bsp, IBookStore bookStore) {
		String totalIncome;
		totalIncome = String.valueOf(bookStore.getTotalIncome());
		bsp.setTextTotalIncome(totalIncome);
	}

}
