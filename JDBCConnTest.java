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
 * JDBC���Ӳ���
 * 
 * @author acer
 *
 */
public class JDBCConnTest {
	String DRIVER = "com.mysql.jdbc.Driver";// ���ݿ�����
	String URL = "jdbc:mysql:///test";// ���ݿ�·��
	String USER = "root";// �û���
	String PASSWORD = "tiger";// ����
	Connection conn = null;
	PreparedStatement ps = null;
	// Statement st=null;
	ResultSet rs = null;

	@Before
	public void before() {
		try {
			Class.forName(DRIVER);// �������ݿ�����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);// ���������ݿ������
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void insertUser() {
		try {
			// Class.forName(DRIVER);//�������ݿ�����
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//���������ݿ������
			String sql = "insert into user_info values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 5);
			ps.setString(2, "������");
			ps.setString(3, "���ǳ�");
			ps.setString(4, "95721");
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("��¼����ɹ�!");
			} else {
				System.out.println("��¼����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deletetUser() {
		try {
			// Class.forName(DRIVER);//�������ݿ�����
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//���������ݿ������
			int id = 5;
			String sql = "delete from user_info where id='" + id + "'";
			ps = conn.prepareStatement(sql);
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("��¼ɾ���ɹ�!");
			} else {
				System.out.println("��¼ɾ��ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatetUser() {
		try {
			// Class.forName(DRIVER);//�������ݿ�����
			// conn=DriverManager.getConnection(URL,USER,PASSWORD);//���������ݿ������
			int id = 4;
			String password = "123456";
			String sql = "update user_info set password='" + password + "' where id='" + id + "'";
			ps = conn.prepareStatement(sql);
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("��¼���³ɹ�!");
			} else {
				System.out.println("��¼����ʧ�ܣ�");
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
				System.out.println("�û���ID��"+rs.getString("id")+"\t�û�����"+rs.getString("username"));
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
			System.out.println("������Դ�ر�ʧ��");
		}
	}
}
