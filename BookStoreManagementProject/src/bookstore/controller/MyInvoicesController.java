package bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.InvoiceBuilder;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;
import bookstore.model.Invoice;
import bookstore.model.MyTableModel;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BookStoreInterface;
import bookstore.view.MyInvoicesPanel;

public class MyInvoicesController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void saveInvoice(AddInvoicePanel aip, IInvoice invoice, IInvoiceRepository invoiceRepository,
			MyInvoicesPanel mip) {

		int invoiceNumber;
		List<IProduct> productList = new ArrayList<IProduct>();
		ISupplier supplier;
		boolean rebate;
		double invoiceValue;

		IInvoice newInvoice;

		invoiceNumber = Integer.parseInt(aip.getTextFieldInvoiceNumber());
		supplier = aip.getSelectedSupplier();

		for (IProduct product : invoice.getProductList()) {
			productList.add(product);
		}

		rebate = aip.getCheckBoxRebate().isSelected();
		invoiceValue = invoice.getValue();

		IInvoiceBuilder invoiceBuilder;

		invoiceBuilder = new InvoiceBuilder();
		newInvoice = new Invoice(invoiceNumber, supplier, productList, invoiceValue, rebate);

		invoiceRepository.addInvoiceToList(newInvoice);

		updateInvoicesTable(mip, invoiceRepository);
	}

	public void updateInvoicesTable(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		String dataInvoice[][];

		while (((MyTableModel) mip.getInvoicesTable().getModel()).getRowCount() > 0) {
			((MyTableModel) mip.getInvoicesTable().getModel()).removeRow(0);
		}
		dataInvoice = invoiceRepository.toStringVector();
		for (int i = 0; i < dataInvoice.length; i++) {
			((MyTableModel) mip.getInvoicesTable().getModel()).addRow(dataInvoice[i]);
		}
	}

	public void openInvoice(BookStoreInterface screen, MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		int row;
		int invoiceNumberColumn = 0;
		String invoiceNumber;

		IInvoice invoice;

		try {
			row = ((JTable) mip.getInvoicesTable()).getSelectedRow();
			invoiceNumber = ((JTable) mip.getInvoicesTable()).getValueAt(row, invoiceNumberColumn).toString();
			invoice = invoiceRepository.getInvoiceByNumber(invoiceNumber);
			updateBooksTable(mip, invoice);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(screen, "No invoice selected");
		}
	}

	public void updateBooksTable(MyInvoicesPanel mip, IInvoice invoice) {
		String dataProduct[][];

		while (((MyTableModel) mip.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) mip.getBookTable().getModel()).removeRow(0);
		}
		dataProduct = invoice.toStringVector();
		for (int i = 0; i < dataProduct.length; i++) {
			((MyTableModel) mip.getBookTable().getModel()).addRow(dataProduct[i]);
		}
	}
}
