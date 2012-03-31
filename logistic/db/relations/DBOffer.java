package db.relations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import classes.relations.Offer;

import db.entity.DBConnection;

public class DBOffer extends DBConnection{
	
	
	/**
	 * 读取所有提供关系
	 * @return
	 */
	public static ArrayList<Offer> readOffer(){	
		try {
			String read = "select * from offer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			ArrayList<Offer> offers= new ArrayList<Offer>();
			while(rs.next()){
				offers.add(new Offer(rs.getInt(1),rs.getInt(2),
						rs.getInt(3)));
			}
			rs.close();
			stmt.close();
			
			return offers;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 读取单个提供关系
	 * @param supplierId
	 * @param materialId
	 * @return
	 */
	public static Offer readSingle(int supplierId,int materialId){
		
		try {
			connect();
			String read = "select * from offer where supplierId = "+supplierId
							+" and materialId = "+materialId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			Offer offer ;
			if(rs.next()){
				offer = new Offer(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}
			else {
				return null;
			}
			return offer;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/*
	 * 插入提供关系
	 */
	public static boolean offerInsert (Offer offer){
		try{
			connect();
			String insert ="insert into offer values(?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert); 
			psmt.setInt(1, offer.getSupplierId());
			psmt.setInt(2, offer.getMaterialId());
			psmt.setInt(3, offer.getOfferNum());
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
		}catch ( Exception e){
			System.out.println("error in offerInsert.");
			return false;
			
		}
	}
	/*
	 * 删除提供关系
	 */
	public static boolean offerDel(int supplierId , int materialId){
		try{
			connect();
			String del= "delete from offer where supplierId = ? and materialId = ? ";
			PreparedStatement psmt = con.prepareStatement (del);
			
			psmt.setInt(1, supplierId);
			psmt.setInt(2, materialId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		}catch(Exception e){
			System.out.println("error in offerDelete.");
			return false;
		}
	}
	
	/*
	 * 更新提供关系
	 */
	public static boolean offerUpdate(Offer offer){
		try{
			connect();
			if(offerDel(offer.getSupplierId(),offer.getMaterialId())){
				offerInsert(offer);
				return true; 
			}
			else{
				System.out.println("error in offerUpdate Excep.");
				return false;
			}
				
		}catch(Exception e){
			System.out.println("error in offerUpdate.");
			return false;
		}
	}
	public static void main(String[] args){
		Offer offer = new Offer(122,133,139);
//		System.out.println(offerInsert(offer));
//		System.out.println(offerDel(122,123));
		System.out.println(offerUpdate(offer));
		System.out.println(readOffer());

	}
	
}

