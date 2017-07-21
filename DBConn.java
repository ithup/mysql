package day13;
/**
 * ���ݿ����ӹ�����
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;
public class DBConn {
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql:///test";
	public static final String USER="root";
	public static final String PASSWORD="tiger";
	public static Connection conn =null;
	static{
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������ݿ�����
	 * @return
	 */
	public static Connection getConn(){
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConn(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ѯ����
	 * @param sql
	 * @return
	 */
	public static ResultSet query(String sql){
		ResultSet rs=null;
		try {
			Statement st = getConn().prepareStatement(sql);
			rs = st.executeQuery(sql);//��ѯ
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
