package view.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import classes.relations.Used;
import db.relations.DBUsed;

public class UsedTableModel extends AbstractTableModel implements Observer {

	private String columnNames[]={"workerId","materialId","materialUsedNum"};
	private ArrayList<Used> useds = DBUsed.readUsed();
	
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		int index = useds.indexOf(o);
		fireTableRowsUpdated(index,index);
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return useds.size();
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
			return useds.get(row).getWorkerId();
		case 1:
			return useds.get(row).getMaterialId();
		case 2:
			return useds.get(row).getMaterialUsedNum();

		default:
			return null;
		}
	}

	/**
	 * ≤‚ ‘∂¡»° ˝æ›
	 */
	public static void main(String[] args) {
		UsedTableModel usedTableModel =  new UsedTableModel();
		System.out.println(usedTableModel.getValueAt(0, 0));
		System.out.println(usedTableModel.getValueAt(0, 1));
		System.out.println(usedTableModel.getValueAt(0, 2));
	}

}
