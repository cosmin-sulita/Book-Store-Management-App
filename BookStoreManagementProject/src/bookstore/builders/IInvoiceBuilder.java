package bookstore.builders;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import bookstore.model.IInvoice;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;

public interface IInvoiceBuilder extends Serializable {

	IInvoice buildEmptyInvoice();

	IInvoice build(int invoiceNumber, Date date, ISupplier supplier, List<IProduct> productList, boolean rebate,
			double invoiceValue);

}
