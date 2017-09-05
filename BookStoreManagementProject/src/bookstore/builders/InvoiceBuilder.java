package bookstore.builders;

import java.util.List;

import bookstore.model.IInvoice;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;
import bookstore.model.Invoice;

public class InvoiceBuilder implements IInvoiceBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IInvoice build(int invoiceNumber, ISupplier supplier, List<IProduct> productList, boolean rebate,
			double invoiceValue) {
		return new Invoice(invoiceNumber, supplier, productList, rebate, invoiceValue);
	}

	@Override
	public IInvoice buildEmptyInvoice() {
		return new Invoice();
	}

}
