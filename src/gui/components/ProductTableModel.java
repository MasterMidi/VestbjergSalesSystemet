package gui.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.product.Product;
import model.product.SellableProduct;

public class ProductTableModel extends AbstractTableModel {
	private static final String[] COL_NAMES = { "Navn", "Stregkode", "Pris", "Lagerbeholdning", "Lager status",
			"Product status" };
	private List<Product> data;

	public ProductTableModel() {
		data = new ArrayList<>();
	}

	public void setData(List<Product> data) {
		this.data.clear();
		this.data.addAll(data);
		super.fireTableDataChanged();
	}

	public Product getDataAtIndex(int index) {
		Product res = null;
		if (index >= 0 && index < data.size()) {
			res = data.get(index);
		}
		return res;
	}

	@Override
	public int getRowCount() {
		int res = 0;
		if (data != null) {
			res = data.size();
		}
		return res;
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Product product = data.get(row);
		String res = "UNDEFINED";
		switch (column) {
		case 0: {
			res = product.getName();
			break;
		}
		case 1:
			res = product.getBarcode();
			break;
		case 2:
			res = String.valueOf(product.getPrice());
			break;
		case 3:
			res = String.valueOf(((SellableProduct) product).getStock());
			break;
		case 4:
			res = ((SellableProduct) product).getStatus().toString();
			break;
		case 5:
			res = "In store";
			break;
		default:
			res = "UNKNOWN COL - CONTACT ADMIN";
			break;
		}
		return res;
	}
}
