package bookstore.model;

import java.io.Serializable;
import java.util.List;

public interface IInvoiceRepository extends Serializable {

	void addInvoiceToList(IInvoice invoice);

	void removeInvoiceFromList(IInvoice invoice);

	String[][] toStringVector();

	IInvoice getInvoiceByNumber(String invoiceNumber);

	List<IInvoice> getInvoiceList();

	double getTotalInvoiceValue();

	void calculateTotalInvoiceValue();

	IProduct getProduct(IBook book);

	IInvoice getInvoiceThatContains(IBook book);

}
