package bookstore.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayOnTermPayment implements IPayment {

	private static final long serialVersionUID = 1L;

	Date term;

	public PayOnTermPayment(Date term) {
		this.term = term;
	}

	@Override
	public String toStringForTable() {
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = dateFormatter.format(term);
		return formattedDate;
	}

	@Override
	public String toString() {
		return "Pay on term";
	}

	@Override
	public boolean dateIsDue() {
		Date today = new Date();
		return term.before(today);
	}

}
