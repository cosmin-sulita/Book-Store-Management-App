package bookstore.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LoadScreen extends JFrame implements IView {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JButton jbNew;
	private JButton jbLoad;
	private JButton jbExit;

	public LoadScreen(String title) {
		super(title);

		initWidgets();
		addWidgets();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(440, 70);
		setResizable(false);
	}

	private void initWidgets() {
		panel = new JPanel(new FlowLayout());

		jbNew = new JButton("Start New Book Store");
		jbLoad = new JButton("Load Saved Book Store");
		jbExit = new JButton("Exit");
	}

	private void addWidgets() {
		panel.add(jbLoad);
		panel.add(jbNew);
		panel.add(jbExit);

		setContentPane(panel);
	}

	public void addActionListener(ActionListener l) {
		jbNew.addActionListener(l);
		jbLoad.addActionListener(l);
		jbExit.addActionListener(l);
	}

	public JButton getButtonLoad() {
		return jbLoad;
	}

	public JButton getButtonNew() {
		return jbNew;
	}

	public JButton getButtonExit() {
		return jbExit;
	}

}
