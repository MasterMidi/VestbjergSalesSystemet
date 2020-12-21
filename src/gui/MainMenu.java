package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import controller.OrderController;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private OrderController orderController;
	private JTextField tfProducts;
	private JTextField tfCustumers;
	private JList lProducts;
	private JList lCustomers;
	private JTextPane tpDisplayCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
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

		JPanel pCreateOrder = new JPanel();
		pCreateOrder.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				CreateEmptyOrder();
			}
		});
		pCreateOrder.setName("");
		tabbedPane.addTab("Create Order", null, pCreateOrder, null);
		GridBagLayout gbl_pCreateOrder = new GridBagLayout();
		gbl_pCreateOrder.columnWidths = new int[] { 106, 0, 231, 0, 91, 0, 0, 0 };
		gbl_pCreateOrder.rowHeights = new int[] { 0, 23, 276, 0, 0, 0 };
		gbl_pCreateOrder.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pCreateOrder.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pCreateOrder.setLayout(gbl_pCreateOrder);

		tfProducts = new JTextField();
		tfProducts.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				productSearch();
			}
		});
		tfProducts.setText("Stregkode...");
		GridBagConstraints gbc_tfProducts = new GridBagConstraints();
		gbc_tfProducts.insets = new Insets(0, 0, 5, 5);
		gbc_tfProducts.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfProducts.gridx = 0;
		gbc_tfProducts.gridy = 1;
		pCreateOrder.add(tfProducts, gbc_tfProducts);
		tfProducts.setColumns(10);

		tfCustumers = new JTextField();
		tfCustumers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				customerSearch();
			}
		});
		tfCustumers.setText("navn/tlf...");
		GridBagConstraints gbc_tfCustumers = new GridBagConstraints();
		gbc_tfCustumers.insets = new Insets(0, 0, 5, 5);
		gbc_tfCustumers.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCustumers.gridx = 2;
		gbc_tfCustumers.gridy = 1;
		pCreateOrder.add(tfCustumers, gbc_tfCustumers);
		tfCustumers.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		pCreateOrder.add(scrollPane, gbc_scrollPane);

		lProducts = new JList();
		lProducts.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setViewportView(lProducts);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(lProducts, popupMenu);

		JMenuItem mntmEdit = new JMenuItem("Ændre pris");
		mntmEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editPressed();
			}
		});
		popupMenu.add(mntmEdit);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 2;
		pCreateOrder.add(scrollPane_1, gbc_scrollPane_1);

		lCustomers = new JList();
		scrollPane_1.setViewportView(lCustomers);

		tpDisplayCustomer = new JTextPane();
		tpDisplayCustomer.setEditable(false);
		GridBagConstraints gbc_tpDisplayCustomer = new GridBagConstraints();
		gbc_tpDisplayCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_tpDisplayCustomer.fill = GridBagConstraints.BOTH;
		gbc_tpDisplayCustomer.gridx = 4;
		gbc_tpDisplayCustomer.gridy = 2;
		pCreateOrder.add(tpDisplayCustomer, gbc_tpDisplayCustomer);

		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelPressed();
			}

		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 4;
		pCreateOrder.add(btnCancel, gbc_btnCancel);

		JButton btnConfirm = new JButton("Færdig");
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPressed();
			}

		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.anchor = GridBagConstraints.EAST;
		gbc_btnConfirm.gridx = 6;
		gbc_btnConfirm.gridy = 4;
		pCreateOrder.add(btnConfirm, gbc_btnConfirm);

		JPanel pCreateOffer = new JPanel();
		tabbedPane.addTab("Create Offer", null, pCreateOffer, null);

		JPanel pCustomers = new JPanel();
		tabbedPane.addTab("Customers", null, pCustomers, null);

		JPanel pProducts = new JPanel();
		tabbedPane.addTab("Products", null, pProducts, null);
		pProducts.setLayout(new BorderLayout(0, 0));

		JPanel pOrders = new JPanel();
		tabbedPane.addTab("Orders", null, pOrders, null);

		JPanel pOffers = new JPanel();
		tabbedPane.addTab("Offers", null, pOffers, null);

		init();
	}

	private void init() {
		orderController = new OrderController();
	}

	private void editPressed() {
		// TODO Auto-generated method stub

	}

	private void cancelPressed() {
		// TODO Auto-generated method stub

	}

	private void confirmPressed() {
		// TODO Auto-generated method stub

	}

	private void customerSearch() {
		// TODO Auto-generated method stub

	}

	private void productSearch() {
		// TODO Auto-generated method stub

	}

	private void CreateEmptyOrder() {
		orderController.createOrder();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
