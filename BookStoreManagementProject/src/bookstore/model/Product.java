package bookstore.model;

import java.text.DecimalFormat;

public class Product implements IProduct {

	private static final long serialVersionUID = 1L;

	IBook book;
	private int quantity;
	private double supplierPrice;
	private double personalPrice;
	private double totalPrice;
	private int storeQuantity;

	public Product(IBook book, int quantity, double supplierPrice, double personalPrice) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.supplierPrice = supplierPrice;
		this.personalPrice = personalPrice;
		this.totalPrice = calculateTotalPrice();
		this.storeQuantity = quantity;
	}

	private double calculateTotalPrice() {
		return Double.parseDouble(new DecimalFormat("##.##").format(supplierPrice * quantity));
	}

	public IBook getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getSupplierPrice() {
		return supplierPrice;
	}

	@Override
	public double getPersonalPrice() {
		return personalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String getBookTitle() {
		return book.getTitle();
	}

	@Override
	public String getBookPublisher() {
		return book.getPublisher();
	}

	@Override
	public String getQuantityAsString() {
		return String.valueOf(quantity);
	}

	@Override
	public String getSupplierPriceAsString() {
		return String.valueOf(supplierPrice);
	}

	@Override
	public String getTotalPriceAsString() {
		return String.valueOf(totalPrice);
	}

	@Override
	public void decreaseStoreQuantity() {
		storeQuantity -= 1;
	}

	@Override
	public int getStoreQuantity() {
		return storeQuantity;
	}

	@Override
	public String getPersonalPriceAsString() {
		return String.valueOf(personalPrice);
	}

}
