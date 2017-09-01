package bookstore.model;

public class Supplier implements ISupplier {

	private static final long serialVersionUID = 1L;

	private String name;

	public Supplier(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean isTheSameAs(ISupplier supplier) {
		return name.equals(supplier.getName());
	}

}
