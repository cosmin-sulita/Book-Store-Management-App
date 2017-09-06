package bookstore.model;

import java.io.Serializable;
import java.util.List;

public interface IBookStore extends Serializable {

	void addBook(IBook book);

	void removeBook(IBook book);

	void sortBookListAlphabetically();

	IBook getBookByTitle(String title);

	IBook getBookByTitleAndPublisher(String title, String publisher);

	IBook getBookByISBN(String isbn);

	boolean doesISBNAlreadyExist(int isbn);

	String[][] toStringVector();

	boolean hasBooks();

	List<IBook> getBookList();

	void removeBooksFromStock(IInvoice selectedInvoice, IBookStore bookStore);

	void addBooksToStock(IInvoice newInvoice);

	String[][] toStringVector(IBook foundBook);

	void setBooksPrice(IInvoice newInvoice);

	boolean hasCopiesOf(IBook book);

	void sell(IBook book);

	double getTotalIncome();

}
