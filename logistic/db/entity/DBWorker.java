package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import classes.entity.Group;
import classes.entity.Stock;
import classes.entity.Worker;

public class DBWorker extends DBConnection{
	
	
	/**
	 * ��ȡ���й�����Ա����Ϣ
	 * @return
	 */
	public static ArrayList<Worker> readWorker(){
		
		try {
			connect();
			String read = "select * from worker ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			ArrayList<Worker > workers = new ArrayList<Worker>();
			while(rs.next()){
	
				workers.add(new Worker(rs.getInt(1),rs.getString(2),
								rs.getString(3),rs.getString(4)));
			}
			return workers;
			
		} catch (Exception e) {
			System.out.println("error in readWorker.");
			return null;
		}
	}
	
	/**
	 * ��ȡ����������Ա����Ϣ
	 * @param workerId
	 * @return
	 */
	public static Worker readWorkerSingle(int workerId){
		
		try {
			connect();
			String read = "select * from worker where workerId="+workerId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);
			rs.next();
			
			Worker worker = new Worker(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4));
			
			return worker;
			
		} catch (Exception e) {
			System.out.println("error in readWorkerSingle.");
			return null;
		}
	}
	/**
	 * ���빤����Ա��Ϣ
	 * @param worker
	 * @return
	 */
	public static boolean workerInsert(Worker worker){
		try {
			connect();
			String insert="insert into worker values(?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(insert);
			psmt.setInt(1, worker.getWorkerId());
			psmt.setString(2, worker.getWorkerName());
			psmt.setString(3, worker.getWorkerTel());
			psmt.setString(4, worker.getWorkerSex());
			psmt.executeUpdate();
			
			psmt.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("error in workerInsert.");
			return false;
		}
		
	}
	
	/**
	 * ɾ��������Ա��Ϣ
	 * @param workerId
	 * @return
	 */
	public static boolean workerDel(int workerId){
		try {
			connect();
			String del = "delete from worker where workerId = ?";
			PreparedStatement psmt = con.prepareStatement(del);
			psmt.setInt(1, workerId);
			psmt.executeUpdate();
			psmt.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("error in workerDel.");
			return false;
		}
		
	}
	
	/**
	 * ������Ա��Ϣ����
	 * @param worker
	 * @return
	 */
	public static boolean workerUpdate(Worker worker){
			
			try {
				connect();
				if(workerDel(worker.getWorkerId())){
					return workerInsert(worker);
				}
				else
				{
					System.out.println("error in workerUpdate Excep.");
					return false;
				}
			} catch (Exception e) {
				System.out.println("error in workerUpdate.");
				return false;
			}
	}
	
	/**
	 * ��������
	 * @param args
	 */
	public static void main(String[] args) {
		Worker worker = new Worker(101,"qin","11112222","male");
		System.out.println(workerInsert(worker));
	}
}

