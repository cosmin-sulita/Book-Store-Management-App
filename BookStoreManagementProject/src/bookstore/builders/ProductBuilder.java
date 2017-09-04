package bookstore.builders;

import bookstore.model.IBook;
import bookstore.model.IProduct;
import bookstore.model.Product;

public class ProductBuilder implements IProductBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IProduct build(IBook book, int quantity, double supplierPrice, double personalPrice) {
		return new Product(book, quantity, supplierPrice, personalPrice);
	}

}
