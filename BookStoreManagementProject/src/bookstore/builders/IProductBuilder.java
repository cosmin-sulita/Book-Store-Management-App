package bookstore.builders;

import java.io.Serializable;

import bookstore.model.IBook;
import bookstore.model.IProduct;

public interface IProductBuilder extends Serializable {

	IProduct build(IBook book, int quantity, double supplierPrice, double personalPrice);

}
