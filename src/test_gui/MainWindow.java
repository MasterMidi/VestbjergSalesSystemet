package test_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable tblProducts;
	private JTable tblProducts_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1035, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 192, 534, 274);
		contentPane.add(scrollPane);

		tblProducts = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(new String[3][3], new String[] { "amount", "Description", "Price" });
		
		tblProducts_1 = new JTable(tableModel);
//		tblProducts_1.setSelectionModel(null);

//		tblProducts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = tblProducts_1.getColumnModel();
		columnModel.getColumn(0).setMinWidth(50);
		columnModel.getColumn(0).setMaxWidth(80);
		columnModel.getColumn(2).setMinWidth(50);
		columnModel.getColumn(2).setMaxWidth(80);
		scrollPane.setViewportView(tblProducts_1);
		scrollPane.setViewportView(tblProducts_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 150, 232, 316);
		contentPane.add(scrollPane_1);

		JList listCustomer = new JList();
		scrollPane_1.setViewportView(listCustomer);
		
		textField = new JTextField();
		textField.setBounds(311, 149, 534, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	private void deleteRow() {
		int[] index = tblProducts_1.getSelectedRows();
		if (index.length > 0) {
			DefaultTableModel tableModel = (DefaultTableModel) tblProducts_1.getModel();

			for (int i = index.length - 1; i >= 0; i--) {
				tableModel.removeRow(index[i]);

			}
		}
	}

	private void addRow() {
		DefaultTableModel tableModel = (DefaultTableModel) tblProducts_1.getModel();

		String[] data = { "Hey", "There", "Magnus" };
		tableModel.addRow(data);
	}
}
