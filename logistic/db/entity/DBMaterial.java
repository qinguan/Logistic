package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import classes.entity.Activity;
import classes.entity.Material;

public class DBMaterial extends DBConnection{

	/**
	 * ��ȡ����������Ϣ
	 * @return
	 */
	public static ArrayList<Material> readMaterial(){
		
		try {
			connect();
			String read = "select * from material ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			ArrayList<Material> materials = new ArrayList<Material>();
			
			while(rs.next()){				
				materials.add(new Material(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
			return materials;
		} catch (Exception e) {
			System.out.println("error in readMaterial.");
			return null;
		}
	}
	
	/**
	 * ��ȡָ�����ʵ���Ϣ
	 * @param materialId
	 * @return
	 */
	public static Material readMaterialSingle(int materialId){
		
		try {
			connect();
			String read = "select * from material where materialId = " + materialId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			rs.next();
			Material material = new Material(rs.getInt(1),rs.getString(2),rs.getString(3));
			
			return material;
			
		} catch (Exception e) {
			System.out.println("error in readMaterialSingle.");
			return null;
		}
	}
	
	/**
	 * ��ȡָ���ֿ�ָ��������Ϣ
	 * @param materialId
	 * @param stockId
	 * @return
	 */
	public static Material readMaterial_Stock(int materialId,int stockId){
		
		try {
			connect();
			String read = "select * from material where materialId="+materialId
							+"and stockId=" + stockId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			rs.next();
			Material material = new Material(rs.getInt(1),rs.getString(2),rs.getString(3));
			
			return material;
		} catch (Exception e) {
			System.out.println("error in readMaterialSingle.");
			return null;
		}
	}
	/**
	 * ����������Ϣ
	 * @param material
	 * @return
	 */
	public static boolean materialInsert(Material material){
		
		try {
			connect();
			String insert = "insert into material values(?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, material.getMaterialId());
			psmt.setString(2, material.getMaterialName());
			psmt.setString(3, material.getMaterialUsed());
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in materialInsert.");
			return false;
		}
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param materialId
	 * @return
	 */
	public static boolean materialDel(int materialId){
		
		try {
			connect();
			String del = "delete from material where materialId=?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, materialId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
		} catch (Exception e) {
			System.out.println("error in materialDel2.");
			return false;
		}
	}
	
	/**
	 * ������Ϣ����
	 * @param material
	 * @return
	 */
	public static boolean materialUpdate(Material material){
		
		try {
			if(materialDel(material.getMaterialId())){
				materialInsert(material);
				return true;
			}
			else {
				System.out.println("error in materialUpdate.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("error in materialUpdate2.");
			return false;
		}
	}
	
	
	/**
	 * ��������
	 * @param args
	 */
	public static void main(String[] args) {
		Material material = new Material(403,"shoes","ware");
		System.out.println(materialInsert(material));
		System.out.println(materialUpdate(material));
	}
}
