package bookstore.view;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddSupplierFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Box mainBox;
	private Box hBox1;
	private Box hBox2;

	private JLabel jlName;

	private JTextField jtName;

	private JButton bAddSupplier;

	public AddSupplierFrame(String title) {
		super(title);
		initWidgets();
		addWidgets();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(350, 100);
		setResizable(false);
	}

	private void initWidgets() {
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();

		jlName = new JLabel("Enter Name:");

		jtName = new JTextField(10);

		jtName.setHorizontalAlignment(JTextField.RIGHT);

		bAddSupplier = new JButton("Add Supplier");

	}

	private void addWidgets() {
		hBox1.add(Box.createHorizontalStrut(20));
		hBox1.add(jlName);

		hBox1.add(Box.createHorizontalStrut(32));
		hBox1.add(jtName);
		hBox1.add(Box.createHorizontalStrut(20));

		hBox2.add(Box.createHorizontalStrut(34));
		hBox2.add(Box.createHorizontalStrut(20));
		hBox2.add(bAddSupplier);

		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(10));

		getContentPane().add(mainBox);
	}

	public void addActionListener(ActionListener a) {
		bAddSupplier.addActionListener(a);

	}

	public JButton getButtonAddSupplier() {
		return bAddSupplier;
	}

	public String getName() {
		return jtName.getText().trim();
	}

	public void setTextFieldName(String string) {
		jtName.setText(string);
	}

}
