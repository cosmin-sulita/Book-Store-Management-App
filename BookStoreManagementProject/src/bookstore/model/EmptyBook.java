package bookstore.model;

public class EmptyBook implements IBook {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private String publisher;
	private int isbn;
	private int stock;
	private double price;

	public EmptyBook() {
		super();
		this.title = "";
		this.author = "";
		this.publisher = "";
		this.isbn = 0;
		this.stock = 0;
		this.price = 0.0;
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
	public String getPriceAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStockAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getISBN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStock(int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeStock(int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrice(double personalPrice) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStock() {
		return stock;
	}

	@Override
	public double getPrice() {
		return price;
	}

}
