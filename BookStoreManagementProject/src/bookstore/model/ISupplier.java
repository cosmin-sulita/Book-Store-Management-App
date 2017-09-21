package bookstore.model;

import java.io.Serializable;

public interface ISupplier extends Serializable {

	String getName();

	boolean hasSameNameAs(String name);

}
