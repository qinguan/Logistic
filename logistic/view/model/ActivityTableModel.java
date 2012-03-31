package view.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import classes.entity.Activity;
import db.entity.DBActivity;

public class ActivityTableModel extends AbstractTableModel implements Observer{

	private String columnNames[]={"activityId","activityName","activityPlace",
			"activityTime","activityContact","activityPersonNum"};
	private ArrayList<Activity> activitys = DBActivity.readActivity();

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return activitys.size();
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
			return activitys.get(row).getActivityId();
		case 1:
			return activitys.get(row).getActivityName();
		case 2:
			return activitys.get(row).getActivityPlace();
		case 3:
			return activitys.get(row).getActivityTime();
		case 4:
			return activitys.get(row).getActivityContact();
		case 5:
			return activitys.get(row).getActivityPersonNum();
		default:
			return null;
		}
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		int index = activitys.indexOf(o);
		fireTableRowsUpdated(index,index);
	}
	
	/**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	ActivityTableModel activityTableModel = new ActivityTableModel();
    	String string = (String) activityTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}

	

}
