package db.relations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import classes.relations.Charge;

import db.entity.DBConnection;

/**
 *管理组、活动、工作人员之间的关系 
 * @author qinguan
 *
 */
public class DBCharge extends DBConnection{

	/**
	 * 读取charge表中所有负责关系
	 * @return
	 */
	public static ArrayList<Charge> readCharge(){
		try {
			connect();
			String read = "select * from charge";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			ArrayList<Charge> charges= new ArrayList<Charge>();
			while(rs.next()){
				charges.add(new Charge(rs.getInt(1),rs.getInt(2),
						rs.getInt(3),rs.getString(4)));
			}
			rs.close();
			stmt.close();
			
			return charges;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 读取表中指定负责关系
	 * @param groupId
	 * @param workerId
	 * @param activityId
	 * @return
	 */
	public static Charge readChargeSingle(int groupId,int workerId,int activityId){
		
		try{
			connect();
			String read = "select * from charge where groupId = " +groupId+
					" and workerId = " + workerId + " and activityId = " + activityId;
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(read);
			Charge charge ;

			if(rs.next()){
				charge = new Charge(rs.getInt(1),rs.getInt(2),
						rs.getInt(3),rs.getString(4));
			}
			else {
				return null;
			}
			
			return charge;
		}
		catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 插入一个负责关系
	 * @param charge
	 * @return
	 */
	public static boolean chargeInsert(Charge charge){
		try {
			connect();
			String insert = "insert into charge values(?,?,?,?)" ;
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, charge.getGroupId());
			psmt.setInt(2, charge.getWorkerId());
			psmt.setInt(3, charge.getActivityId());
			psmt.setString(4, charge.getChargeTime());
			
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in chargeInsert.");
			return false;
		}
		
	}
	
	/**
	 * 删除指定负责关系
	 * @param groupId
	 * @param workerId
	 * @param activityId
	 * @return
	 */
	public static boolean chargeDel(int groupId,int workerId,int activityId){
		try {
			connect();
			String del = "delete from charge where groupId = ? and workerId = ? and activityId = ? ";
			PreparedStatement psmt = con.prepareStatement(del);
			
			psmt.setInt(1, groupId);
			psmt.setInt(2, workerId);
			psmt.setInt(3, activityId);
			
			int r = psmt.executeUpdate();
			if(r>0){
				psmt.close();
				return true;
			}	
			psmt.close();
			
			return false;
		} catch (Exception e) {
			System.out.println("error in chargeDel.");
			return false;
		}
		
	}
	
	/**
	 * 负责信息更新
	 * @param charge
	 * @return
	 */
	public static boolean chargeUpdate(Charge charge) {
		try {
			connect();
			
/*			String update = "update charge set groupId = ? and workerId = ? and activityId = ? where ";
			PreparedStatement psmt = con.prepareStatement(update);
			
			psmt.setInt(1, charge.getGroupId());
			psmt.setInt(2, charge.getWorkerId());
			psmt.setInt(3, charge.getActivityId());
			psmt.executeUpdate();
			psmt.close();
			
			return true;
*/		
//			boolean flag = chargeDel(charge.getActivityId(),charge.getWorkerId(),charge.getActivityId());
//			System.out.println("flag = "+ flag);
			
			if(chargeDel(charge.getGroupId(),charge.getWorkerId(),charge.getActivityId())){
				chargeInsert(charge);
				return true;
			}
			return false;
			
		} catch (Exception e) {
			System.out.println("error in groupUpdate.");
			return false;
		}
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Charge charge = new Charge(4,34,14,"10:00~11:00");
//		Charge charge = new Charge(1,101,13,"8:00~10:00");
//		System.out.println(chargeInsert(charge));
//		System.out.println(chargeDel(4,34,14));	
/*		System.out.println(charge.getGroupId());
		System.out.println(charge.getWorkerId());
		System.out.println(charge.getActivityId());
		
		System.out.println(chargeUpdate(charge));
		*/
//		Charge charge = readChargeSingle(1,101,13);
		System.out.println(chargeUpdate(charge));
//		System.out.println(chargeDel(4, 34, 14));
/*		System.out.println(charge.getGroupId());
		System.out.println(charge.getWorkerId());
		System.out.println(charge.getActivityId());
		System.out.println(charge.getChargeTime());
*/		
		
	}
}
