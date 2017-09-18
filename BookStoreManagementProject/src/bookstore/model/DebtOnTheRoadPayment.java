package bookstore.model;

public class DebtOnTheRoadPayment implements IPayment {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Debt on the road";
	}

	@Override
	public String toStringForTable() {
		return "Debt of the road";
	}

	@Override
	public boolean dateIsDue() {
		// TODO Auto-generated method stub
		return false;
	}

}
