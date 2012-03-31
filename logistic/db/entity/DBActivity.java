package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import classes.entity.Activity;
import classes.entity.Stock;

public class DBActivity extends DBConnection{
	
	
	/**
	 * ��ȡ���л��Ϣ
	 * @return
	 */
	public static ArrayList<Activity> readActivity(){
		try {
			connect();
			String read = "select * from activity ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			ArrayList<Activity > activities = new ArrayList<Activity>();
			
			while(rs.next()){
				activities.add(new Activity(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
			}
			
			return activities;
			
		} catch (Exception e) {
			System.out.println("error in readActivity.");
			return null;
		}
	}
	
	/**
	 * ��ȡ�������Ϣ
	 * @param activityId
	 * @return
	 */
	public static Activity readActivitySingle(int activityId){
		
		try {
			connect();
			String read = "select * from activity where activityId = " + activityId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			rs.next();
			Activity activity = new Activity(rs.getInt(1),rs.getString(2),
					rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
			
			return activity;
		} catch (Exception e) {
			System.out.println("error in readActivitySingle.");
			return null;
		}
		
	}
	/**
	 * ����
	 * @param activity
	 * @return
	 */
	public static boolean activityInsert(Activity activity){
		try {
			connect();
			String insert = "insert into activity values(?,?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, activity.getActivityId());
			psmt.setString(2, activity.getActivityName());
			psmt.setString(3, activity.getActivityPlace());
			psmt.setString(4, activity.getActivityTime());
			psmt.setString(5, activity.getActivityContact());
			psmt.setInt(6, activity.getActivityPersonNum());
			psmt.executeUpdate();
			psmt.close();
			return true;
		} catch (Exception e) {
			System.out.println("error in activityInsert.");
			return false;
		}
		
	}
	
	/**
	 * ɾ���
	 * @param activityId
	 * @return
	 */
	public static boolean activityDel(int activityId){
		
		try {
			connect();
			String del = "delete from activity where activityId=?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, activityId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error in activityDel.");
			return false;
		}
	}
		
	/**
	 * ���Ϣ����
	 * @param activity
	 * @return
	 */
	public static boolean activityUpdate(Activity activity){
		try {
			if(activityDel(activity.getActivityId())){
				activityInsert(activity);
				return true;
			}
			else
			{
				System.out.println("error in activityUpdate.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("error in activityUpdate2.");
			return false;
		}
		
	}
	
	/**
	 * ��������
	 * @param args
	 */
	public static void main(String[] args) {
		Activity activity = new Activity(13,"sing3","building2",
				"8:00~10:00","about spring",200);
	//	System.out.println(activityDel(activity.getActivityId()));
	//	activity.setActivityName("sing2");
	//	activityUpdate(activity);
		System.out.println(activityInsert(activity));
	}
}
