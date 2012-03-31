package view.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import classes.entity.Worker;
import db.entity.DBWorker;

public class WorkerTableModel extends AbstractTableModel {

	private String columnNames[]={"workerId","workerName","workerTel","workerSex"};
	private ArrayList<Worker> worker = DBWorker.readWorker();

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return worker.size();
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
			return worker.get(row).getWorkerId();
		case 1:
			return worker.get(row).getWorkerName();
		case 2:
			return worker.get(row).getWorkerTel();
		case 3:
			return worker.get(row).getWorkerSex();
		default:
			return null;
		}
	}
	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	WorkerTableModel workerTableModel = new WorkerTableModel();
    	String string = (String) workerTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}

}
