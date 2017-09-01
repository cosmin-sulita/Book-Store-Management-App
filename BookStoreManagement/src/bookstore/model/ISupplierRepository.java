package bookstore.model;

import java.io.Serializable;
import java.util.List;

public interface ISupplierRepository extends Serializable {

	void addSupplierToList(ISupplier supplier);

	void removeSupplierFromList(ISupplier supplier);

	boolean doesNameAlreadyExist(String name);

	List<ISupplier> getList();

}
