package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.OrderController;
import main.TryMe;
import model.people.Person;
import model.people.PrivateCustomer;
import model.sale.Order.OrderLine;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenu extends JFrame {

	private DefaultListModel<OrderLine> pModel;
	private DefaultListModel<Person> cModel;
	private JPanel contentPane;
	private OrderController orderController;
	private String pText;
	private String cText;
	private JTextField txtfProduct;
	private JTextField txtfCustomer;
	private JTextArea txtaCustomer;
	private JList<Person> listCustomers;
	private JList<OrderLine> listProducts;
	private JScrollPane scrlCustomer;

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

		JPanel pCreateOrder = new JPanel();
		pCreateOrder.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				CreateEmptyOrder();
				System.out.println("hey");
			}
		});
		tabbedPane.addTab("Create Order", null, pCreateOrder, null);
		pCreateOrder.setLayout(new BorderLayout(0, 0));

		JPanel pMain = new JPanel();
		pCreateOrder.add(pMain);
		GridBagLayout gbl_pMain = new GridBagLayout();
		gbl_pMain.columnWidths = new int[] { 20, 106, 20, 231, 50, 0 };
		gbl_pMain.rowHeights = new int[] { 20, 23, 74, 276, 0 };
		gbl_pMain.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pMain.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pMain.setLayout(gbl_pMain);

		txtfProduct = new JHintTextField("Stregkode...");
		txtfProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				productSearch();
			}
		});
		txtfProduct.setForeground(Color.GRAY);
		txtfProduct.setColumns(10);
		GridBagConstraints gbc_txtfProduct = new GridBagConstraints();
		gbc_txtfProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtfProduct.gridx = 1;
		gbc_txtfProduct.gridy = 1;
		pMain.add(txtfProduct, gbc_txtfProduct);

		txtfCustomer = new JHintTextField("navn/tlf...");
		txtfCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				customerSearch();
			}
		});
		txtfCustomer.setForeground(Color.GRAY);
		txtfCustomer.setColumns(10);
		GridBagConstraints gbc_txtfCustomer = new GridBagConstraints();
		gbc_txtfCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_txtfCustomer.gridx = 3;
		gbc_txtfCustomer.gridy = 1;
		pMain.add(txtfCustomer, gbc_txtfCustomer);

		JScrollPane scrlProducts = new JScrollPane();
		GridBagConstraints gbc_scrlProducts = new GridBagConstraints();
		gbc_scrlProducts.fill = GridBagConstraints.BOTH;
		gbc_scrlProducts.gridheight = 2;
		gbc_scrlProducts.insets = new Insets(0, 0, 0, 5);
		gbc_scrlProducts.gridx = 1;
		gbc_scrlProducts.gridy = 2;
		pMain.add(scrlProducts, gbc_scrlProducts);

		listProducts = new JList<>();
		scrlProducts.setViewportView(listProducts);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(listProducts, popupMenu);

		JMenuItem mntmEditPrice = new JMenuItem("Edit price");
		mntmEditPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		popupMenu.add(mntmEditPrice);

		JScrollPane scrlCustomerInfo = new JScrollPane();
		GridBagConstraints gbc_scrlCustomerInfo = new GridBagConstraints();
		gbc_scrlCustomerInfo.fill = GridBagConstraints.BOTH;
		gbc_scrlCustomerInfo.insets = new Insets(0, 0, 5, 5);
		gbc_scrlCustomerInfo.gridx = 3;
		gbc_scrlCustomerInfo.gridy = 2;
		pMain.add(scrlCustomerInfo, gbc_scrlCustomerInfo);

		txtaCustomer = new JTextArea();
		scrlCustomerInfo.setViewportView(txtaCustomer);

		scrlCustomer = new JScrollPane();
		GridBagConstraints gbc_scrlCustomer = new GridBagConstraints();
		gbc_scrlCustomer.fill = GridBagConstraints.BOTH;
		gbc_scrlCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_scrlCustomer.gridx = 3;
		gbc_scrlCustomer.gridy = 3;
		pMain.add(scrlCustomer, gbc_scrlCustomer);

		listCustomers = new JList<>();
		listCustomers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				highlightCustomer();
			}
		});
		scrlCustomer.setViewportView(listCustomers);

		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pCreateOrder.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnConfirm = new JButton("Færdig");
		panel.add(btnConfirm);

		JButton btnCancel = new JButton("Annuller");
		panel.add(btnCancel);

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

//	protected void setEmptyText(FocusEvent e) {
//		TextField currTF = (TextField) e.getComponent();
//		currTF.setText("");
//
//	}

	private void init() {
		orderController = new OrderController();
		pModel = new DefaultListModel<>();
		cModel = new DefaultListModel<>();
		listProducts.setModel(pModel);
		listCustomers.setModel(cModel);
		createRenderers();
		new TryMe();
		customerSearch();
	}

	private void refreshProductList() {
		pModel.clear();
		pModel.addAll(orderController.getCurrentOrder().getOrderLineList());
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

	private void highlightCustomer() {
		Person person = listCustomers.getSelectedValue();
		if (person != null) {
			if (person instanceof PrivateCustomer) {
				String text = String.format("Navn: %s - (%s)\ntlf.: %s\nEmail: %s", 
						person.getName(),
						((PrivateCustomer) person).getCPR(), 
						person.getPhoneNr(), 
						person.getEmail());
				txtaCustomer.setText(text);

			} else {

			}

		}
	}

	private void createRenderers() {
		listProducts.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof OrderLine) {
					OrderLine castedValue = ((OrderLine) value);
					String text = String.format("%d x %s - %s,-", castedValue.getAmount(),
							castedValue.getProduct().getName(), Double.toString(castedValue.getPrice()));
					((JLabel) renderer).setText(text);
				}
				return renderer;
			}
		});

		listCustomers.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Person) {
					Person castedValue = ((Person) value);
					String text = String.format("%s - (%s)", castedValue.getName(), castedValue.getPhoneNr());
					((JLabel) renderer).setText(text);
				}
				return renderer;
			}
		});
	}

	private void customerSearch() {
		String search = txtfCustomer.getText();
		List<Person> currList = orderController.findCustomers(search);
		currList.addAll(0, orderController.findCustomers("0000"));
		cModel.clear();
		cModel.addAll(currList);
	}

	private void productSearch() {
		String search = txtfProduct.getText();
		orderController.scanProduct(search);
		refreshProductList();
	}

	private void CreateEmptyOrder() {
		orderController.createOrder();
		customerSearch();
	}
}
