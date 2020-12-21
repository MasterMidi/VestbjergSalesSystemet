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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import model.sale.Order.OrderLine;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame {

	private DefaultListModel<OrderLine> pModel;
	private DefaultListModel<Person> cModel;
	private JPanel contentPane;
	private OrderController orderController;
	private String pText;
	private String cText;
	private JTextField txtfBarcode;
	private JTextField txtfCustomer;
	private JTextArea txtaCustomer;
	private JList<Person> listCustomers;
	private JList<OrderLine> listProducts;

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
		tabbedPane.addTab("Create Order", null, pCreateOrder, null);
		pCreateOrder.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		pCreateOrder.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 106, 0, 231, 0, 122, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 23, 74, 276, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		txtfBarcode = new JHintTextField("Stregkode...");
		txtfBarcode.setForeground(Color.GRAY);
		txtfBarcode.setColumns(10);
		GridBagConstraints gbc_txtfBarcode = new GridBagConstraints();
		gbc_txtfBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtfBarcode.gridx = 1;
		gbc_txtfBarcode.gridy = 1;
		panel_1.add(txtfBarcode, gbc_txtfBarcode);

		txtfCustomer = new JHintTextField("navn/tlf...");
		txtfCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		txtfCustomer.setForeground(Color.GRAY);
		txtfCustomer.setColumns(10);
		GridBagConstraints gbc_txtfCustomer = new GridBagConstraints();
		gbc_txtfCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_txtfCustomer.gridx = 3;
		gbc_txtfCustomer.gridy = 1;
		panel_1.add(txtfCustomer, gbc_txtfCustomer);

		JScrollPane scrlProducts = new JScrollPane();
		GridBagConstraints gbc_scrlProducts = new GridBagConstraints();
		gbc_scrlProducts.fill = GridBagConstraints.BOTH;
		gbc_scrlProducts.gridheight = 2;
		gbc_scrlProducts.insets = new Insets(0, 0, 5, 5);
		gbc_scrlProducts.gridx = 1;
		gbc_scrlProducts.gridy = 2;
		panel_1.add(scrlProducts, gbc_scrlProducts);

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
		panel_1.add(scrlCustomerInfo, gbc_scrlCustomerInfo);

		txtaCustomer = new JTextArea();
		scrlCustomerInfo.setViewportView(txtaCustomer);

		JScrollPane scrlCustomer = new JScrollPane();
		GridBagConstraints gbc_scrlCustomer = new GridBagConstraints();
		gbc_scrlCustomer.fill = GridBagConstraints.BOTH;
		gbc_scrlCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_scrlCustomer.gridx = 3;
		gbc_scrlCustomer.gridy = 3;
		panel_1.add(scrlCustomer, gbc_scrlCustomer);

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
		txtaCustomer.setText(listCustomers.getSelectedValue().getName());
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
}
