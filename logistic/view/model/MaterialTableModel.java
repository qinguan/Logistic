package view.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import classes.entity.Material;
import db.entity.DBMaterial;

public class MaterialTableModel extends AbstractTableModel {

	
	private String columnNames[]={"materialId","materialName","materialUsed"};
	private ArrayList<Material> materials = DBMaterial.readMaterial();
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return materials.size();
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
			return materials.get(row).getMaterialId();
		case 1:
			return materials.get(row).getMaterialName();
		case 2:
			return materials.get(row).getMaterialUsed();
		default:
			return null;
		}
	}
	  /**
     * ≤‚ ‘∂¡»° ˝æ›
     */
    public static void main(String[] args) {
    	MaterialTableModel materialTableModel =  new MaterialTableModel();
    	String string = (String) materialTableModel.getValueAt(0, 1);
    	System.out.println(string);

	}

}
