package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gui.tabs.pCreateOrder;
import gui.tabs.pProducts;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel pCreateOrder = new pCreateOrder();
		tabbedPane.addTab("Create Order", null, pCreateOrder, null);

		JPanel pCreateOffer = new JPanel();
		tabbedPane.addTab("Create Offer", null, pCreateOffer, null);

		JPanel pCustomers = new JPanel();
		tabbedPane.addTab("Customers", null, pCustomers, null);

		JPanel pProducts = new JPanel();
		tabbedPane.addTab("Products", null, pProducts, null);
		pProducts.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new pProducts();
		pProducts.add(panel, BorderLayout.CENTER);

		JPanel pOrders = new JPanel();
		tabbedPane.addTab("Orders", null, pOrders, null);

		JPanel pOffers = new JPanel();
		tabbedPane.addTab("Offers", null, pOffers, null);

		init();
	}

	private void init() {

	}

}
