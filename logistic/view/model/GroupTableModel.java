package view.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


import classes.entity.Group;
import db.entity.DBGroup;

public class GroupTableModel extends AbstractTableModel {

	private String columnNames[]={"groupId","groupName","groupDuty","office","workTime"};
	private ArrayList<Group> groups = DBGroup.readGroup();

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	public String getColumnName(int col){
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch (col) {
		case 0:
			return groups.get(row).getGroupId();
		case 1:
			return groups.get(row).getGroupName();
		case 2:
			return groups.get(row).getGroupDuty();
		case 3:
			return groups.get(row).getOffice();
		case 4:
			return groups.get(row).getWorkTime();

		default:
			return null;
		}
	}
	
	public Class getColumnClass(int c) {
	    return getValueAt(0, c).getClass();
	}

	 /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    /*
    public void setValueAt(Object value, int row, int col) {
    	switch (col) {
		case 0:
			groups.get(row).setGroupId(value);
		case 1:
			groups.get(row).setGroupName(value);
		case 2:
			groups.get(row).setGroupDuty(value);
		case 3:
			groups.get(row).setOffice(value);
		case 4:
			groups.get(row).setWorkTime(value);

		default:
			break;
		}
        fireTableCellUpdated(row, col);
    }
    */


    /**
     * ²âÊÔ¶ÁÈ¡Êý¾Ý
     */
    public static void main(String[] args) {
    	GroupTableModel groupTableModel = new GroupTableModel();
    	String string = (String) groupTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}
}
