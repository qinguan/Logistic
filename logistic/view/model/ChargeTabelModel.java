package view.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import classes.relations.Charge;
import db.relations.DBCharge;

public class ChargeTabelModel extends AbstractTableModel implements Observer {

	
	private String columnNames[]={"groupId","workerId","activityId","chargeTime"};
	private ArrayList<Charge> charges = DBCharge.readCharge();
	
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		int index = charges.indexOf(o);
		fireTableRowsUpdated(index,index);
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return charges.size();
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
			return charges.get(row).getGroupId();
		case 1:
			return charges.get(row).getWorkerId();
		case 2:
//			System.out.println(charges.get(row).getGroupId());
			return charges.get(row).getActivityId();
		case 3:
			return charges.get(row).getChargeTime();

		default:
			return null;
		}
	}
	
	/**
	 * ≤‚ ‘∂¡»° ˝æ›
	 */
	public static void main(String[] args) {
		ChargeTabelModel chargeTableModel =  new ChargeTabelModel();
		System.out.println(chargeTableModel.getValueAt(2, 0));
		System.out.println(chargeTableModel.getValueAt(2, 1));
		System.out.println(chargeTableModel.getValueAt(2, 2));
		System.out.println(chargeTableModel.getValueAt(2, 3));
	}

}
