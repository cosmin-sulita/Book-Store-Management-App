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

	private JLabel jlBookTable;
	private JLabel jlTotalIncome;

	private JTextField jtTotalIncome;

	private JButton bSellBook;
	private JButton bDeleteBook;
	private JButton bSave;
	private JButton bSaveAndQuit;

	private JTable tBooks;

	private MyTableModel model;
	private JScrollPane spBookTable;

	String[] bookColumns = { "Invoice", "Supplier", "Value", "Rebate", "Payment" };
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

		jlBookTable = new JLabel("Showing All Invoices To Pay");
		jlTotalIncome = new JLabel("Total income");

		jtTotalIncome = new JTextField();
		jtTotalIncome.setHorizontalAlignment(JTextField.CENTER);
		jtTotalIncome.setEditable(false);

		bSellBook = new JButton("Sell");
		bDeleteBook = new JButton("Delete");
		bSave = new JButton("Save");
		bSaveAndQuit = new JButton("Save & Quit");

		model = new MyTableModel(bookData, bookColumns);
		tBooks = new JTable(model);
		tBooks.setPreferredScrollableViewportSize(new Dimension(428, 300));
		tBooks.setFillsViewportHeight(true);
		tBooks.setAutoCreateRowSorter(true);
		tBooks.getTableHeader().setReorderingAllowed(false);
		tBooks.getColumnModel().getColumn(0).setPreferredWidth(40);
		tBooks.getColumnModel().getColumn(1).setPreferredWidth(100);
		tBooks.getColumnModel().getColumn(2).setPreferredWidth(30);
		tBooks.getColumnModel().getColumn(3).setPreferredWidth(20);

		spBookTable = new JScrollPane(tBooks);

		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void addWidgets() {
		hBox1.add(jlBookTable);
		hBox1.add(Box.createHorizontalStrut(170));
		hBox1.add(bDeleteBook);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(bSellBook);

		hBox2.add(spBookTable);

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
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);

		add(mainBox);
	}

	public void addActionListener(ActionListener a) {
		bSellBook.addActionListener(a);
		bDeleteBook.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
	}

	public JButton getButtonSellBook() {
		return bSellBook;
	}

	public JButton getButtonDeleteBook() {
		return bDeleteBook;
	}

	public JButton getButtonSave() {
		return bSave;
	}

	public JButton getButtonSaveAndQuit() {
		return bSaveAndQuit;
	}

	public JTable getBookTable() {
		return tBooks;
	}

}
