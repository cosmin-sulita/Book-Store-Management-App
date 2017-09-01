package bookstore.builders;

import java.io.Serializable;

import bookstore.model.ISupplier;

public interface ISupplierBuilder extends Serializable {

	ISupplier build(String name);

}
