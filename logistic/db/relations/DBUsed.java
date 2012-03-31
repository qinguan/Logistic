package db.relations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import classes.relations.Used;

import db.entity.DBConnection;

public class DBUsed extends DBConnection{
	
	
	/**
	 * 读取所有使用关系
	 * @return
	 */
	public static ArrayList<Used> readUsed(){	
		try {
			connect();
			String read = "select * from used";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);			
			ArrayList<Used> useds= new ArrayList<Used>();
			
			while(rs.next()){
				useds.add(new Used(rs.getInt(1),rs.getInt(2),
						rs.getInt(3)));
			}
			rs.close();
			stmt.close();
			
			return useds;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 读取单个使用关系
	 * @param workerId
	 * @param materialId
	 * @return
	 */
	public static Used readSingle(int workerId,int materialId){
		
		try {
			connect();
			String read = "select * from used where workerId = "+workerId
							+" and materialId = "+materialId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			Used used ;
			if(rs.next()){
				used = new Used(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}
			else {
				return null;
			}
			return used;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/*
	 * 插入使用关系
	 */
	public static boolean usedInsert (Used used){
		try{
			connect();
			String insert ="insert into used values(?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert); 
			
			psmt.setInt(1, used.getWorkerId());
			psmt.setInt(2, used.getMaterialId());
			psmt.setInt(3, used.getMaterialUsedNum());
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
		}catch ( Exception e){
			System.out.println("error in usedInsert.");
			return false;
			
		}
	}
	/*
	 * 删除使用关系
	 */
	public static boolean usedDel(int workerId , int materialId){
		try{
			connect();
			String del= "delete from used where workerId=? and materialId = ? ";
			PreparedStatement psmt = con.prepareStatement (del);
			
			psmt.setInt(1, workerId);
			psmt.setInt(2, materialId);
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		}catch(Exception e){
			System.out.println("error in usedDelete.");
			return false;
		}
	}
	
	/*
	 * 更新使用关系
	 */
	public static boolean usedUpdate(Used used){
		try{
			connect();
			if(usedDel(used.getWorkerId(),used.getMaterialId())){
				usedInsert(used);
				return true ; 
			}
			else{
				System.out.println("error in usedUpdate Excep.");
				return false;
			}
				
		}catch(Exception e){
			System.out.println("error in usedUpdate.");
			return false;
		}
	}
	
	public static void main(String[] args){
		Used used = new Used(122,133,145);
//		System.out.println(usedInsert(used));
//		System.out.println(usedDel(122,133));
		System.out.println(usedUpdate(used));
//		System.out.println(readUsed());

	}
	
}
