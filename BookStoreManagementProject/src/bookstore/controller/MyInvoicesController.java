package bookstore.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.InvoiceBuilder;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.view.AddInvoicePanel;

public class MyInvoicesController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void saveInvoice(AddInvoicePanel aip, IInvoice invoice, IInvoiceRepository invoiceRepository) {

		int invoiceNumber;
		LocalDate date;

		invoiceNumber = Integer.parseInt(aip.getTextFieldInvoiceNumber());
		date = LocalDate.now();

		IInvoiceBuilder invoiceBuilder;

		invoiceBuilder = new InvoiceBuilder();

		invoiceRepository.addInvoiceToList(invoice);

	}

}
