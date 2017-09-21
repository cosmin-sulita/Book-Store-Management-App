package bookstore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Invoice implements IInvoice {

	private static final long serialVersionUID = 1L;

	private int invoiceNumber;
	private ISupplier supplier;
	private Date invoiceDate;
	private List<IProduct> productList;
	private String rebate;
	private double invoiceValue;
	private IPayment paymentType;
	private boolean paid;

	public Invoice() {
		super();
		this.invoiceNumber = 0;
		this.supplier = null;
		this.invoiceDate = null;
		this.productList = new ArrayList<IProduct>();
		this.rebate = null;
		this.invoiceValue = 0;
		this.paymentType = null;
		this.paid = false;
	}

	public Invoice(int invoiceNumber, Date invoiceDate, ISupplier supplier, List<IProduct> productList, String rebate,
			double invoiceValue, IPayment paymentType) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.supplier = supplier;
		this.invoiceDate = invoiceDate;
		this.productList = productList;
		this.invoiceValue = invoiceValue;
		this.rebate = rebate;
		this.paymentType = paymentType;
		this.paid = false;
	}

	@Override
	public String[][] toStringVector() {
		String[][] total = new String[productList.size()][6];
		for (int i = 0; i < productList.size(); i++) {
			total[i][0] = productList.get(i).getBookTitle();
			total[i][1] = productList.get(i).getBookPublisher();
			total[i][2] = productList.get(i).getQuantityAsString();
			total[i][3] = productList.get(i).getSupplierPriceAsString();
			total[i][4] = productList.get(i).getPersonalPriceAsString();
			total[i][5] = productList.get(i).getTotalPriceAsString();
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

	@Override
	public void removeProductFromInvoice(IProduct product) {
		productList.remove(product);
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
		return Math.floor(invoiceValue * 100) / 100;
	}

	@Override
	public Date getInvoiceDate() {
		return invoiceDate;
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
		return String.valueOf(getValue());
	}

	@Override
	public String getRebateAsString() {
		return rebate;
	}

	@Override
	public String getPaymentAsString() {
		return paymentType.toString();
	}

	@Override
	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	@Override
	public void removeAllProducts() {
		Collection<IProduct> collection = productList;
		productList.removeAll(collection);
	}

	@Override
	public void resetValue() {
		invoiceValue = 0;
	}

	@Override
	public boolean isEmpty() {
		return productList.isEmpty();
	}

	public IPayment getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(IPayment paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public void decreaseInvoiceValue(double price) {
		if (invoiceValue < price) {
			invoiceValue = 0;
		} else {
			invoiceValue -= price;
		}
	}

	@Override
	public String getPaymentAsStringForTable() {
		return paymentType.toStringForTable();
	}

	@Override
	public boolean paymentIsDue() {
		if (paymentType.dateIsDue()) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean hasPayOnTermPayment() {
		if (paymentType.toString() == "Pay on term") {
			return true;
		} else
			return false;
	}

	@Override
	public void setPaidStatus(boolean status) {
		paid = status;
	}

	@Override
	public boolean getPaidStatus() {
		return paid;
	}

	@Override
	public String[][] toStringVectorProductList() {
		String[][] total = new String[productList.size()][5];
		for (int i = 0; i < productList.size(); i++) {
			total[i][0] = productList.get(i).getBookTitle();
			total[i][1] = productList.get(i).getBookPublisher();
			total[i][2] = productList.get(i).getQuantityAsString();
			total[i][3] = productList.get(i).getSupplierPriceAsString();
			total[i][4] = productList.get(i).getTotalPriceAsString();
		}
		return total;
	}

}
