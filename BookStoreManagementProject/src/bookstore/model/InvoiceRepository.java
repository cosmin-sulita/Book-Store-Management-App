package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements IInvoiceRepository, IRepository {

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

	@Override
	public String[][] toStringVector() {
		String[][] total = new String[invoiceList.size()][6];
		for (int i = 0; i < invoiceList.size(); i++) {
			total[i][0] = invoiceList.get(i).getInvoiceNumberAsString();
			total[i][1] = invoiceList.get(i).getSupplierName();
			total[i][2] = invoiceList.get(i).getValueAsString();
			total[i][3] = invoiceList.get(i).getRebateAsString();
			total[i][4] = invoiceList.get(i).getPaymentAsString();
		}
		return total;
	}

}
