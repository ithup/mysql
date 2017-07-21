package day13;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * ≤‚ ‘¿‡
 * @author acer
 *
 */
public class DBTest {
	@Test
	public void findUser(){
		int id=4;
		String sql="select * from user_info where id='"+id+"'";
		ResultSet rs = DBConn.query(sql);
		try {
			while(rs.next()){
				System.out.println(rs.getString(1)+rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
