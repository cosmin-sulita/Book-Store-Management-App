package bookstore.model;

import java.io.Serializable;

public interface IInvoiceRepository extends Serializable {

	void addInvoiceToList(IInvoice invoice);

	void removeInvoiceFromList(IInvoice invoice);

}
