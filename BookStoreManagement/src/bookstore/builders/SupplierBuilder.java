package bookstore.builders;

import bookstore.model.ISupplier;
import bookstore.model.Supplier;

public class SupplierBuilder implements ISupplierBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public ISupplier build(String name) {
		return new Supplier(name);
	}

}
