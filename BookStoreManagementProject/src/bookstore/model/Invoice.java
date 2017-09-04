package bookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice implements IInvoice {

	private static final long serialVersionUID = 1L;

	private int invoiceNumber;
	private Date date;
	private ISupplier supplier;
	private List<IProduct> productList;
	private boolean rebate;
	private double invoiceValue;

	public Invoice() {
		this.invoiceNumber = 0;
		this.date = null;
		this.supplier = null;
		this.productList = new ArrayList<IProduct>();
		this.rebate = false;
		this.invoiceValue = 0.0;
	}

	public Invoice(int invoiceNumber, Date date, ISupplier supplier, List<IProduct> productList, boolean rebate,
			double invoiceValue) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.date = date;
		this.supplier = supplier;
		this.productList = productList;
		this.rebate = rebate;
		this.invoiceValue = invoiceValue;
	}

	public Invoice(int invoiceNumber, ISupplier supplier, List<IProduct> productList, double invoiceValue,
			boolean rebate) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.supplier = supplier;
		this.productList = productList;
		this.invoiceValue = invoiceValue;
		this.rebate = rebate;

	}

	@Override
	public String[][] toStringVector() {
		String[][] total = new String[productList.size()][6];
		for (int i = 0; i < productList.size(); i++) {
			total[i][0] = productList.get(i).getBookTitle();
			total[i][1] = productList.get(i).getBookPublisher();
			total[i][2] = productList.get(i).getQuantityAsString();
			total[i][3] = productList.get(i).getSupplierPriceAsString();
			total[i][4] = productList.get(i).getTotalPriceAsString();
		}
		return total;
	}

	@Override
	public void addProductToList(IProduct product) {
		productList.add(product);
		addProductValueToInvoiceValue(product.getTotalPrice());
	}

	private void addProductValueToInvoiceValue(double value) {
		invoiceValue += value;
	}

	@Override
	public void removeProductFromList(IProduct product) {
		productList.remove(product);
		removeProductValueFromInvoceValue(product.getTotalPrice());
	}

	private void removeProductValueFromInvoceValue(double value) {
		invoiceValue -= value;
	}

	@Override
	public boolean doesBookAlreadyExist(IBook book) {
		for (IProduct product : productList) {
			if (product.getBookTitle().toLowerCase().equals(book.getTitle().toLowerCase())
					&& product.getBookPublisher().toLowerCase().equals(book.getPublisher().toLowerCase()))
				return true;
		}
		return false;
	}

	@Override
	public IProduct getProductByTitleAndPublisher(String title, String publisher) {
		for (IProduct product : productList) {
			if (product.getBookTitle().toLowerCase().equals(title.toLowerCase())
					&& product.getBookPublisher().toLowerCase().equals(publisher.toLowerCase())) {
				return product;
			}
		}
		return null;
	}

	@Override
	public double getValue() {
		return invoiceValue;
	}

	@Override
	public String getSupplierName() {
		return supplier.getName();
	}

	@Override
	public List<IProduct> getProductList() {
		return productList;
	}

	@Override
	public String getInvoiceNumberAsString() {
		return String.valueOf(invoiceNumber);
	}

	@Override
	public String getValueAsString() {
		return String.valueOf(invoiceValue);
	}

	@Override
	public String getRebateAsString() {
		return String.valueOf(rebate);
	}

	@Override
	public String getPaymentAsString() {
		return "payment";
	}

}
