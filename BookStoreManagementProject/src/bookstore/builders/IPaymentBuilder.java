package bookstore.builders;

import java.io.Serializable;
import java.util.Date;

import bookstore.model.IPayment;

public interface IPaymentBuilder extends Serializable {

	IPayment build();

	IPayment build(Date term);

}
