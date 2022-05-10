package dao;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnect;
/**
 * @author ***
 * @date 2021��5��18�� ����1:35:13
 */
public class UserDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;

	/**
	 * ��֤����
	 * @param uMail
	 * @param password
	 * @return ��ѯ�����Ϊboolean����
	 */
	public boolean checkLogin(String uMail, String password) {
		String psw = null;
		String sql = "select * from user where u_mail=? and u_status=1";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, uMail);
			rs = ps.executeQuery();
			while (rs.next()) {
				psw = rs.getString("u_password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		if (password.equals(psw)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �����û�������Ϣ
	 * @param uRealname
	 * @param uMail
	 * @param uPhone
	 * @param uQq
	 * @return Ӱ������
	 */
	public int updateUserInfo(String uRealname, String uMail, String uPhone, String uQq) {
		int row = 0;
		String sql = "update user set u_realname=?,u_phone=?,u_qq=? where u_mail=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, uRealname);
			ps.setString(2, uPhone);
			ps.setString(3, uQq);
			ps.setString(4, uMail);
			// ������Ӱ��ļ�¼����
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ������Ӱ��ļ�¼����
		return row;
	}
	
	/**
	 * �����û���Ϣ
	 * @param uid
	 * @param uMail
	 * @param uPassword
	 * @param uRole
	 * @param uRealname
	 * @param uPhone
	 * @param uQq
	 * @param uStatus
	 * @return Ӱ������
	 */
	public int updateUserInfo(int uid, String uMail, String uPassword, int uRole, String uRealname, String uPhone,
			String uQq, int uStatus) {
		int row = 0;
		String sql = "update user set u_mail=?,u_password=?,u_role=?,u_realname=?,u_phone=?,u_qq=?,u_status=? where u_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, uMail);
			ps.setString(2, uPassword);
			ps.setInt(3, uRole);
			ps.setString(4, uRealname);
			ps.setString(5, uPhone);
			ps.setString(6, uQq);
			ps.setInt(7, uStatus);
			ps.setInt(8, uid);
			// ������Ӱ��ļ�¼����
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ������Ӱ��ļ�¼����
		return row;
	}

	/**
	 * �޸�����
	 * @param uMail
	 * @param uPassword
	 * @return Ӱ������
	 */
	public int updateUserPwd(String uMail, String uPassword) {
		int row = 0;
		String sql = "update user set u_password=? where u_mail=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, uPassword);
			ps.setString(2, uMail);
			// ������Ӱ��ļ�¼����
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ������Ӱ��ļ�¼����
		return row;
	}

	/**
	 * ͨ��mail��ȡ�û�
	 * @param uMail
	 * @return ��ѯ���Ľ����Ϊһ��User����
	 */
	public User getUserByMail(String uMail) {
		User u = new User();
		// and u_status=1
		String sql = "select * from user where u_mail=? ";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, uMail);
			rs = ps.executeQuery();
			while (rs.next()) {
				u.setUid(rs.getInt("u_id"));
				u.setUmail(rs.getString("u_mail"));
				u.setUpassword(rs.getString("u_password"));
				u.setUphone(rs.getString("u_phone"));
				u.setUqq(rs.getString("u_qq"));
				u.setUrealname(rs.getString("u_realname"));
				u.setUrole(rs.getInt("u_role"));
				u.setUstatus(rs.getInt("u_status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return u;
	}

	/**
	 * ͨ��id��ȡ�û�
	 * @param uid
	 * @return ��ѯ���Ľ����Ϊһ��User����
	 */
	public User getUserById(int uid) {
		User u = new User();
		// and u_status=1
		String sql = "select * from user where u_id=? ";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				u.setUid(rs.getInt("u_id"));
				u.setUmail(rs.getString("u_mail"));
				u.setUpassword(rs.getString("u_password"));
				u.setUphone(rs.getString("u_phone"));
				u.setUqq(rs.getString("u_qq"));
				u.setUrealname(rs.getString("u_realname"));
				u.setUrole(rs.getInt("u_role"));
				u.setUstatus(rs.getInt("u_status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return u;
	}

	/**
	 * ��ȡ����ר�Ҳ��Ҹ���id�����ѯ����
	 * @return ��ѯ���Ľ������
	 */
	public List<User> listAllExpert() {

		List<User> list = new ArrayList<User>();
		String sql = "select * from user where u_role=2 order by u_id ";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("u_id"));
				u.setUmail(rs.getString("u_mail"));
				u.setUpassword(rs.getString("u_password"));
				u.setUphone(rs.getString("u_phone"));
				u.setUqq(rs.getString("u_qq"));
				u.setUrealname(rs.getString("u_realname"));
				u.setUrole(rs.getInt("u_role"));
				u.setUstatus(rs.getInt("u_status"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ����һ��list����
		return list;
	}

	/**
	 * �û�����ģ�鲢�Ҹ���id�����ѯ���
	 * @return ��ѯ���Ľ������
	 */
	public List<User> listAll() {
		List<User> list = new ArrayList<User>();
		String sql = "select * from user order by u_id";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("u_id"));
				u.setUmail(rs.getString("u_mail"));
				u.setUpassword(rs.getString("u_password"));
				u.setUphone(rs.getString("u_phone"));
				u.setUqq(rs.getString("u_qq"));
				u.setUrealname(rs.getString("u_realname"));
				u.setUrole(rs.getInt("u_role"));
				u.setUstatus(rs.getInt("u_status"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ����һ��list����
		return list;
	}

	/**
	 * ����û�
	 * @param u ��Ӷ���
	 * @return Ӱ������
	 */
	public int add(User u) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "insert into user (u_mail,u_password,u_role,u_realname,u_phone,u_qq,u_status ) values (?,?,?,?,?,?,?)";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, u.getUmail());
			ps.setString(2, u.getUpassword());
			ps.setInt(3, u.getUrole());
			ps.setString(4, u.getUrealname());
			ps.setString(5, u.getUphone());
			ps.setString(6, u.getUqq());
			ps.setInt(7, u.getUstatus());
			// ������Ӱ��ļ�¼����
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ������Ӱ��ļ�¼����
		return row;
	}

	/**
	 * ɾ���û�
	 * @param uid �û�id
	 * @return Ӱ������
	 */
	public int deleteUser(int uid) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "delete from user where u_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setInt(1, uid);
			// ������Ӱ��ļ�¼����
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ������Ӱ��ļ�¼����
		return row;
	}

	/**
	 * ��ѯ���û�����
	 * @return ���û�����
	 */
	public int getCount() {
		int lineCount = 0;
		String sql = "select COUNT(*) from user";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				lineCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * ��ҳ��ѯ����
	 * @param page
	 * @return ��ѯ���Ľ������
	 */
	public List<User> listByPage(int page) {// page����ǰҳ
		List<User> list = new ArrayList<User>();
		// ��ʼ��������ʾÿҳ��һ����¼�����ݿ��е�����
		int rowBegin = 0;
		if (page > 1) {
			// ��ҳ��ȡ�ÿ�ʼ��������ÿҳ������ʾ10����¼
			rowBegin = 10 * (page - 1); 
		}
		String sql = "select  *  from user order by u_id limit ?,? ";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, rowBegin);
			ps.setInt(2, 10);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("u_id"));
				u.setUmail(rs.getString("u_mail"));
				u.setUpassword(rs.getString("u_password"));
				u.setUphone(rs.getString("u_phone"));
				u.setUqq(rs.getString("u_qq"));
				u.setUrealname(rs.getString("u_realname"));
				u.setUrole(rs.getInt("u_role"));
				u.setUstatus(rs.getInt("u_status"));

				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close(rs, ps, con);
		}
		return list;
	}

}
