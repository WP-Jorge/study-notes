package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.*;
import entity.Role;
/**
 * @author ***
 * @date 2021��5��18�� ����1:34:56
 */
public class RoleDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;
	public List<Role> listAll(){
		List<Role> list=new ArrayList<Role>();
		String sql="select * from role order by r_id";
		try{
		con=dbcon.getConnection();
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			Role r=new Role();
			r.setRid(rs.getInt("r_id"));
			r.setRtitle(rs.getString("r_title"));
			list.add(r);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		//����һ��list����
		return list;
	}
}
