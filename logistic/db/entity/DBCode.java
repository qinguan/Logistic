package db.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * √‹¬Î≤È—Ø
 * @author qinguan
 *
 */
public class DBCode extends DBConnection {

	
	public static int serchName(String name,String passwd){
		try {
			connect();
			String read = "select * from code ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(read);

			while(rs.next()){
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
				if(name.equals(rs.getString(1))&&
						passwd.equals(rs.getString(2))){
					return 1;
				}
				else if(name.equals(rs.getString(1))){
					return 2;
				}
			}
			return 3;
		} catch (Exception e) {
			// TODO: handle exception
			return 3;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(serchName("qinguan","1235"));
	}
}
