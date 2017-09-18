package bookstore.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import bookstore.model.IBook;
import bookstore.model.ISupplier;
import bookstore.model.MyTableModel;

public class AddInvoicePanel extends JPanel {

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
	private JLabel jlQuantity;
	private JLabel jlInvoiceValue;
	private JLabel jlPublisher;
	private JLabel jlSupplierPrice;
	private JLabel jlPersonalPrice;

	private JLabel jlBlank;

	private JTextField jtInvoiceNo;
	private JTextField jtDate;
	private JTextField jtQuantity;
	private JTextField jtInvoiceValue;
	private JTextField jtPublisher;
	private JTextField jtSupplierPrice;
	private JTextField jtPersonalPrice;
	private JXDatePicker jdTerm;

	private JComboBox<ISupplier> jcSupplier;
	private JComboBox<IBook> jcBook;

	private JButton bAddInvoice;
	private JButton bAddNewSupplier;
	private JButton bAddNewBook;
	private JButton bAddProduct;
	private JButton bRemoveProduct;

	private JRadioButton rbPayOnTerm;
	private JRadioButton rbDebtOnTheRoad;
	private ButtonGroup group;

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
		jlQuantity = new JLabel("Quantity");
		jlInvoice = new JLabel("Invoice");
		jlInvoiceValue = new JLabel("Invoice Value");
		jlSupplierPrice = new JLabel("Supp Price");
		jlPersonalPrice = new JLabel("My Price");

		jlBlank = new JLabel("                                 ");

		jtInvoiceNo = new JTextField();
		jtDate = new JTextField();
		jtPublisher = new JTextField();
		jtInvoiceValue = new JTextField();
		jtQuantity = new JTextField();
		jtSupplierPrice = new JTextField();
		jtPersonalPrice = new JTextField();
		jdTerm = new JXDatePicker();

		jdTerm.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

		jcSupplier = new JComboBox<ISupplier>();
		jcBook = new JComboBox<IBook>();
		AutoCompleteDecorator.decorate(jcBook);

		jtInvoiceNo.setHorizontalAlignment(JTextField.CENTER);
		jtDate.setHorizontalAlignment(JTextField.CENTER);
		jtQuantity.setHorizontalAlignment(JTextField.CENTER);
		jtInvoiceValue.setHorizontalAlignment(JTextField.CENTER);
		jtSupplierPrice.setHorizontalAlignment(JTextField.CENTER);
		jtPersonalPrice.setHorizontalAlignment(JTextField.CENTER);

		jtPublisher.setEditable(false);
		jtInvoiceValue.setEditable(false);
		jtDate.setEditable(false);
		jtInvoiceNo.setEditable(false);

		jtQuantity.setText("1");
		jtInvoiceValue.setText("0");

		bAddInvoice = new JButton("Add Invoice");
		bAddNewSupplier = new JButton("New Supplier");
		bAddNewBook = new JButton("New Book");
		bAddProduct = new JButton("Add Product");
		bRemoveProduct = new JButton("Remove Product");

		rbPayOnTerm = new JRadioButton("Pay On Term");
		rbPayOnTerm.setSelected(true);
		rbDebtOnTheRoad = new JRadioButton("Debt On The Road");

		group = new ButtonGroup();
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
		hBox1.add(jlBlank);
		hBox1.add(Box.createHorizontalStrut(28));
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
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jlQuantity);
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jtQuantity);

		hBox6.add(Box.createHorizontalStrut(220));
		hBox6.add(bAddProduct);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(bRemoveProduct);

		hBox7.add(jlInvoice);
		hBox7.add(Box.createHorizontalStrut(40));
		hBox7.add(rbPayOnTerm);
		hBox7.add(Box.createHorizontalStrut(5));
		hBox7.add(jdTerm);
		hBox7.add(Box.createHorizontalStrut(5));
		hBox7.add(rbDebtOnTheRoad);
		hBox7.add(Box.createHorizontalStrut(5));

		hBox8.add(spBookTable);

		hBox9.add(bAddInvoice);
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
		bAddInvoice.addActionListener(a);
		bAddProduct.addActionListener(a);
		bRemoveProduct.addActionListener(a);
		cbRebate.addActionListener(a);
		jcBook.addActionListener(a);
		rbPayOnTerm.addActionListener(a);
		rbDebtOnTheRoad.addActionListener(a);
	}

	public JButton getButtonSaveInvoice() {
		return bAddInvoice;
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

	public JCheckBox getCheckBoxRebate() {
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
		return jtQuantity.getText().trim();
	}

	public String getTextFieldSupplierPrice() {
		return jtSupplierPrice.getText().trim();
	}

	public void setTextFieldInvoiceValue(String totalAmmount) {
		jtInvoiceValue.setText(totalAmmount);
	}

	public void setTextFieldQuantity(String quantity) {
		jtQuantity.setText(quantity);
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

	public void setInvoiceNumber(String invoiceNumber) {
		jtInvoiceNo.setText(invoiceNumber);
	}

	public void disablePersonalPrice() {
		jtPersonalPrice.setText("");
		jtPersonalPrice.setEditable(false);
	}

	public void enablePersonalPrice() {
		jtPersonalPrice.setEditable(true);
	}

	public String getTextFieldPersonalPrice() {
		return jtPersonalPrice.getText().trim();
	}

	public String getTextFieldInvoiceNumber() {
		return jtInvoiceNo.getText().trim();
	}

	public boolean rebateIsSelected() {
		return cbRebate.isSelected();
	}

	public String getPaymentType() {
		if (rbPayOnTerm.isSelected()) {
			return "Pay on term";
		} else {
			return "Debt on the road";
		}
	}

	public Date getPaymentTerm() {
		return jdTerm.getDate();
	}

	public void initTerm(Date date) {
		jdTerm.setDate(date);
	}

	public void enableDatePicker(boolean b) {
		jdTerm.setEnabled(b);
	}

	public boolean debtOnTheRoadIsSelected() {
		return rbDebtOnTheRoad.isSelected();
	}

	public boolean payOnTermIsSelected() {
		return rbPayOnTerm.isSelected();
	}

	public JRadioButton getRadioButtonDebtOnTheRoad() {
		return rbDebtOnTheRoad;
	}

	public JRadioButton getRadioButtonPayOnTerm() {
		return rbPayOnTerm;
	}

}