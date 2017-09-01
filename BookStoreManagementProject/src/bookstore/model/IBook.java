package bookstore.model;

import java.io.Serializable;

public interface IBook extends Serializable {

	String getTitle();

	String getPublisher();

	int getIsbn();

	String getAuthor();

	String getPrice();

	String getStock();

	String getISBN();

}
