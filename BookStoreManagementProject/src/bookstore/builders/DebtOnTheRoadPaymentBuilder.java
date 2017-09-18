package bookstore.builders;

import java.util.Date;

import bookstore.model.DebtOnTheRoadPayment;
import bookstore.model.IPayment;

public class DebtOnTheRoadPaymentBuilder implements IPaymentBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build() {
		return new DebtOnTheRoadPayment();
	}

	@Override
	public IPayment build(Date term) {
		// TODO Auto-generated method stub
		return null;
	}

}
