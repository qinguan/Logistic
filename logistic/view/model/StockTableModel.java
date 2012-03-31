package view.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import classes.entity.Stock;
import db.entity.DBStock;

public class StockTableModel extends AbstractTableModel {

	private String columnNames[]={"stockId","stockPlace","stockTel","stockArea"};
	private ArrayList<Stock> stocks = DBStock.readStock();

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return stocks.size();
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
			return stocks.get(row).getStockId();
		case 1:
			return stocks.get(row).getStockPlace();
		case 2:
			return stocks.get(row).getStockTel();
		case 3:
			return stocks.get(row).getStockArea();
		default:
			return null;
		}
	}
	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	StockTableModel stockTableModel = new StockTableModel();
    	String string = (String) stockTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}


}
