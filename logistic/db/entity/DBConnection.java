package db.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�������
 * @author qinguan
 *
 */
public class DBConnection {

	protected static Connection con;

	public static Connection getConnection() throws SQLException {
		//���ݿ�����
		String driver = "com.mysql.jdbc.Driver";
		//�������ݿ�����
		String url = "jdbc:mysql://localhost:3306/logistic";
		//�û���
		String user = "root";
		//����
		String password = "870619";
		try {
			//��������
			Class.forName(driver);
			//�������ݿ�
			DriverManager.getConnection(url, user, password);
			//��������
			return DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��������״̬�����ӳɹ�����true������ʧ�ܷ���false
	 * @return
	 */
	public static boolean connect() {
		try {
			con = DBConnection.getConnection();
			return true;
		} catch (SQLException e) {
			System.out.println("ERROR in connect (DBConnecttion).");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ����DBConnection����
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DBConnection.getConnection();
			System.out.println("true");
		} catch (SQLException e) {			
			System.out.println("ERROR .");
			e.printStackTrace();
		}
	}
}
