package bookstore.builders;

import java.io.Serializable;
import java.util.Date;

import bookstore.model.IPayment;

public interface IPaymentFactory extends Serializable {

	IPayment buildPayOnTermPayment(Date term);

	IPayment buildPayBySalePayment();

}
