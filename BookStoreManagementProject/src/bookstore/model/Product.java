package bookstore.model;

public class Product implements IProduct {

	private static final long serialVersionUID = 1L;

	IBook book;
	private int quantity;
	private double supplierPrice;
	private double personalPrice;
	private double totalPrice;

	public Product(IBook book, int quantity, double supplierPrice, double personalPrice) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.supplierPrice = supplierPrice;
		this.personalPrice = personalPrice;
		this.totalPrice = calculateTotalPrice();
	}

	private double calculateTotalPrice() {
		return supplierPrice * quantity;
	}

	public IBook getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSupplierPrice() {
		return supplierPrice;
	}

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

}
