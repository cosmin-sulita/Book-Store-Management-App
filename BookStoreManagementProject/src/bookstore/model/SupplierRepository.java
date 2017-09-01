package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository implements ISupplierRepository, IRepository {

	private static final long serialVersionUID = 1L;

	private List<ISupplier> supplierList;

	public SupplierRepository() {
		supplierList = new ArrayList<ISupplier>();
	}

	@Override
	public void addSupplierToList(ISupplier supplier) {
		supplierList.add(supplier);
	}

	@Override
	public void removeSupplierFromList(ISupplier supplier) {
		supplierList.remove(supplier);
	}

	@Override
	public boolean doesNameAlreadyExist(String name) {
		for (ISupplier supplier : supplierList) {
			if (supplier.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ISupplier> getList() {
		return supplierList;
	}

}
