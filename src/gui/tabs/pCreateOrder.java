package gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
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
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.OrderController;
import gui.Input;
import gui.components.JHintTextField;
import main.TryMe;
import model.people.BuisnessCustomer;
import model.people.Person;
import model.people.PrivateCustomer;
import model.sale.Order.OrderLine;
import model.sale.PaymentMethod;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class pCreateOrder extends JPanel {

	private DefaultListModel<OrderLine> pModel;
	private DefaultListModel<Person> cModel;
	private OrderController orderController;
	private JHintTextField txtfProduct;
	private JHintTextField txtfCustomer;
	private JTextArea txtaCustomer;
	private JList<Person> listCustomers;
	private JList<OrderLine> listProducts;
	private JScrollPane scrlCustomer;

	/**
	 * Create the panel.
	 */
	public pCreateOrder() {
		setMinimumSize(new Dimension(400, 250));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				resetScreen();
			}
		});
		setLayout(new BorderLayout(0, 0));

		JPanel pMain = new JPanel();
		pMain.setMinimumSize(new Dimension(600, 10));
		add(pMain, BorderLayout.CENTER);
		GridBagLayout gbl_pMain = new GridBagLayout();
		gbl_pMain.columnWidths = new int[] { 10, 200, 10, 200, 10, 0 };
		gbl_pMain.rowHeights = new int[] { 10, 20, 0 };
		gbl_pMain.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pMain.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		pMain.setLayout(gbl_pMain);

		txtfProduct = new JHintTextField();
		txtfProduct.setHint("Stregkode...");
		txtfProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				productSearch();
			}
		});
		txtfProduct.setColumns(10);
		GridBagConstraints gbc_txtfProduct = new GridBagConstraints();
		gbc_txtfProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtfProduct.gridx = 1;
		gbc_txtfProduct.gridy = 1;
		pMain.add(txtfProduct, gbc_txtfProduct);

		txtfCustomer = new JHintTextField();
		txtfCustomer.setHint("navn/tlf...");
		txtfCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				customerSearch();
			}
		});
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
		listProducts.setMinimumSize(new Dimension(200, 0));
		listProducts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrlProducts.setViewportView(listProducts);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(listProducts, popupMenu);

		JMenuItem mntmEditPrice = new JMenuItem("Ændre pris");
		mntmEditPrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editpriceClicked();
			}
		});
		popupMenu.add(mntmEditPrice);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ændre antal");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAmountClicked();
			}
		});
		popupMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Slet product");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClicked();
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JScrollPane scrlCustomerInfo = new JScrollPane();
		GridBagConstraints gbc_scrlCustomerInfo = new GridBagConstraints();
		gbc_scrlCustomerInfo.fill = GridBagConstraints.BOTH;
		gbc_scrlCustomerInfo.insets = new Insets(0, 0, 5, 5);
		gbc_scrlCustomerInfo.gridx = 3;
		gbc_scrlCustomerInfo.gridy = 2;
		pMain.add(scrlCustomerInfo, gbc_scrlCustomerInfo);

		txtaCustomer = new JTextArea();
		txtaCustomer.setEditable(false);
		scrlCustomerInfo.setViewportView(txtaCustomer);

		scrlCustomer = new JScrollPane();
		GridBagConstraints gbc_scrlCustomer = new GridBagConstraints();
		gbc_scrlCustomer.fill = GridBagConstraints.BOTH;
		gbc_scrlCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_scrlCustomer.gridx = 3;
		gbc_scrlCustomer.gridy = 3;
		pMain.add(scrlCustomer, gbc_scrlCustomer);

		listCustomers = new JList<>();
		listCustomers.setMinimumSize(new Dimension(200, 0));
		listCustomers.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				highlightCustomer();
			}
		});
		scrlCustomer.setViewportView(listCustomers);

		JPanel pBottom = new JPanel();
		pBottom.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(pBottom, BorderLayout.SOUTH);
		pBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnConfirm = new JButton("Færdig");
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishPressed();
			}
		});
		pBottom.add(btnConfirm);

		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelPressed();
			}
		});
		pBottom.add(btnCancel);

		init();
	}

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

	private void cancelPressed() {
		resetScreen();
	}

	private void finishPressed() {
		Person currPerson = listCustomers.getSelectedValue();
		int numOfItems = pModel.getSize();
		if (currPerson != null && numOfItems > 0) {
			orderController.attachCustomer(currPerson.getPhoneNr());
			PaymentMethod payment = (currPerson.getPhoneNr().equalsIgnoreCase("0000")) ? findPaymentMothod()
					: PaymentMethod.invoice;
			if (payment != null) {
				orderController.finishSale(payment);
				resetScreen();
			}
		} else {
			String message = numOfItems <= 0 ? "Fejl, tilføj varer" : "Fejl, ingen kunde valgt!";
			JOptionPane.showMessageDialog(this, message);
		}
	}

	private PaymentMethod findPaymentMothod() {
		// TODO find payment method (cash or card)
		PaymentMethod res = null;
		PaymentMethod[] list = { PaymentMethod.cash, PaymentMethod.creditcard };
		res = Input.getPaymentInput("Vælg betaling metode", "Betaling", list);
		return res;
	}

	private void deleteClicked() {
		OrderLine currOrderLine = listProducts.getSelectedValue();
		if (currOrderLine != null) {

			if (JOptionPane.showConfirmDialog(this,
					"Er du sikker på du vil fjerne \"" + currOrderLine.getProduct().getName() + "\"", "Slet",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				orderController.removeOrderLine(currOrderLine);
				refreshProductList();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Fejl, intet produkt valgt");

		}
	}

	private void ChangeAmountClicked() {
		OrderLine currItem = listProducts.getSelectedValue();
		if (currItem != null) {
			Integer nAmount = Input.getInputInteger("Nyt antal af product"); // retunere null ved cancel eller input der
																				// ikke er et tal
			if (nAmount != null) {

				if (nAmount > 0) {
					currItem.setAmount(nAmount);
					refreshProductList();
				} else {
					JOptionPane.showMessageDialog(this, "Fejl, antal skal være over \"0\"");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Fejl, intet produkt valgt");
		}
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
			String text = null;

			if (person instanceof PrivateCustomer) {
				text = String.format("Navn: %s - (%s)\ntlf.: %s\nEmail: %s\nAdresse: %s", person.getName(),
						((PrivateCustomer) person).getCPR(), person.getPhoneNr(), person.getEmail(),
						person.getAddress());
			} else {
				text = String.format("Navn: %s - (%s)\ntlf.: %s\nEmail: %s\nAdresse: %s\nKontaktperson: %s - (%s)",
						person.getName(), ((BuisnessCustomer) person).getCVRNumber(), person.getPhoneNr(),
						person.getEmail(), ((BuisnessCustomer) person).getAddress(),
						((BuisnessCustomer) person).getContact(), ((BuisnessCustomer) person).getContactPhone());
			}

			txtaCustomer.setText(text);
			orderController.attachCustomer(person.getPhoneNr());
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

	// Fix this later
	private void customerSearch() {
		String search = txtfCustomer.getText();
		List<Person> currList = orderController.findCustomers(search);
		Person defaultCustomer = orderController.findCustomers("0000").get(0);
		currList.remove(defaultCustomer);
		cModel.clear();
		currList.add(0, defaultCustomer);
		cModel.addAll(currList);
	}

	private void editpriceClicked() {
		OrderLine currItem = listProducts.getSelectedValue();
		if (currItem != null) {
			Double nPrice = Input.getInputDouble("nye pris: "); // retunere null ved cancel eller input der ikke er et
																// tal
			if (nPrice != null && currItem != null) {
				currItem.editPrice(nPrice);
				refreshProductList();
			}
		} else {
			JOptionPane.showMessageDialog(this, "fejl, intet produkt valgt");
		}
	}

	private void productSearch() {
		String search = txtfProduct.getText();
		orderController.scanProduct(search);
		refreshProductList();
	}

	private void resetScreen() {
		txtfCustomer.setText("");
		txtfProduct.setText("");
		orderController.createOrder();
		cModel.clear();
		pModel.clear();
		txtaCustomer.setText("");
		customerSearch();
	}

}
