package bookstore.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import bookstore.model.IBook;
import bookstore.model.ISupplier;
import bookstore.model.MyTableModel;

public class AddInvoicePanel extends JPanel implements IView {

	private static final long serialVersionUID = 1L;

	private Box mainBox;
	private Box hBox1;
	private Box hBox2;
	private Box hBox3;
	private Box hBox4;
	private Box hBox5;
	private Box hBox6;
	private Box hBox7;
	private Box hBox8;
	private Box hBox9;

	private JLabel jlInvoiceNo;
	private JLabel jlDate;
	private JLabel jlInvoice;
	private JLabel jlSupplier;
	private JLabel jlBook;
	private JLabel jlQty;
	private JLabel jlInvoiceValue;
	private JLabel jlPublisher;
	private JLabel jlSupplierPrice;
	private JLabel jlPersonalPrice;

	private JTextField jtInvoiceNo;
	private JTextField jtDate;
	private JTextField jtQty;
	private JTextField jtInvoiceValue;
	private JTextField jtPublisher;
	private JTextField jtSupplierPrice;
	private JTextField jtPersonalPrice;
	private JTextField jtTerm;

	private JComboBox<ISupplier> jcSupplier;
	private JComboBox<IBook> jcBook;

	private JButton bSaveInvoice;
	private JButton bAddNewSupplier;
	private JButton bAddNewBook;
	private JButton bAddProduct;
	private JButton bRemoveProduct;

	private JRadioButton rbPayOnTerm;
	private JRadioButton rbDebtOnTheRoad;

	private JCheckBox cbRebate;

	private JTable tBooks;

	private MyTableModel model;
	private JScrollPane spBookTable;

	String[] bookColumns = { "Book", "Publisher", "Qty", "Price", "Total" };
	String[][] bookData = { { " ", " ", " ", " ", " " } };

