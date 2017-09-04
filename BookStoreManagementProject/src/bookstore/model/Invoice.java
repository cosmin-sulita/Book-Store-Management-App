package bookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice implements IInvoice {

	private static final long serialVersionUID = 1L;

	private int invoiceNumber;
	private Date date;
	private Supplier supplier;
	private List<IProduct> productList;
	// private IPayment paymentType;
	private boolean rebate;

	public Invoice() {
		productList = new ArrayList<IProduct>();
	}

	public Invoice(int invoiceNumber, Date date, Supplier supplier, List<IProduct> productList, boolean rebate) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.date = date;
		this.supplier = supplier;
		this.productList = productList;
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
	}

	@Override
	public void removeProductFromList(IProduct product) {
		productList.remove(product);
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

}
