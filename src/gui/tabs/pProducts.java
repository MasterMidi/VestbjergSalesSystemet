package gui.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.ProductController;
import gui.components.JHintTextField;
import gui.components.ProductTableModel;
import model.product.Product;

public class pProducts extends JPanel {
	private ProductController productController;
	private ProductTableModel tableModel;

	private JHintTextField txtfBarcode;
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
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pButtons.add(btnCreate);

		btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pButtons.add(btnDelete);

		btnUpdate = new JButton("Opdater");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
//		tblProducts.setAutoCreateRowSorter(true);
		
		init();
	}

	private void init() {
		productController = new ProductController();
		tableModel = new ProductTableModel();
		tblProducts.setModel(tableModel);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblProducts.getModel());
		tblProducts.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 1;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setComparator(columnIndexToSort, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
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
