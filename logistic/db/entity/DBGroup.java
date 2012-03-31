package db.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import classes.entity.Group;

public class DBGroup extends DBConnection{
	
	
	/**
	 * 读取所有管理组信息
	 * @return
	 */
	public static ArrayList<Group> readGroup(){
		
		try {
			connect();
			String read = "select * from groups ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			ArrayList<Group> groups = new ArrayList<Group>();
			while(rs.next()){
				groups.add(new Group(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5)));
			}
			return groups;
		} catch (Exception e) {
			System.out.println("error in readGroup.");
			return null;
		}
	}
	
	/**
	 * 读取单个管理组信息
	 * @param groupId
	 * @return
	 */
	public static Group readGroupSingle(int groupId){
		
		try {
			connect();
			String read = "select * from groups where groupId="+groupId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			
			rs.next();
			Group group = new Group(rs.getInt(1),rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5));
			
			return group;
			
		} catch (Exception e) {
			System.out.println("error in readGroupSingle.");
			return null;
		}
	}
	/**
	 * 插入管理组信息
	 * @param group
	 * @return
	 */
	public static boolean groupInsert(Group group){
		try {
			connect();
			String insert="insert into groups values(?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, group.getGroupId());
			psmt.setString(2, group.getGroupName());
			psmt.setString(3, group.getGroupDuty());
			psmt.setString(4, group.getOffice());
			psmt.setString(5, group.getWorkTime());
			psmt.executeUpdate();
			psmt.close();
			
			return true;		
		} catch (Exception e) {
			System.out.println("error in groupInsert.");
			return false;
		}		
	}
	
	/**
	 * 删除管理组信息
	 * @param groupId
	 * @return
	 */
	public static boolean groupDel(int groupId){
		try {
			connect();
			String del = "delete from groups where groupId = ?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, groupId);
			psmt.executeUpdate();
			psmt.close();
			
			return true;
		} catch (Exception e) {
			System.out.println("error in groupDel.");
			return false;
		}
	}
	
	/**
	 * 管理组信息更新
	 * @param group
	 * @return
	 */
	public static boolean groupUpdate(Group group){
		
		try {
			connect();
			if(groupDel(group.getGroupId())){
				return groupInsert(group);
			}
			else
			{
				System.out.println("error in groupUpdate Excep.");
				return false;
			}
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
		Group group = new Group(1,"publicizeTeam",
				"publicize","building_1","8:00~10:00");
//		System.out.println(groupDel(group.getGroupId()));
		System.out.println(groupInsert(group));
		System.out.println(readGroupSingle(1));
	}
}
