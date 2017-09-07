package bookstore.model;

import java.io.Serializable;

public interface IProduct extends Serializable {

	String getBookTitle();

	String getBookPublisher();

	String getQuantityAsString();

	String getSupplierPriceAsString();

	String getTotalPriceAsString();

	double getTotalPrice();

	int getQuantity();

	double getPersonalPrice();

	void decreaseStoreQuantity();

	int getStoreQuantity();

	String getPersonalPriceAsString();

}
