package bookstore.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class BookStoreInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	private BrowseStorePanel bsp;
	private AddInvoicePanel aiv;
	private MyInvoicesPanel mip;
	private JTabbedPane jtb;
	private String filler;

	public BookStoreInterface(String title) {
		super(title);

		jtb = new JTabbedPane();
		bsp = new BrowseStorePanel();
		aiv = new AddInvoicePanel();
		mip = new MyInvoicesPanel();

		filler = "      ";

		jtb.addTab(filler + " Add Invoice " + filler, aiv);
		jtb.addTab(filler + " Browse Store " + filler, bsp);
		jtb.addTab(filler + "Invoices " + filler, mip);

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

	public MyInvoicesPanel getMyInvoicesPanel() {
		return mip;
	}

}
