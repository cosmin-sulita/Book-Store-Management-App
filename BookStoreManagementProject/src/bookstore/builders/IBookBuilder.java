package bookstore.builders;

import java.io.Serializable;

import bookstore.model.IBook;

public interface IBookBuilder extends Serializable {

	IBook build(String title, String author, String publisher, int isbn, int stock, double price);

	IBook buildEmptyBook();

}
