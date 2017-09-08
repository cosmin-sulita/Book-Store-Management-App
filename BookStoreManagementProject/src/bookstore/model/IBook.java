package bookstore.model;

import java.io.Serializable;

public interface IBook extends Serializable {

	String getTitle();

	String getPublisher();

	int getIsbn();

	String getAuthor();

	String getPriceAsString();

	String getStockAsString();

	String getISBN();

	void addStock(int quantity);

	void removeStock(int quantity);

	void setPrice(double personalPrice);

	int getStock();

	double getPrice();

}
