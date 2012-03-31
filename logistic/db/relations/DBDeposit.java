package db.relations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import classes.relations.Deposit;

import db.entity.DBConnection;

public class DBDeposit extends DBConnection{
	
	
	/**
	 * 读取所有存储关系
	 * @return
	 */
	public static ArrayList<Deposit> readDeposit(){	
		try {
			connect();
			String read = "select * from deposit";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			ArrayList<Deposit> deposits= new ArrayList<Deposit>();
			while(rs.next()){
				deposits.add(new Deposit(rs.getInt(1),rs.getInt(2),
						rs.getInt(3)));
			}
			rs.close();
			stmt.close();
			
			return deposits;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 读取单个存储关系
	 * 
	 */
	public static Deposit readSingle(int materialId,int stockId){
		
		try {
			connect();
			String read = "select * from deposit where materialId = "+materialId
							+" and stockId = "+stockId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			Deposit deposit ;
			if(rs.next()){
				deposit = new Deposit(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}
			else {
				return null;
			}
			return deposit;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/*
	 * 插入存储关系
	 */
	public static boolean depositInsert (Deposit deposit){
		try{
			connect();
			String insert ="insert into deposit values(?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert); 
			psmt.setInt(1, deposit.getMaterialId());
			psmt.setInt(2, deposit.getStockId());
			psmt.setInt(3, deposit.getDepositNum());
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
		}catch ( Exception e){
			System.out.println("error in depositInsert.");
			return false;
			
		}
	}
	/*
	 * 删除存储关系
	 */
	public static boolean depositDel(int materialId , int stockId){
		try{
			connect();
			String del= "delete from deposit where materialId = ? and stockId=?";
			PreparedStatement psmt = con.prepareStatement (del);
			
			psmt.setInt(1, materialId);
			psmt.setInt(2, stockId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		}catch(Exception e){
			System.out.println("error in depositDelete.");
			return false;
		}
	}
	
	/*
	 * 更新存储关系
	 */
	public static boolean depositUpdate(Deposit deposit){
		try{
			connect();
			if(depositDel(deposit.getMaterialId(),deposit.getStockId())){
				depositInsert(deposit);
				return true;
			}
			else{
				System.out.println("error in depositUpdate Excep.");
				return false;
			}
				
		}catch(Exception e){
			System.out.println("error in depositUpdate.");
			return false;
		}
	}
	public static void main(String[] args){
//		Deposit deposit = new Deposit(112,113,114);
//		System.out.println(depositDel(112,113));
//		System.out.println(depositInsert(deposit));
//		System.out.println(depositUpdate(deposit));
//		Deposit deposit = readDeposit().get(1);
		

		Deposit deposit = readSingle(112, 113);
		System.out.println(deposit);
		System.out.println(deposit.getMaterialId());
		System.out.println(deposit.getStockId());
		System.out.println(deposit.getDepositNum());
		
	}
	
}
