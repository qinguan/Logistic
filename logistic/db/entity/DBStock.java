package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import classes.entity.Stock;

public class DBStock extends DBConnection{
	
	/**
	 * 读取所有仓库信息
	 * @return
	 */
	public static ArrayList<Stock> readStock(){
		try {
			connect();
			String read = "select * from stock ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			ArrayList<Stock > stocks = new ArrayList<Stock>();
			
			while(rs.next()){
				stocks.add(new Stock(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4)));
			}
			
			return stocks;
			
		} catch (Exception e) {
			System.out.println("error in readStock.");
			return null;
		}
	}
	
	/**
	 * 读取单个仓库信息
	 * @param stockId
	 * @return
	 */
	public static Stock readStockSingle(int stockId){
		try {
			connect();
			String read = "select * from stock where stockId="+stockId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			Stock stock=null;
			rs.next();

			stock = new Stock(rs.getInt(1),rs.getString(2),
					rs.getString(3),rs.getString(4));
			
			return stock;
		} catch (Exception e) {
			System.out.println("error in readStockSingle.");
			return null;
		}
	}
	
	/**
	 * 插入新的仓库信息
	 * @param stock
	 * @return
	 */
	public static boolean stockInsert(Stock stock){
		
		try {
			connect();
			String insert = "insert into stock values(?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, stock.getStockId());
			psmt.setString(2, stock.getStockPlace());
			psmt.setString(3, stock.getStockTel());
			psmt.setString(4, stock.getStockArea());
			
			psmt.executeUpdate();
			psmt.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("error in stockInsert.");
			return false;
		}
	}
	
	/**
	 * 删除仓库信息
	 * @param stockId
	 * @return
	 */
	public static boolean stockDel(int stockId){
		
		try {
			connect();
			String del = "delete from stock where stockId=?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, stockId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in stockDe2.");
			return false;
		}
	}
	
	/**
	 * 仓库信息更新
	 * @param stock
	 * @return
	 */
	
	public static boolean stockUpdate(Stock stock){
		
		try {
			if(stockDel(stock.getStockId())){
				stockInsert(stock);
				return true;
			}
			else {
				System.out.println("error in stockUpdate.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("error in stockUpdate2.");
			return false;
		}
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
//		Stock stock = new Stock(112,"beiqu","82330733","200m*100m");
//		System.out.println(stockInsert(stock));
		System.out.println(readStockSingle(111));
	}
}
