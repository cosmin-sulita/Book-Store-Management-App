package bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import bookstore.builders.DebtOnTheRoadPaymentBuilder;
import bookstore.builders.IInvoiceBuilder;
import bookstore.builders.IPaymentBuilder;
import bookstore.builders.InvoiceBuilder;
import bookstore.builders.PayOnTermPaymentBuilder;
import bookstore.model.IBookStore;
import bookstore.model.IInvoice;
import bookstore.model.IInvoiceRepository;
import bookstore.model.IPayment;
import bookstore.model.IProduct;
import bookstore.model.ISupplier;
import bookstore.model.MyTableModel;
import bookstore.view.AddInvoicePanel;
import bookstore.view.BookStoreInterface;
import bookstore.view.MyInvoicesPanel;

public class MyInvoicesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int REBATE_PERCENT = 15;

	public void saveInvoice(AddInvoicePanel aip, MyInvoicesPanel mip, IInvoice invoice,
			IInvoiceRepository invoiceRepository, IBookStore bookStore) {

		int invoiceNumber;
		List<IProduct> productList = new ArrayList<IProduct>();
		ISupplier supplier;
		String rebate;
		double invoiceValue;
		IPayment paymentType;

		IInvoice newInvoice;
		IInvoiceBuilder invoiceBuilder = new InvoiceBuilder();
		IPaymentBuilder payOnTermPaymentBuilder = new PayOnTermPaymentBuilder();
		IPaymentBuilder debtOnTheRoadPaymentBuilder = new DebtOnTheRoadPaymentBuilder();

		try {
			invoiceNumber = Integer.parseInt(aip.getTextFieldInvoiceNumber());
			supplier = aip.getSelectedSupplier();

			for (IProduct product : invoice.getProductList()) {
				productList.add(product);
			}

			invoiceValue = invoice.getValue();

			if (aip.rebateIsSelected()) {
				invoiceValue = invoiceValue - invoiceValue * REBATE_PERCENT / 100;
				rebate = "Yes";
			} else {
				rebate = "No";
			}

			if (aip.getPaymentType() == "Pay on term") {
				paymentType = payOnTermPaymentBuilder.build();
			} else {
				paymentType = debtOnTheRoadPaymentBuilder.build();
			}

			newInvoice = invoiceBuilder.build(invoiceNumber, supplier, productList, rebate, invoiceValue, paymentType);

			if (newInvoice.isEmpty()) {
				JOptionPane.showMessageDialog(aip, "Invoice is empty");
			} else {
				invoiceRepository.addInvoiceToList(newInvoice);
			}

			updateInvoicesTable(mip, invoiceRepository);
			setTotalInvoicesValue(mip, invoiceRepository);
			bookStore.addBooksToStock(newInvoice);
			bookStore.setBooksPrice(invoiceRepository);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(aip, "Incomplete invoice");
		}
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
		setTotalInvoicesValue(mip, invoiceRepository);
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

	private void setTotalInvoicesValue(MyInvoicesPanel mip, IInvoiceRepository invoiceRepository) {
		double totalInvoiceValue;
		invoiceRepository.calculateTotalInvoiceValue();
		totalInvoiceValue = invoiceRepository.getTotalInvoiceValue();
		mip.setTotalInvoiceValue(String.valueOf(totalInvoiceValue));
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

	public void deleteInvoice(MyInvoicesPanel mip, IInvoice invoice, IInvoiceRepository invoiceRepository,
			IBookStore bookStore) {
		int row;
		int invoiceNumberColumn = 0;
		String invoiceNumber;
		IInvoice selectedInvoice;

		try {
			row = ((JTable) mip.getInvoicesTable()).getSelectedRow();
			invoiceNumber = ((JTable) mip.getInvoicesTable()).getValueAt(row, invoiceNumberColumn).toString();

			selectedInvoice = invoiceRepository.getInvoiceByNumber(invoiceNumber);
			bookStore.removeBooksFromStock(selectedInvoice, bookStore);

			invoiceRepository.removeInvoiceFromList(selectedInvoice);
			updateInvoicesTable(mip, invoiceRepository);
			setTotalInvoicesValue(mip, invoiceRepository);
			selectedInvoice.removeAllProducts();
			updateBooksTable(mip, selectedInvoice);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(mip, "No invoice selected");
		}
	}

}
