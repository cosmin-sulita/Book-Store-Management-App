package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements IInvoiceRepository {

	private static final long serialVersionUID = 1L;

	private List<IInvoice> invoiceList;

	public InvoiceRepository() {
		invoiceList = new ArrayList<IInvoice>();
	}

	@Override
	public void addInvoiceToList(IInvoice invoice) {
		invoiceList.add(invoice);
	}

	@Override
	public void removeInvoiceFromList(IInvoice invoice) {
		invoiceList.remove(invoice);
	}

}
