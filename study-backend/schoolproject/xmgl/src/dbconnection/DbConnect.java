package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.sql.*;
import java.util.*;
/**
 * @author ***
 * @date 2021��5��18�� ����1:35:33
 */
public class DbConnect {
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/xmgl";
	public static final String DBNAME="root";
	public static final String DBPW="123456";
	
	ResultSet rs=null;
	PreparedStatement ps=null;
	Connection con=null;
	
	/**
	 * ������ݿ�����
	 * @return ����
	 */
	public Connection getConnection(){
		Connection con=null;
	  try {
		//1.ע������
		Class.forName(DRIVER);
		//2.������ݿ�����
		con=DriverManager.getConnection(URL, DBNAME,DBPW);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
	}
	/**
	 * �Ͽ����ݿ�����
	 * @param rs
	 * @param ps
	 * @param con
	 */
	public void close(ResultSet rs,PreparedStatement ps,Connection con){//�÷��������ͷ���Դ
		try {
		if(rs!=null){rs.close();}
		if(ps!=null){ps.close();}
		if(con!=null){con.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}
