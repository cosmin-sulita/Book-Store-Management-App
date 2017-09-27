package bookstore.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bookstore.model.MyTableModel;

public class BrowseStorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Box mainBox;
	private Box hBox1;
	private Box hBox2;
	private Box hBox3;
	private Box hBox4;
	private Box hBox5;

	private JLabel jlSearchBook;
	private JLabel jlBookTable;
	private JLabel jlTotalIncome;

	private JTextField jtTotalIncome;
	private JTextField jtSearchBook;

	private JButton bSearchBook;
	private JButton bShowAvailableBooks;
	private JButton bSellBook;
	private JButton bDeleteBook;
	private JButton bSave;
	private JButton bSaveAndQuit;

	private JTable tBooks;

	private MyTableModel model;
	private JScrollPane spBookTable;

	String[] bookColumns = { "Title", "Author", "Publisher", "Price", "Stock", "ISBN" };
	String[][] bookData = { { " ", " ", " ", " ", " ", " " } };

	public BrowseStorePanel() {
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

		jlSearchBook = new JLabel("Search Book by Title / ISBN");
		jlBookTable = new JLabel("Showing All Books in Library");
		jlTotalIncome = new JLabel("Total income");

		jtSearchBook = new JTextField();
		jtTotalIncome = new JTextField();
		jtTotalIncome.setHorizontalAlignment(JTextField.CENTER);
		jtTotalIncome.setEditable(false);

		ImageIcon seachIcon = new ImageIcon("search.png");
		ImageIcon bookIcon = new ImageIcon("book.png");

		bSearchBook = new JButton(seachIcon);
		bShowAvailableBooks = new JButton(bookIcon);
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
		tBooks.getColumnModel().getColumn(0).setPreferredWidth(180);
		tBooks.getColumnModel().getColumn(1).setPreferredWidth(150);
		tBooks.getColumnModel().getColumn(3).setPreferredWidth(50);
		tBooks.getColumnModel().getColumn(4).setPreferredWidth(40);

		spBookTable = new JScrollPane(tBooks);

		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void addWidgets() {
		hBox1.add(jlSearchBook);
		hBox1.add(Box.createHorizontalStrut(10));
		hBox1.add(jtSearchBook);
		hBox1.add(Box.createHorizontalStrut(10));
		hBox1.add(bSearchBook);

		hBox2.add(jlBookTable);
		hBox2.add(Box.createHorizontalStrut(110));
		hBox2.add(bShowAvailableBooks);
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(bDeleteBook);
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(bSellBook);

		hBox3.add(spBookTable);

		hBox5.add(bSave);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(bSaveAndQuit);
		hBox5.add(Box.createHorizontalStrut(100));
		hBox5.add(jlTotalIncome);
		hBox5.add(Box.createHorizontalStrut(10));
		hBox5.add(jtTotalIncome);
		hBox5.add(Box.createHorizontalStrut(10));

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
		bSearchBook.addActionListener(a);
		bShowAvailableBooks.addActionListener(a);
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

	public JButton getButtonSearchBook() {
		return bSearchBook;
	}

	public String getSearchedBook() {
		return jtSearchBook.getText().trim();
	}

	public void setTextTotalIncome(String totalIncome) {
		jtTotalIncome.setText(totalIncome);
	}

	public JButton getButtonShowAvailableBooks() {
		return bShowAvailableBooks;
	}

	public void clearSearchTextField() {
		jtSearchBook.setText("");
	}

}
