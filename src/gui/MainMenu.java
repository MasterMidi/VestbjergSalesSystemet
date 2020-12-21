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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import main.TryMe;
import model.people.Person;
import model.sale.Order.OrderLine;

public class MainMenu extends JFrame {

	private DefaultListModel<OrderLine> pModel;
	private DefaultListModel<Person> cModel;
	private JPanel contentPane;
	private OrderController orderController;
	private JTextField tfProducts;
	private JTextField tfCustumers;
	private JList<OrderLine> lProducts;
	private JList<Person> lCustomers;
	private String pText;
	private String cText;

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
		gbl_pCreateOrder.columnWidths = new int[] { 106, 0, 231, 0, 122, 0, 0, 0 };
		gbl_pCreateOrder.rowHeights = new int[] { 0, 23, 74, 276, 0, 0, 0 };
		gbl_pCreateOrder.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pCreateOrder.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pCreateOrder.setLayout(gbl_pCreateOrder);

		tfProducts = new JTextField();
		tfProducts.setForeground(Color.GRAY);
		tfProducts.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfProducts.getText().equals(pText)) {
					tfProducts.setText("");
					tfProducts.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfProducts.getText().isEmpty()) {
					tfProducts.setForeground(Color.GRAY);
					tfProducts.setText(pText);
				}
			}
		});
		tfProducts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		tfCustumers.setForeground(Color.GRAY);
		tfCustumers.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfCustumers.getText().equals(cText)) {
					tfCustumers.setText("");
					tfCustumers.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfCustumers.getText().isEmpty()) {
					tfCustumers.setForeground(Color.GRAY);
					tfCustumers.setText(cText);
				}
			}
		});
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

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 2;
		pCreateOrder.add(scrollPane_2, gbc_scrollPane_2);

		JTextArea textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		pCreateOrder.add(scrollPane, gbc_scrollPane);

		lProducts = new JList<OrderLine>();
		lProducts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
		gbc_scrollPane_1.gridy = 3;
		pCreateOrder.add(scrollPane_1, gbc_scrollPane_1);

		lCustomers = new JList<Person>();
		scrollPane_1.setViewportView(lCustomers);

		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 4;
		pCreateOrder.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnConfirm = new JButton("Færdig");
		panel.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPressed();
			}

		});

		JButton btnCancel = new JButton("Annuller");
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelPressed();
			}

		});

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

	protected void setEmptyText(FocusEvent e) {
		TextField currTF = (TextField) e.getComponent();
		currTF.setText("");

	}

	private void init() {
		orderController = new OrderController();
		pModel = new DefaultListModel<>();
		lProducts.setModel(pModel);
		cModel = new DefaultListModel<>();
		lCustomers.setModel(cModel);
		createRenderers();
		pText = tfProducts.getText();
		cText = tfCustumers.getText();
		new TryMe();
	}

	private void createRenderers() {
		lProducts.setCellRenderer(new DefaultListCellRenderer() {
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

		lCustomers.setCellRenderer(new DefaultListCellRenderer() {
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
		String search = tfCustumers.getText();
		List<Person> currList = orderController.findCustomers(search);
		currList.addAll(0, orderController.findCustomers("0000"));
		cModel.clear();
		cModel.addAll(currList);
	}

	private void productSearch() {
		String search = tfProducts.getText();
		orderController.scanProduct(search);
		refreshProductList();
	}

	private void CreateEmptyOrder() {
		orderController.createOrder();
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
}
