package bookstore.controller;

import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.BookBuilder;
import bookstore.builders.IBookBuilder;
import bookstore.model.IBook;
import bookstore.model.IBookStore;
import bookstore.model.MyTableModel;
import bookstore.view.AddBookFrame;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BookStoreInterface;
import bookstore.view.BrowseStorePanel;

public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void addBookToBookStore(BookStoreInterface screen, AddBookFrame abf, AddInvoicePanel aip,
			BrowseStorePanel bsp, IBookStore bookStore) {
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
					JOptionPane.showMessageDialog(screen, isbn + " already exist!\nPlease use another isbn!");
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
				JOptionPane.showMessageDialog(screen, "Isbn is not a number!");
			}
		} else {
			JOptionPane.showMessageDialog(screen, "Please fill out all non-optional fields");
		}
	}

	public void removeBookFromStore(BookStoreInterface screen, AddBookFrame abf, AddInvoicePanel aip,
			BrowseStorePanel bsp, IBookStore bookStore) {
		int row;
		int column;
		String isbn;
		IBook book;

		try {
			row = ((JTable) bsp.getBookTable()).getSelectedRow();
			column = 5;
			isbn = ((JTable) bsp.getBookTable()).getValueAt(row, column).toString();
			book = bookStore.getBookByISBN(isbn);
			bookStore.removeBook(book);
			updateBookTable(bsp, bookStore);
			aip.removeBookFromComboBox(book);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(screen, "No book selected");
		}

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

}
