package day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JDBC连接操作
 * 
 * @author acer
 *
 */
public class JDBCConnTest {
	String DRIVER = "com.mysql.jdbc.Driver";// 数据库驱动
	String URL = "jdbc:mysql:///test";// 数据库路径
	String USER = "root";// 用户名
	String PASSWORD = "tiger";// 密码
	Connection conn = null;
	PreparedStatement ps = null;
	// Statement st=null;
	ResultSet rs = null;

	@Before
	public void before() {
		try {
			Class.forName(DRIVER);// 加载数据库驱动
			conn = DriverManager.getConnection(URL, USER, PASSWORD);// 建立与数据库的连接
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void insertUser() {
		try {
			// Class.forName(DRIVER);//加载数据库驱动
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//建立与数据库的连接
			String sql = "insert into user_info values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 5);
			ps.setString(2, "周星星");
			ps.setString(3, "周星驰");
			ps.setString(4, "95721");
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("记录插入成功!");
			} else {
				System.out.println("记录插入失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deletetUser() {
		try {
			// Class.forName(DRIVER);//加载数据库驱动
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//建立与数据库的连接
			int id = 5;
			String sql = "delete from user_info where id='" + id + "'";
			ps = conn.prepareStatement(sql);
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("记录删除成功!");
			} else {
				System.out.println("记录删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatetUser() {
		try {
			// Class.forName(DRIVER);//加载数据库驱动
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//建立与数据库的连接
			int id = 4;
			String password = "123456";
			String sql = "update user_info set password='" + password + "' where id='" + id + "'";
			ps = conn.prepareStatement(sql);
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("记录更新成功!");
			} else {
				System.out.println("记录更新失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	@Test
	public void selectUser(){
		try {
			String sql="select * from user_info";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("用户名ID："+rs.getString("id")+"\t用户名："+rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
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
			System.out.println("数据资源关闭失败");
		}
	}
}
