package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import classes.entity.Activity;
import classes.entity.Material;

public class DBMaterial extends DBConnection{

	/**
	 * 读取所有物质信息
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
	 * 读取指定物质的信息
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
	 * 读取指定仓库指定物质信息
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
	 * 插入物质信息
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
	 * 删除物质信息
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
	 * 物质信息更新
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
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Material material = new Material(403,"shoes","ware");
		System.out.println(materialInsert(material));
		System.out.println(materialUpdate(material));
	}
}
