package gui.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ProductController;
import gui.components.ProductTableModel;
import model.product.Product;

public class pProducts extends JPanel {
	private ProductController productController;
	private ProductTableModel tableModel;

	private JTextField txtfBarcode;
	private JTable tblProducts;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnFind;

	/**
	 * Create the panel.
	 */
	public pProducts() {
		setLayout(new BorderLayout(0, 0));

		JPanel pButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(pButtons, BorderLayout.SOUTH);

		btnCreate = new JButton("Opret...");
		pButtons.add(btnCreate);

		btnDelete = new JButton("Slet");
		pButtons.add(btnDelete);

		btnUpdate = new JButton("Opdater");
		pButtons.add(btnUpdate);

		btnFind = new JButton("Søg...");
		pButtons.add(btnFind);

		JPanel pProductView = new JPanel();
		add(pProductView, BorderLayout.CENTER);
		pProductView.setLayout(new BorderLayout(0, 0));

		txtfBarcode = new JTextField();
		pProductView.add(txtfBarcode, BorderLayout.SOUTH);
		txtfBarcode.setColumns(10);

		JScrollPane scrlProducts = new JScrollPane();
		pProductView.add(scrlProducts, BorderLayout.CENTER);

		tblProducts = new JTable();
		scrlProducts.setViewportView(tblProducts);
		tblProducts.getTableHeader().setReorderingAllowed(false);

		init();
	}

	private void init() {
		productController = new ProductController();
		tableModel = new ProductTableModel();
		tblProducts.setModel(tableModel);
		refreshProductTable();
	}

	private void refreshProductTable() {
		List<Product> lists = productController.getProducts(txtfBarcode.getText());
		tableModel.setData(lists);
	}

}
