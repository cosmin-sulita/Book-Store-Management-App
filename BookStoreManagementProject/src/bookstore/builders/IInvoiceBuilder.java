package bookstore.builders;

import java.io.Serializable;
import java.util.List;

import bookstore.model.IInvoice;
import bookstore.model.IPayment;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;

public interface IInvoiceBuilder extends Serializable {

	IInvoice buildEmptyInvoice();

	IInvoice build(int invoiceNumber, ISupplier supplier, List<IProduct> productList, String rebate,
			double invoiceValue, IPayment paymentType);

}
