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
import javax.swing.JCheckBox;
import java.awt.GridLayout;

public class pProducts extends JPanel {
	private ProductController productController;
	private ProductTableModel tableModel;

	private JHintTextField txtfBarcode;
	private JTable tblProducts;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JCheckBox chckbxShowInactive;
	private boolean showInactiveProducts;
	private JPanel pButtons;
	private JPanel pFilter;

	/**
	 * Create the panel.
	 */
	public pProducts() {
		setLayout(new BorderLayout(0, 0));

		JPanel pActions = new JPanel();
		add(pActions, BorderLayout.SOUTH);
		pActions.setLayout(new GridLayout(0, 2, 0, 0));

		pFilter = new JPanel();
		FlowLayout fl_pFilter = (FlowLayout) pFilter.getLayout();
		fl_pFilter.setAlignment(FlowLayout.LEFT);
		pActions.add(pFilter);

		chckbxShowInactive = new JCheckBox("Vis inaktive");
		pFilter.add(chckbxShowInactive);
		chckbxShowInactive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshProductTable();
			}
		});

		pButtons = new JPanel();
		FlowLayout fl_pButtons = (FlowLayout) pButtons.getLayout();
		fl_pButtons.setAlignment(FlowLayout.RIGHT);
		pActions.add(pButtons);

		btnCreate = new JButton("Opret...");
		pButtons.add(btnCreate);

		btnDelete = new JButton("Slet");
		pButtons.add(btnDelete);

		btnUpdate = new JButton("Opdater");
		pButtons.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateClicked();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClicked();
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createClicked();
			}
		});

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

		txtfBarcode = new JHintTextField();
		txtfBarcode.setHint("Stregkode / Navn...");
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
//		Product selectedProduct = tableModel.getDataAtIndex(tblProducts.getSelectedRow());
		
		int[] selectedProductIndecies = tblProducts.getSelectedRows();
		Product[] selectedProducts = new Product[selectedProductIndecies.length];
		for(int i = 0; i < selectedProducts.length; i++) {
			selectedProducts[i] = tableModel.getDataAtIndex(selectedProductIndecies[i]);
		}
		
		if (selectedProducts.length > 0) {
			for(int i = 0; i < selectedProducts.length; i++) {
				productController.setProductStatus(selectedProducts[i].getBarcode(), ProductStatus.Inactive);
			}
		}
		
		refreshProductTable();
	}

	protected void updateClicked() {
		Product selectedProduct = tableModel.getDataAtIndex(tblProducts.getSelectedRow());
		if (selectedProduct != null) {
			ProductDisplay productDisplay = new ProductDisplay(selectedProduct);
			productDisplay.setModal(true);
			productDisplay.setVisible(true);
		}
		refreshProductTable();
	}

	private void createClicked() {
		ProductDisplay productDisplay = new ProductDisplay(null);
		productDisplay.setModal(true);
		productDisplay.setVisible(true);
		refreshProductTable();
	}

	private void init() {
		productController = new ProductController();
		tableModel = new ProductTableModel();
		tblProducts.setModel(tableModel);

		refreshProductTable();
	}

	private void refreshProductTable() {
		List<Product> lists = productController.getProducts(txtfBarcode.getText(), chckbxShowInactive.isSelected());
//		Collections.sort(lists, new Comparator<Product>() {
//			@Override
//			public int compare(Product o1, Product o2) {
//				return o1.getName().compareTo(o1.getName());
//			}
//		});
		tableModel.setData(lists);
	}

}
