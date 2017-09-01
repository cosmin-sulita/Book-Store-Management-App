package bookstore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookStore implements IBookStore, IRepository {

	private static final long serialVersionUID = 1L;

	private List<IBook> bookList;

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
		return null;
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
			if (bookList.get(i).getISBN().contentEquals(isbn)) {
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
			total[i][3] = bookList.get(i).getPrice();
			total[i][4] = bookList.get(i).getStock();
			total[i][5] = bookList.get(i).getISBN();
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

}
