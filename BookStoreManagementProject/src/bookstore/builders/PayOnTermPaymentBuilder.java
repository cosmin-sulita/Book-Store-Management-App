package bookstore.builders;

import bookstore.model.IPayment;
import bookstore.model.PayOnTermPayment;

public class PayOnTermPaymentBuilder implements IPaymentBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build() {
		return new PayOnTermPayment();
	}
}
