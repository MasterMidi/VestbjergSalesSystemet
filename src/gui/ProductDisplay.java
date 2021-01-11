package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import gui.components.JDateTextField;
import model.product.Product;
import model.product.SellableProduct;

public class ProductDisplay extends JDialog {

	private JPanel contentPane;
	private JTextField txtfName;
	private JTextField txtfBarcode;
	private JTextField txtfDescription;
	private JLabel lblAmount;
	private JTextField txtfAmount;
	private JPanel pButtons;
	private JButton btnCancel;
	private JButton btnCreate;
	private Product product;
	private String productBarcode;
	private ProductController productController;
	private JTextField txtfPrice;
	private JLabel lblPriceDate;
//	private JTextField txtfPriceDate;
	private JDateTextField txtfPriceDate;
	private JPanel pInput;

	/**
	 * Create the frame.
	 */
	public ProductDisplay(Product inputProduct) {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pInput = new JPanel();
		contentPane.add(pInput, BorderLayout.CENTER);
		GridBagLayout gbl_pInput = new GridBagLayout();
		gbl_pInput.columnWidths = new int[] { 0, 92, 0, 0 };
		gbl_pInput.rowHeights = new int[] { 0, 0, 0, 0, 29, 0, 0, 0 };
		gbl_pInput.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pInput.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pInput.setLayout(gbl_pInput);

		JLabel lblName = new JLabel("Navn:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		pInput.add(lblName, gbc_lblName);

		txtfName = new JTextField();
		GridBagConstraints gbc_txtfName = new GridBagConstraints();
		gbc_txtfName.insets = new Insets(0, 0, 5, 0);
		gbc_txtfName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfName.gridx = 2;
		gbc_txtfName.gridy = 1;
		pInput.add(txtfName, gbc_txtfName);
		txtfName.setColumns(10);

		JLabel lblBarcode = new JLabel("Stregkode:");
		lblBarcode.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblBarcode = new GridBagConstraints();
		gbc_lblBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblBarcode.anchor = GridBagConstraints.EAST;
		gbc_lblBarcode.gridx = 1;
		gbc_lblBarcode.gridy = 2;
		pInput.add(lblBarcode, gbc_lblBarcode);

		txtfBarcode = new JTextField();
		GridBagConstraints gbc_txtfBarcode = new GridBagConstraints();
		gbc_txtfBarcode.insets = new Insets(0, 0, 5, 0);
		gbc_txtfBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfBarcode.gridx = 2;
		gbc_txtfBarcode.gridy = 2;
		pInput.add(txtfBarcode, gbc_txtfBarcode);
		txtfBarcode.setColumns(10);

		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 3;
		pInput.add(lblDescription, gbc_lblDescription);

		txtfDescription = new JTextField();
		GridBagConstraints gbc_txtfDescription = new GridBagConstraints();
		gbc_txtfDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtfDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfDescription.gridx = 2;
		gbc_txtfDescription.gridy = 3;
		pInput.add(txtfDescription, gbc_txtfDescription);
		txtfDescription.setColumns(10);

		JLabel lblPrice = new JLabel("Pris:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 4;
		pInput.add(lblPrice, gbc_lblPrice);

		txtfPrice = new JTextField();
		GridBagConstraints gbc_txtfPrice = new GridBagConstraints();
		gbc_txtfPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtfPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfPrice.gridx = 2;
		gbc_txtfPrice.gridy = 4;
		pInput.add(txtfPrice, gbc_txtfPrice);
		txtfPrice.setColumns(10);

		lblAmount = new JLabel("Antal:");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.EAST;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 1;
		gbc_lblAmount.gridy = 5;
		pInput.add(lblAmount, gbc_lblAmount);

		txtfAmount = new JTextField();
		GridBagConstraints gbc_txtfAmount = new GridBagConstraints();
		gbc_txtfAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtfAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfAmount.gridx = 2;
		gbc_txtfAmount.gridy = 5;
		pInput.add(txtfAmount, gbc_txtfAmount);
		txtfAmount.setColumns(10);

		lblPriceDate = new JLabel("Pris Start Dato:");
		GridBagConstraints gbc_lblPriceDate = new GridBagConstraints();
		gbc_lblPriceDate.anchor = GridBagConstraints.LINE_END;
		gbc_lblPriceDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblPriceDate.gridx = 1;
		gbc_lblPriceDate.gridy = 6;
		pInput.add(lblPriceDate, gbc_lblPriceDate);

//		txtfPriceDate = new JTextField();
//		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
//				.withLocale(Locale.forLanguageTag("dk"));
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		txtfPriceDate = new JDateTextField(dateFormatter);
//		txtfPriceDate.setValue(LocalDate.now(ZoneId.systemDefault()));
		GridBagConstraints gbc_txtfPriceDate = new GridBagConstraints();
		gbc_txtfPriceDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfPriceDate.gridx = 2;
		gbc_txtfPriceDate.gridy = 6;
		pInput.add(txtfPriceDate, gbc_txtfPriceDate);
		txtfPriceDate.setColumns(10);

		pButtons = new JPanel();
		FlowLayout fl_pButtons = (FlowLayout) pButtons.getLayout();
		fl_pButtons.setAlignment(FlowLayout.RIGHT);
		contentPane.add(pButtons, BorderLayout.SOUTH);

		btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		pButtons.add(btnCancel);

		String buttonTxt = inputProduct == null ? "Opret" : "Opdater";
		btnCreate = new JButton(buttonTxt);
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveClicked();
			}

		});
		pButtons.add(btnCreate);
		product = inputProduct;
		init();
	}

	private void init() {
		if (product != null) {
			productBarcode = product.getBarcode();
			fillTextFields(product);
		}
		productController = new ProductController();
	}

	private void updateProduct() {

		productController.updateProduct(productBarcode, txtfName.getText(), txtfBarcode.getText(),
				txtfDescription.getText(), Double.parseDouble(txtfPrice.getText()), txtfPriceDate.getText(),
				Integer.parseInt(txtfAmount.getText()));
	}

	private void createProduct() {
		productController.createSellableProduct(txtfName.getText(), txtfBarcode.getText(), txtfDescription.getText(),
				Double.parseDouble(txtfPrice.getText()), Integer.parseInt(txtfAmount.getText()));

	}

	private void fillTextFields(Product inputProduct) {
		txtfName.setText(inputProduct.getName());
		txtfBarcode.setText(inputProduct.getBarcode());
		txtfDescription.setText(inputProduct.getDescription());
		txtfPrice.setText(String.valueOf(inputProduct.getPrice()));
		txtfAmount.setText(String.valueOf(((SellableProduct) inputProduct).getStock()));
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		txtfPriceDate.setText(format
				.format(((SellableProduct) inputProduct).getPriceObj(Calendar.getInstance().getTime()).getStartDate()));
	}

	private void saveClicked() {
		if (validateTxtFields()) {
			if (product == null) {
				createProduct();

			} else {
				updateProduct();
			}
			setVisible(false);
			dispose();
		}
	}

	public Product getProduct() {
		return product;
	}

	private boolean validateTxtFields() {
		boolean res = false;
		String errors = "";
		if (txtfName.getText().isBlank()) {
			errors += "Fejl: produkt skal have et navn \n";
		}
		if (txtfBarcode.getText().isBlank()) {
			errors += "Fejl: produkt skal have en stregkode \n";
		}
		if (txtfDescription.getText().length() < 50) {
			errors += "Fejl: produkt skal have en beskrivelse over 50 tegn \n";
		}
		if (!txtfPrice.getText().isBlank() && txtfPrice.getText().trim().matches("[^\\d]+")) {
			errors += "Fejl: pris skal være tomt eller et tal\n";
		}
//		if (!txtfPrice.getText().isBlank() && // checks if date is DD-MM-YYYY
//				!txtfPriceDate.getText()
//						.matches("^(0[1-9]|[12][0-9]|3[01])[- \\/.](0[1-9]|1[012])[- \\/.](19|20|21)\\d\\d$")) {
//			errors += "Fejl: pris skal have en gyldig start dato [DD-MM-YYYY]\n";
//		}
		if (txtfAmount.getText().isEmpty() && txtfAmount.getText().trim().matches("[^\\d]+")) {
			errors += "Fejl: antal skal være 0 eller større";
		}

		if (!errors.isBlank()) {
			JOptionPane.showMessageDialog(this, errors);
		} else {
			res = true;
		}
		return res;
	}
}