	public AddInvoicePanel() {
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
		hBox6 = Box.createHorizontalBox();
		hBox7 = Box.createHorizontalBox();
		hBox8 = Box.createHorizontalBox();
		hBox9 = Box.createHorizontalBox();

		jlInvoiceNo = new JLabel("Invoice No.");
		jlDate = new JLabel("Date");
		jlSupplier = new JLabel("Supplier");
		jlBook = new JLabel("Book");
		jlPublisher = new JLabel("Publisher");
		jlQty = new JLabel("Qty");
		jlInvoice = new JLabel("Invoice");
		jlInvoiceValue = new JLabel("Invoice Value");
		jlSupplierPrice = new JLabel("Supp Price");
		jlPersonalPrice = new JLabel("My Price");

		jtInvoiceNo = new JTextField(1);
		jtDate = new JTextField(1);
		jtPublisher = new JTextField(1);
		jtInvoiceValue = new JTextField(1);
		jtQty = new JTextField(1);
		jtSupplierPrice = new JTextField(1);
		jtPersonalPrice = new JTextField(1);
		jtTerm = new JTextField(1);

		jcSupplier = new JComboBox<ISupplier>();
		jcBook = new JComboBox<IBook>();
		AutoCompleteDecorator.decorate(jcBook);

		jtInvoiceNo.setHorizontalAlignment(JTextField.LEFT);
		jtDate.setHorizontalAlignment(JTextField.LEFT);
		jtQty.setHorizontalAlignment(JTextField.CENTER);
		jtInvoiceValue.setHorizontalAlignment(JTextField.CENTER);
		jtSupplierPrice.setHorizontalAlignment(JTextField.CENTER);
		jtPersonalPrice.setHorizontalAlignment(JTextField.CENTER);

		jtPublisher.setEditable(false);
		jtInvoiceValue.setEditable(false);
		jtDate.setEditable(false);

		jtQty.setText("1");
		jtInvoiceValue.setText("0");

		bSaveInvoice = new JButton("Save Invoice");
		bAddNewSupplier = new JButton("New Supplier");
		bAddNewBook = new JButton("New Book");
		bAddProduct = new JButton("Add Product");
		bRemoveProduct = new JButton("Remove Product");

		rbPayOnTerm = new JRadioButton("Pay On Term");
		rbPayOnTerm.setSelected(true);
		rbDebtOnTheRoad = new JRadioButton("Debt On The Road");

		ButtonGroup group = new ButtonGroup();
		group.add(rbPayOnTerm);
		group.add(rbDebtOnTheRoad);

		cbRebate = new JCheckBox("Rebate");

		cbRebate.setSelected(false);

		model = new MyTableModel(bookData, bookColumns);
		tBooks = new JTable(model);
		tBooks.setPreferredScrollableViewportSize(new Dimension(428, 150));
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
		hBox1.add(jlInvoiceNo);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(jtInvoiceNo);
		hBox1.add(Box.createHorizontalStrut(96));
		hBox1.add(jlDate);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(jtDate);

		hBox2.add(jlSupplier);
		hBox2.add(Box.createHorizontalStrut(19));
		hBox2.add(jcSupplier);
		hBox2.add(Box.createHorizontalStrut(7));
		hBox2.add(cbRebate);
		hBox2.add(Box.createHorizontalStrut(10));
		hBox2.add(bAddNewSupplier);

		hBox3.add(jlBook);
		hBox3.add(Box.createHorizontalStrut(37));
		hBox3.add(jcBook);
		hBox3.add(Box.createHorizontalStrut(100));
		hBox3.add(bAddNewBook);

		hBox4.add(jlPublisher);
		hBox4.add(Box.createHorizontalStrut(12));
		hBox4.add(jtPublisher);
		hBox4.add(Box.createHorizontalStrut(210));

		hBox5.add(jlSupplierPrice);
		hBox5.add(Box.createHorizontalStrut(4));
		hBox5.add(jtSupplierPrice);
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jlPersonalPrice);
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jtPersonalPrice);
		hBox5.add(Box.createHorizontalStrut(20));
		hBox5.add(jlQty);
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jtQty);

		hBox6.add(Box.createHorizontalStrut(220));
		hBox6.add(bAddProduct);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(bRemoveProduct);

		hBox7.add(jlInvoice);
		hBox7.add(Box.createHorizontalStrut(45));
		hBox7.add(rbPayOnTerm);
		hBox7.add(Box.createHorizontalStrut(10));
		hBox7.add(jtTerm);
		hBox7.add(Box.createHorizontalStrut(10));
		hBox7.add(rbDebtOnTheRoad);
		hBox7.add(Box.createHorizontalStrut(45));

		hBox8.add(spBookTable);

		hBox9.add(bSaveInvoice);
		hBox9.add(Box.createHorizontalStrut(140));
		hBox9.add(jlInvoiceValue);
		hBox9.add(Box.createHorizontalStrut(20));
		hBox9.add(jtInvoiceValue);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(hBox5);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(hBox6);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(hBox7);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox8);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox9);

		add(mainBox);

	}

	public void addActionListener(ActionListener a) {
		bAddNewSupplier.addActionListener(a);
		bAddNewBook.addActionListener(a);
		bSaveInvoice.addActionListener(a);
		bAddProduct.addActionListener(a);
		bRemoveProduct.addActionListener(a);
		cbRebate.addActionListener(a);
		jcBook.addActionListener(a);
	}

	public JButton getButtonSaveInvoice() {
		return bSaveInvoice;
	}

	public JButton getAddNewSupplier() {
		return bAddNewSupplier;
	}

	public JButton getButtonAddNewBook() {
		return bAddNewBook;
	}

	public JButton getButtonAddBookToInvoice() {
		return bAddProduct;
	}

	public JButton getButtonRemoveBookFromInvoice() {
		return bRemoveProduct;
	}

	public JCheckBox getCheckBoxPrice() {
		return cbRebate;
	}

	public JTable getBookTable() {
		return tBooks;
	}

	public JComboBox<ISupplier> getComboBoxSupplier() {
		return jcSupplier;
	}

	public JComboBox<IBook> getComboBoxBook() {
		return jcBook;
	}

	public void setPublisherName(String publisherName) {
		jtPublisher.setText(publisherName);
	}

	public void setSupplierPrice(String supplierPrice) {
		jtSupplierPrice.setText(supplierPrice);
	}

	public void populateSupplierComboBox(List<ISupplier> supplierList) {
		jcSupplier.removeAllItems();
		for (ISupplier supplier : supplierList) {
			jcSupplier.addItem(supplier);
		}
	}

	public void populateBookComboBox(List<IBook> collection) {
		jcBook.removeAllItems();
		for (IBook book : collection) {
			jcBook.addItem(book);
		}
	}

	public void insertBookInComboBox(IBook book, int index) {
		jcBook.insertItemAt(book, index);
	}

	public void insertSupplierInComboBox(ISupplier supplier, int index) {
		jcSupplier.insertItemAt(supplier, index);
	}

	public IBook getSelectedBook() {
		return (IBook) jcBook.getSelectedItem();
	}

	public ISupplier getSelectedSupplier() {
		return (ISupplier) jcSupplier.getSelectedItem();
	}

	public void removeBookFromComboBox(IBook book) {
		jcBook.removeItem(book);
	}

	public JTable getProductTable() {
		return tBooks;
	}

	public String getTextFieldQuantity() {
		return jtQty.getText().trim();
	}

	public String getTextFieldSupplierPrice() {
		return jtSupplierPrice.getText().trim();
	}

	public void setTextFieldInvoiceValue(String totalAmmount) {
		jtInvoiceValue.setText(totalAmmount);
	}

	public void setTextFieldQuantity(String quantity) {
		jtQty.setText(quantity);
	}

	public void setTextFieldPersonalPrice(String price) {
		jtPersonalPrice.setText(price);
	}

	public void setTextFieldSupplierPrice(String price) {
		jtSupplierPrice.setText(price);
	}

	public JButton getButtonAddNewSupplier() {
		return bAddNewSupplier;
	}

	public void setTextFieldDate(String date) {
		jtDate.setText(date);
	}

	public String getTextInvoiceNumber() {
		return jtInvoiceNo.getText().trim();
	}

	public String getTextFieldInvoiceValue() {
		return jtInvoiceValue.getText().trim();
	}

}