package bookstore.builders;

import java.util.Date;

import bookstore.model.PayBySalePayment;
import bookstore.model.IPayment;
import bookstore.model.PayOnTermPayment;

public class PaymentFactory implements IPaymentFactory {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment buildPayOnTermPayment(Date term) {
		return new PayOnTermPayment(term);
	}

	@Override
	public IPayment buildPayBySalePayment() {
		return new PayBySalePayment();
	}

}
