package view.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import classes.relations.Charge;
import classes.relations.Deposit;
import db.relations.DBCharge;
import db.relations.DBDeposit;

public class DepositTableModel extends AbstractTableModel implements Observer {

	private String columnNames[]={"materialId","stockId","depositNum"};
	private ArrayList<Deposit> deposits = DBDeposit.readDeposit();
	
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		int index = deposits.indexOf(o);
		fireTableRowsUpdated(index,index);
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return deposits.size();
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
			return deposits.get(row).getMaterialId();
		case 1:
			return deposits.get(row).getStockId();
		case 2:
			return deposits.get(row).getDepositNum();

		default:
			return null;
		}
	}
	
	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	DepositTableModel depositTableModel =  new DepositTableModel();
    	System.out.println(depositTableModel.getValueAt(1, 0));
    	System.out.println(depositTableModel.getValueAt(1, 1));
    	System.out.println(depositTableModel.getValueAt(1, 2));

	}


}
