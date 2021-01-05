package gui.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ProductController;
import gui.ProductDisplay;
import gui.components.JHintTextField;
import gui.components.ProductTableModel;
import model.product.Product;
import model.product.ProductStatus;

public class pProducts extends JPanel {
	private ProductController productController;
	private ProductTableModel tableModel;

	private JHintTextField txtfBarcode;
	private JTable tblProducts;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnUpdate;

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
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createClicked();
			}
		});
		pButtons.add(btnCreate);

		btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClicked();
			}
		});
		pButtons.add(btnDelete);

		btnUpdate = new JButton("Opdater");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateClicked();
			}
		});
		pButtons.add(btnUpdate);

//		btnFind = new JButton("Søg...");
//		btnFind.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				refreshProductTable();
//			}
//		});
//		pButtons.add(btnFind);

		JPanel pProductView = new JPanel();
		add(pProductView, BorderLayout.CENTER);
		pProductView.setLayout(new BorderLayout(0, 0));

		txtfBarcode = new JHintTextField("Stregkode / Navn...");
		txtfBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refreshProductTable();
			}
		});
		pProductView.add(txtfBarcode, BorderLayout.SOUTH);
		txtfBarcode.setColumns(10);

		JScrollPane scrlProducts = new JScrollPane();
		pProductView.add(scrlProducts, BorderLayout.CENTER);

		tblProducts = new JTable();
		scrlProducts.setViewportView(tblProducts);
		tblProducts.getTableHeader().setReorderingAllowed(false);
		tblProducts.setAutoCreateRowSorter(true);
		
		init();
	}

	private void deleteClicked() {
		Product selectedProduct = tableModel.getDataAtIndex(tblProducts.getSelectedRow());
		if (selectedProduct != null) {
			productController.setProductStatus(selectedProduct.getBarcode(), ProductStatus.Inactive);
		}
	}

	protected void updateClicked() {
		Product selectedProduct = tableModel.getDataAtIndex(tblProducts.getSelectedRow());
		if (selectedProduct != null) {
			ProductDisplay productDisplay = new ProductDisplay(selectedProduct);
			productDisplay.setModal(true);
			productDisplay.setVisible(true);
		}
	}

	private void createClicked() {
		ProductDisplay productDisplay = new ProductDisplay(null);
		productDisplay.setModal(true);
		productDisplay.setVisible(true);
	}

	private void init() {
		productController = new ProductController();
		tableModel = new ProductTableModel();
		tblProducts.setModel(tableModel);
		
		refreshProductTable();
	}

	private void refreshProductTable() {
		List<Product> lists = productController.getProducts(txtfBarcode.getText());
//		Collections.sort(lists, new Comparator<Product>() {
//			@Override
//			public int compare(Product o1, Product o2) {
//				return o1.getName().compareTo(o1.getName());
//			}
//		});
		tableModel.setData(lists);
	}

}
