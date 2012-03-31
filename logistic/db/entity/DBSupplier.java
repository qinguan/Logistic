package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import classes.entity.Material;
import classes.entity.Supplier;

public class DBSupplier extends DBConnection {

	/**
	 *读取所有供应商信息
	 * @return
	 */
	public static ArrayList<Supplier> readSupplier(){
		
		try {
			connect();
			String read = "select * from supplier ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
			
			while(rs.next()){				
				suppliers.add(new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			return suppliers;
		} catch (Exception e) {
			System.out.println("error in readMaterial.");
			return null;
		}
	}
	
	/**
	 * 读取单个供应商信息
	 * @param supplierId
	 * @return
	 */
	public static Supplier readSupplierSingle(int supplierId){
		try {
			connect();
			String read = "select * from supplier where supplierId="+supplierId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			Supplier supplier ;
			if(rs.next()){
				supplier = new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return supplier;
			}
			else {
				return null;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error in readSupplierSingle.");
			return null;
		}
	}
	
	/**
	 * 插入单个供应商信息
	 * @param supplier
	 * @return
	 */
	public static boolean supplierInsert(Supplier supplier){
		try {
			connect();
			String insert = "insert into supplier values(?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, supplier.getSupplierId());
			psmt.setString(2, supplier.getOriganization());
			psmt.setString(3, supplier.getAddress());
			psmt.setString(4, supplier.getContactTel());
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in supplierInsert.");
			return false;
		}
	}
	
	/**
	 * 删除单个供应商信息
	 * @param supplierId
	 * @return
	 */
	public static boolean supplierDel(int supplierId){
		try {
			connect();
			String del = "delete from supplier where supplierId=?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, supplierId);
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in supplierDel2.");
			return false;
		}
	}
	
	/**
	 * 供应商信息更新
	 * @param supplier
	 * @return
	 */
	public static boolean supplierUpdate(Supplier supplier){
		try {
			if(supplierDel(supplier.getSupplierId())){
				supplierInsert(supplier);
				return true;
			}
			else {
				System.out.println("error in supplierUpdate.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("error in supplierUpdate2.");
			return false;
		}
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Supplier supplier = new Supplier(301,"beihang","xueyuanlu37hao","82330735");
		System.out.println(supplierDel(301));
		System.out.println(supplierInsert(supplier));
		System.out.println(readSupplierSingle(301));
	}
}
