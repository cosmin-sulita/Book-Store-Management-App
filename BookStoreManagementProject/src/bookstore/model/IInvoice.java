package bookstore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IInvoice extends Serializable {

	String[][] toStringVector();

	void addProductToList(IProduct product);

	void removeProductFromList(IProduct product);

	boolean doesBookAlreadyExist(IBook book);

	IProduct getProductByTitleAndPublisher(String title, String publisher);

	double getValue();

	String getSupplierName();

	List<IProduct> getProductList();

	String getInvoiceNumberAsString();

	String getValueAsString();

	String getRebateAsString();

	String getPaymentAsString();

	int getInvoiceNumber();

	void removeAllProducts();

	void resetValue();

	boolean isEmpty();

	void decreaseInvoiceValue(double price);

	Date getInvoiceDate();

	String getPaymentAsStringForTable();

	void removeProductFromInvoice(IProduct product);

	boolean paymentIsDue();

	boolean hasPayOnTermPayment();

	void setPaid(boolean b);

	boolean getPaid();

	String[][] toStringVectorProductList();

}
