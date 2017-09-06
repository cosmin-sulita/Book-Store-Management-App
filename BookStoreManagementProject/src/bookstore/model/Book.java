package bookstore.model;

public class Book implements IBook {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private String publisher;
	private int isbn;
	private int stock;
	private double price;

	public Book(String title, String author, String publisher, int isbn, int stock, double price) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.stock = stock;
		this.price = price;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getPublisher() {
		return publisher;
	}

	@Override
	public int getIsbn() {
		return isbn;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getPrice() {
		return String.valueOf(price);
	}

	@Override
	public String getStock() {
		return String.valueOf(stock);
	}

	@Override
	public String getISBN() {
		return String.valueOf(isbn);
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public void addStock(int quantity) {
		stock += quantity;
	}

	@Override
	public void removeStock(int quantity) {
		stock -= quantity;
	}

	@Override
	public void setPrice(double personalPrice) {
		price = personalPrice;
	}

	@Override
	public int getStockAsInt() {
		return stock;
	}

}
