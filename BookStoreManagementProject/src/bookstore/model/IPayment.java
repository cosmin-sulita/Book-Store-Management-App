package bookstore.model;

import java.io.Serializable;

public interface IPayment extends Serializable {

	String toStringForTable();

	boolean dateIsDue();

}
