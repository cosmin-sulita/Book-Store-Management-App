package bookstore.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class BookStoreInterface extends JFrame implements IView {

	private static final long serialVersionUID = 1L;

	private BrowseStorePanel bsp;
	private AddInvoicePanel aiv;
	private JTabbedPane jtb;
	private String filler;

	public BookStoreInterface(String title) {
		super(title);

		jtb = new JTabbedPane();
		bsp = new BrowseStorePanel();
		aiv = new AddInvoicePanel();

		filler = "      ";

		jtb.addTab(filler + " Add Invoice " + filler, aiv);
		jtb.addTab(filler + " Browse Store " + filler, bsp);

		add(jtb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(510, 510);
		setResizable(false);
	}

	public BrowseStorePanel getBrowseStorePanel() {
		return bsp;
	}

	public JTabbedPane getTabbedPane() {
		return jtb;
	}

	public String getFiller() {
		return filler;
	}

	public AddInvoicePanel getAddInvoicePanel() {
		return aiv;
	}

}
