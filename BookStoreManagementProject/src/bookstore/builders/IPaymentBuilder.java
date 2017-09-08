package bookstore.builders;

import java.io.Serializable;

import bookstore.model.IPayment;

public interface IPaymentBuilder extends Serializable {

	IPayment build();

}
