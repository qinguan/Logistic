package db.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类
 * @author qinguan
 *
 */
public class DBConnection {

	protected static Connection con;

	public static Connection getConnection() throws SQLException {
		//数据库驱动
		String driver = "com.mysql.jdbc.Driver";
		//创建数据库连接
		String url = "jdbc:mysql://localhost:3306/logistic";
		//用户名
		String user = "root";
		//密码
		String password = "870619";
		try {
			//加载驱动
			Class.forName(driver);
			//连接数据库
			DriverManager.getConnection(url, user, password);
			//返回连接
			return DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回连接状态，连接成功返回true，连接失败返回false
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
	 * 测试DBConnection用例
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
