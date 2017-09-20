package bookstore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookStore implements IBookStore, IRepository {

	private static final long serialVersionUID = 1L;

	private List<IBook> bookList;
	private double totalIncome;

	public BookStore() {
		bookList = new ArrayList<IBook>();
	}

	@Override
	public void addBook(IBook book) {
		bookList.add(book);
	}

	@Override
	public void removeBook(IBook book) {
		bookList.remove(book);
	}

	@Override
	public void sortBookListAlphabetically() {
		Collections.sort(bookList, new Comparator<IBook>() {
			@Override
			public int compare(IBook firstBook, IBook secondBook) {
				return firstBook.getTitle().compareTo(secondBook.getTitle());
			}
		});
	}

	@Override
	public IBook getBookByTitle(String title) {
		for (IBook book : bookList) {
			if (book.getTitle().toLowerCase().contentEquals(title.toLowerCase())) {
				return book;
			}
		}
		return new EmptyBook();
	}

	@Override
	public IBook getBookByTitleAndPublisher(String title, String publisher) {
		for (IBook book : bookList) {
			if (book.getTitle().toLowerCase().contentEquals(title.toLowerCase())
					&& book.getPublisher().toLowerCase().contentEquals(publisher.toLowerCase())) {
				return book;
			}
		}
		return null;
	}

	@Override
	public IBook getBookByISBN(String isbn) {
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getIsbnAsString().contentEquals(isbn)) {
				return bookList.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean doesISBNAlreadyExist(int isbn) {
		for (IBook book : bookList) {
			if (book.getIsbn() == isbn) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String[][] toStringVector() {
		String[][] total = new String[bookList.size()][6];
		for (int i = 0; i < bookList.size(); i++) {
			total[i][0] = bookList.get(i).getTitle();
			total[i][1] = bookList.get(i).getAuthor();
			total[i][2] = bookList.get(i).getPublisher();
			total[i][3] = bookList.get(i).getPriceAsString();
			total[i][4] = bookList.get(i).getStockAsString();
			total[i][5] = bookList.get(i).getIsbnAsString();
		}
		return total;
	}

	@Override
	public boolean hasBooks() {
		if (bookList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<IBook> getBookList() {
		return bookList;
	}

	@Override
	public void removeBooksFromStock(IInvoice selectedInvoice, IBookStore bookStore) {
		for (IProduct product : selectedInvoice.getProductList()) {
			for (IBook book : bookStore.getBookList()) {
				if (book.getTitle() == product.getBookTitle() && book.getPublisher() == product.getBookPublisher()) {
					book.removeStock(product.getQuantity());
				}
			}
		}
	}

	@Override
	public void addBooksToStock(IInvoice invoice) {
		for (IProduct product : invoice.getProductList()) {
			for (IBook book : bookList) {
				if (book.getTitle() == product.getBookTitle() && book.getPublisher() == product.getBookPublisher()) {
					book.addStock(product.getQuantity());
				}
			}
		}
	}

	@Override
	public String[][] toStringVector(IBook foundBook) {
		String[][] total = new String[1][6];

		total[0][0] = foundBook.getTitle();
		total[0][1] = foundBook.getAuthor();
		total[0][2] = foundBook.getPublisher();
		total[0][3] = foundBook.getPriceAsString();
		total[0][4] = foundBook.getStockAsString();
		total[0][5] = foundBook.getIsbnAsString();

		return total;
	}

	@Override
	public void setBooksPrice(IInvoiceRepository invoiceRepository) {
		int i;
		for (i = invoiceRepository.getInvoiceList().size() - 1; i >= 0; i--) {
			for (IProduct product : invoiceRepository.getInvoiceList().get(i).getProductList()) {
				for (IBook book : bookList) {
					if (book.getTitle() == product.getBookTitle()
							&& book.getPublisher() == product.getBookPublisher()) {
						book.setPrice(product.getPersonalPrice());
					}
				}
			}
		}
	}

	@Override
	public boolean hasCopiesOf(IBook book) {
		if (book.getStock() != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void sell(IBook book) {
		book.removeStock(1);
		totalIncome += book.getPrice();
	}

	@Override
	public void sell(IBook book, double supplierPrice) {
		book.removeStock(1);
		totalIncome += book.getPrice() - supplierPrice;
	}

	@Override
	public double getTotalIncome() {
		return Math.floor(totalIncome * 100) / 100;
	}

	@Override
	public int getIndexOf(IBook book) {
		return bookList.indexOf(book);
	}

	@Override
	public String[][] toStringVectorForAvailableBooks() {
		int size = getAvailableBooksNumber();
		String[][] total = new String[size][6];
		int j = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getStock() > 0) {
				total[j][0] = bookList.get(i).getTitle();
				total[j][1] = bookList.get(i).getAuthor();
				total[j][2] = bookList.get(i).getPublisher();
				total[j][3] = bookList.get(i).getPriceAsString();
				total[j][4] = bookList.get(i).getStockAsString();
				total[j][5] = bookList.get(i).getIsbnAsString();
				j++;
			}
		}
		return total;
	}

	private int getAvailableBooksNumber() {
		int number = 0;
		for (IBook book : bookList) {
			if (book.getStock() > 0) {
				number++;
			}
		}
		return number;
	}

	@Override
	public void decreaseTotalIncomeBy(double value) {
		totalIncome -= value;
	}
}
