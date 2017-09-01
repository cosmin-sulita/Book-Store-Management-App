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

}
