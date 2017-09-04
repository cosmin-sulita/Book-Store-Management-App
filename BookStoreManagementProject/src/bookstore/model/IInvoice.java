package bookstore.model;

import java.io.Serializable;

public interface IInvoice extends Serializable {

	String[][] toStringVector();

	void addProductToList(IProduct product);

	void removeProductFromList(IProduct product);

	boolean doesBookAlreadyExist(IBook book);

}
