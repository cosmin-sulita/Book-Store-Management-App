package bookstore.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bookstore.model.MyTableModel;

public class MyInvoicesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Box mainBox;
	private Box hBox1;
	private Box hBox2;
	private Box hBox3;
	private Box hBox4;
	private Box hBox5;

	private JLabel jlInvoiceTable;
	private JLabel jlBookTable;
	private JLabel jlTotalIncome;

	private JTextField jtTotalIncome;

	private JButton bOpenInvoice;
	private JButton bDeleteInvoice;
	private JButton bSave;
	private JButton bSaveAndQuit;

	private JTable tInvoices;
	private JTable tBooks;

	private MyTableModel model;
	private JScrollPane spBookTable;
	private JScrollPane spInvoiceTable;

	String[] invoiceColumns = { "Invoice", "Supplier", "Value", "Rebate", "Payment" };
	String[][] invoiceData = { { " ", " ", " ", " ", " " } };

	String[] bookColumns = { "Book", "Publisher", "Qty", "Price", "Total" };
	String[][] bookData = { { " ", " ", " ", " ", " " } };

	public MyInvoicesPanel() {
		super(new FlowLayout());
		intWidgets();
		addWidgets();
	}

	private void intWidgets() {
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();

		jlInvoiceTable = new JLabel("Showing All Invoices To Pay");
		jlBookTable = new JLabel("Showing All Books From Invoice");
		jlTotalIncome = new JLabel("Total income");

		jtTotalIncome = new JTextField();
		jtTotalIncome.setHorizontalAlignment(JTextField.CENTER);
		jtTotalIncome.setEditable(false);

		bOpenInvoice = new JButton("Open");
		bDeleteInvoice = new JButton("Delete");
		bSave = new JButton("Save");
		bSaveAndQuit = new JButton("Save & Quit");

		model = new MyTableModel(invoiceData, invoiceColumns);
		tInvoices = new JTable(model);
		tInvoices.setPreferredScrollableViewportSize(new Dimension(428, 135));
		tInvoices.setFillsViewportHeight(true);
		tInvoices.setAutoCreateRowSorter(true);
		tInvoices.getTableHeader().setReorderingAllowed(false);
		tInvoices.getColumnModel().getColumn(0).setPreferredWidth(20);
		tInvoices.getColumnModel().getColumn(1).setPreferredWidth(100);
		tInvoices.getColumnModel().getColumn(2).setPreferredWidth(30);
		tInvoices.getColumnModel().getColumn(3).setPreferredWidth(20);

		spInvoiceTable = new JScrollPane(tInvoices);

		spInvoiceTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spInvoiceTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		model = new MyTableModel(bookData, bookColumns);
		tBooks = new JTable(model);
		tBooks.setPreferredScrollableViewportSize(new Dimension(428, 135));
		tBooks.setFillsViewportHeight(true);
		tBooks.setAutoCreateRowSorter(true);
		tBooks.getTableHeader().setReorderingAllowed(false);
		tBooks.getColumnModel().getColumn(0).setPreferredWidth(140);
		tBooks.getColumnModel().getColumn(1).setPreferredWidth(120);
		tBooks.getColumnModel().getColumn(2).setPreferredWidth(25);
		tBooks.getColumnModel().getColumn(3).setPreferredWidth(40);
		spBookTable = new JScrollPane(tBooks);

		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void addWidgets() {
		hBox1.add(jlInvoiceTable);
		hBox1.add(Box.createHorizontalStrut(150));
		hBox1.add(bOpenInvoice);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(bDeleteInvoice);

		hBox2.add(spInvoiceTable);

		hBox3.add(jlBookTable);

		hBox4.add(spBookTable);

		hBox5.add(bSave);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(bSaveAndQuit);
		hBox5.add(Box.createHorizontalStrut(100));
		hBox5.add(jlTotalIncome);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(jtTotalIncome);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(hBox5);

		add(mainBox);
	}

	public void addActionListener(ActionListener a) {
		bOpenInvoice.addActionListener(a);
		bDeleteInvoice.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
	}

	public JButton getButtonDeleteBook() {
		return bDeleteInvoice;
	}

	public JButton getButtonOpenInvoice() {
		return bOpenInvoice;
	}

	public JButton getButtonSave() {
		return bSave;
	}

	public JButton getButtonSaveAndQuit() {
		return bSaveAndQuit;
	}

	public JTable getInvoicesTable() {
		return tInvoices;
	}

	public JTable getBookTable() {
		return tBooks;
	}

}
