package view.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import classes.relations.Offer;
import classes.relations.Used;
import db.relations.DBOffer;
import db.relations.DBUsed;

public class OfferTableModel extends AbstractTableModel implements Observer {

	private String columnNames[]={"supplierId","materialId","offerNum"};
	private ArrayList<Offer> offers = DBOffer.readOffer();
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return offers.size();
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
			return offers.get(row).getSupplierId();
		case 1:
			return offers.get(row).getMaterialId();
		case 2:
			return offers.get(row).getOfferNum();

		default:
			return null;
		}
	}

	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	OfferTableModel offerTableModel =  new OfferTableModel();
    	System.out.println(offerTableModel.getValueAt(0, 0));
    	System.out.println(offerTableModel.getValueAt(0, 1));
    	System.out.println(offerTableModel.getValueAt(0, 2));
    	

	}

}
