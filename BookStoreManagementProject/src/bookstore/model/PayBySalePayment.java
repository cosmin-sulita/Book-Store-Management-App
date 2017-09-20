package bookstore.model;

public class PayBySalePayment implements IPayment {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Pay by sale";
	}

	@Override
	public String toStringForTable() {
		return "Pay by sale";
	}

	@Override
	public boolean dateIsDue() {
		// TODO Auto-generated method stub
		return false;
	}

}
