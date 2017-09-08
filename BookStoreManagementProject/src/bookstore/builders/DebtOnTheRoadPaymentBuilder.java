package bookstore.builders;

import bookstore.model.DebtOnTheRoadPayment;
import bookstore.model.IPayment;

public class DebtOnTheRoadPaymentBuilder implements IPaymentBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build() {
		return new DebtOnTheRoadPayment();
	}

}
