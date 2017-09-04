package bookstore.controller;

import java.io.Serializable;
import java.util.List;

import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.InvoiceBuilder;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;
import bookstore.model.Invoice;
import bookstore.model.MyTableModel;
import bookstore.view.AddInvoicePanel;
import bookstore.view.MyInvoicesPanel;

public class MyInvoicesController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void saveInvoice(AddInvoicePanel aip, IInvoice invoice, IInvoiceRepository invoiceRepository,
			MyInvoicesPanel mip, InvoicePanelController invoicePanelController) {

		int invoiceNumber;
		List<IProduct> productList;
		ISupplier supplier;
		boolean rebate;
		double invoiceValue;

		invoiceNumber = Integer.parseInt(aip.getTextFieldInvoiceNumber());
		supplier = aip.getSelectedSupplier();
		productList = invoice.getProductList();
		rebate = aip.getCheckBoxRebate().isSelected();
		invoiceValue = invoice.getValue();

		IInvoiceBuilder invoiceBuilder;

		invoiceBuilder = new InvoiceBuilder();
		invoice = new Invoice(invoiceNumber, supplier, productList, invoiceValue, rebate);

		invoiceRepository.addInvoiceToList(invoice);

		updateInvoicesTable(mip, invoiceRepository);
		clearInvoicePanel(aip, invoicePanelController);
	}

	private void clearInvoicePanel(AddInvoicePanel aip, InvoicePanelController invoicePanelController) {
		invoicePanelController.setInvoiceNumber(aip);
	}

	private void updateInvoicesTable(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		String dataInvoice[][];

		while (((MyTableModel) mip.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) mip.getBookTable().getModel()).removeRow(0);
		}
		dataInvoice = invoiceRepository.toStringVector();
		for (int i = 0; i < dataInvoice.length; i++) {
			((MyTableModel) mip.getBookTable().getModel()).addRow(dataInvoice[i]);
		}
	}

}
