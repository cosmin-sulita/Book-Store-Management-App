package bookstore.view;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Box mainBox;
	private Box hBox1;
	private Box hBox2;
	private Box hBox3;
	private Box hBox4;
	private Box hBox5;
	private Box hBox6;

	private JLabel jlIsbn;
	private JLabel jlTitle;
	private JLabel jlAuthor;
	private JLabel jlPublisher;
	private JLabel jlLogDog;

	private JTextField jtIsbn;
	private JTextField jtTitle;
	private JTextField jtAuthor;
	private JTextField jtPublisher;

	private JButton bAddBook;
	private JButton bSave;
	private JButton bSaveAndQuit;

	private JTextArea jtaLog;

	private JScrollPane scrollPane;

	public AddBookFrame(String title) {
		super(title);
		initWidgets();
		addWidgets();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(350, 400);
		setResizable(false);
	}

	private void initWidgets() {
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();
		hBox6 = Box.createHorizontalBox();

		jlIsbn = new JLabel("Enter ISBN:");
		jlTitle = new JLabel("Enter Title:");
		jlAuthor = new JLabel("Enter Author:");
		jlPublisher = new JLabel("Enter Publisher:");
		jlLogDog = new JLabel("Log:");

		jtIsbn = new JTextField(10);
		jtTitle = new JTextField(10);
		jtAuthor = new JTextField(10);
		jtPublisher = new JTextField(10);

		jtIsbn.setHorizontalAlignment(JTextField.RIGHT);
		jtTitle.setHorizontalAlignment(JTextField.RIGHT);
		jtAuthor.setHorizontalAlignment(JTextField.RIGHT);
		jtPublisher.setHorizontalAlignment(JTextField.RIGHT);

		bAddBook = new JButton("Add Book To Library");
		bSave = new JButton("Save");
		bSaveAndQuit = new JButton("Save & Quit");

		jtaLog = new JTextArea(10, 24);
		jtaLog.setEditable(false);

		scrollPane = new JScrollPane(jtaLog);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private void addWidgets() {
		hBox1.add(Box.createHorizontalStrut(20));
		hBox1.add(jlTitle);

		hBox1.add(Box.createHorizontalStrut(43));
		hBox1.add(jtTitle);
		hBox1.add(Box.createHorizontalStrut(20));

		hBox2.add(Box.createHorizontalStrut(20));
		hBox2.add(jlAuthor);

		hBox2.add(Box.createHorizontalStrut(29));
		hBox2.add(jtAuthor);
		hBox2.add(Box.createHorizontalStrut(20));

		hBox3.add(Box.createHorizontalStrut(20));
		hBox3.add(jlPublisher);

		hBox3.add(Box.createHorizontalStrut(13));
		hBox3.add(jtPublisher);
		hBox3.add(Box.createHorizontalStrut(20));

		hBox4.add(Box.createHorizontalStrut(20));
		hBox4.add(jlIsbn);

		hBox4.add(Box.createHorizontalStrut(40));
		hBox4.add(jtIsbn);
		hBox4.add(Box.createHorizontalStrut(20));

		hBox5.add(jlLogDog);
		hBox5.add(Box.createHorizontalStrut(140));
		hBox5.add(bAddBook);

		hBox6.add(bSave);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(bSaveAndQuit);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(scrollPane);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox6);
		mainBox.add(Box.createVerticalStrut(5));

		getContentPane().add(mainBox);

		mainBox.add(Box.createVerticalStrut(5));
	}

	public void addActionListener(ActionListener a) {
		bAddBook.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
	}

	public JButton getButtonAddBook() {
		return bAddBook;
	}

	public JButton getButtonSave() {
		return bSave;
	}

	public JButton getButtonSaveAndQuit() {
		return bSaveAndQuit;
	}

	public String getTextFieldIsbn() {
		return jtIsbn.getText().trim();
	}

	public void setTextFieldIsbn(String string) {
		jtIsbn.setText(string);
	}

	public String getTextFieldTitle() {
		return jtTitle.getText().trim();
	}

	public void setTextFieldTitle(String string) {
		jtTitle.setText(string);
	}

	public String getTextFieldAuthor() {
		return jtAuthor.getText().trim();
	}

	public void setTextFieldAuthor(String string) {
		jtAuthor.setText(string);
	}

	public String getTextFieldPublisher() {
		return jtPublisher.getText().trim();
	}

	public void setTextFieldPublisher(String string) {
		jtPublisher.setText(string);
	}

	public void setLog(String text) {
		jtaLog.setText(text);
	}

	public void appendLog(String text) {
		jtaLog.append(text);
	}

}