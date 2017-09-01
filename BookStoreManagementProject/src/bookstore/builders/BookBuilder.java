package bookstore.builders;

import bookstore.model.Book;
import bookstore.model.EmptyBook;
import bookstore.model.IBook;

public class BookBuilder implements IBookBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IBook build(String title, String author, String publisher, int isbn, int stock, double price) {
		return new Book(title, author, publisher, isbn, stock, price);
	}

	@Override
	public IBook buildEmptyBook() {
		return new EmptyBook();
	}

}
