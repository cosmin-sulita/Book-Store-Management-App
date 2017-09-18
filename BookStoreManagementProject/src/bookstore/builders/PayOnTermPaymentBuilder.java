package bookstore.builders;

import java.util.Date;

import bookstore.model.IPayment;
import bookstore.model.PayOnTermPayment;

public class PayOnTermPaymentBuilder implements IPaymentBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build(Date term) {
		return new PayOnTermPayment(term);
	}

	@Override
	public IPayment build() {
		// TODO Auto-generated method stub
		return null;
	}

}
