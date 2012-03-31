package view.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import classes.entity.Stock;
import classes.entity.Supplier;
import db.entity.DBStock;
import db.entity.DBSupplier;

public class SupplierTableModel extends AbstractTableModel {

	private String columnNames[]={"supplierId","origanization","address","contactTel"};
	private ArrayList<Supplier> supplier = DBSupplier.readSupplier();

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return supplier.size();
	}

	public String getColumnName(int col){
		return columnNames[col];
	}
	
	public Class getColumnClass(int c) {
	    return getValueAt(0, c).getClass();
	}
	
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch (col) {
		case 0:
			return supplier.get(row).getSupplierId();
		case 1:
			return supplier.get(row).getOriganization();
		case 2:
			return supplier.get(row).getAddress();
		case 3:
			return supplier.get(row).getContactTel();
		default:
			return null;
		}
	}
	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	SupplierTableModel supplierTableModel = new SupplierTableModel();
    	String string = (String) supplierTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}

}
