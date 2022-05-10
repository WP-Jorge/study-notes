package dao;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnect;
/**
 * @author ***
 * @date 2021年5月18日 下午1:35:13
 */
public class UserDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;

	/**
	 * 验证密码
	 * @param uMail
	 * @param password
	 * @return 查询结果，为boolean类型
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		if (password.equals(psw)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新用户基本信息
	 * @param uRealname
	 * @param uMail
	 * @param uPhone
	 * @param uQq
	 * @return 影响行数
	 */
	public int updateUserInfo(String uRealname, String uMail, String uPhone, String uQq) {
		int row = 0;
		String sql = "update user set u_realname=?,u_phone=?,u_qq=? where u_mail=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setString(1, uRealname);
			ps.setString(2, uPhone);
			ps.setString(3, uQq);
			ps.setString(4, uMail);
			// 返回受影响的记录行数
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回受影响的记录行数
		return row;
	}
	
	/**
	 * 更新用户信息
	 * @param uid
	 * @param uMail
	 * @param uPassword
	 * @param uRole
	 * @param uRealname
	 * @param uPhone
	 * @param uQq
	 * @param uStatus
	 * @return 影响行数
	 */
	public int updateUserInfo(int uid, String uMail, String uPassword, int uRole, String uRealname, String uPhone,
			String uQq, int uStatus) {
		int row = 0;
		String sql = "update user set u_mail=?,u_password=?,u_role=?,u_realname=?,u_phone=?,u_qq=?,u_status=? where u_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setString(1, uMail);
			ps.setString(2, uPassword);
			ps.setInt(3, uRole);
			ps.setString(4, uRealname);
			ps.setString(5, uPhone);
			ps.setString(6, uQq);
			ps.setInt(7, uStatus);
			ps.setInt(8, uid);
			// 返回受影响的记录行数
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回受影响的记录行数
		return row;
	}

	/**
	 * 修改密码
	 * @param uMail
	 * @param uPassword
	 * @return 影响行数
	 */
	public int updateUserPwd(String uMail, String uPassword) {
		int row = 0;
		String sql = "update user set u_password=? where u_mail=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setString(1, uPassword);
			ps.setString(2, uMail);
			// 返回受影响的记录行数
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回受影响的记录行数
		return row;
	}

	/**
	 * 通过mail获取用户
	 * @param uMail
	 * @return 查询到的结果，为一个User对象
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return u;
	}

	/**
	 * 通过id获取用户
	 * @param uid
	 * @return 查询到的结果，为一个User对象
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return u;
	}

	/**
	 * 获取所有专家并且根据id排序查询所有
	 * @return 查询到的结果集合
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回一个list集合
		return list;
	}

	/**
	 * 用户管理模块并且根据id排序查询结果
	 * @return 查询到的结果集合
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回一个list集合
		return list;
	}

	/**
	 * 添加用户
	 * @param u 添加对象
	 * @return 影响行数
	 */
	public int add(User u) {
		int row = 0;
		// 预处理SQL语句
		String sql = "insert into user (u_mail,u_password,u_role,u_realname,u_phone,u_qq,u_status ) values (?,?,?,?,?,?,?)";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setString(1, u.getUmail());
			ps.setString(2, u.getUpassword());
			ps.setInt(3, u.getUrole());
			ps.setString(4, u.getUrealname());
			ps.setString(5, u.getUphone());
			ps.setString(6, u.getUqq());
			ps.setInt(7, u.getUstatus());
			// 返回受影响的记录行数
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回受影响的记录行数
		return row;
	}

	/**
	 * 删除用户
	 * @param uid 用户id
	 * @return 影响行数
	 */
	public int deleteUser(int uid) {
		int row = 0;
		// 预处理SQL语句
		String sql = "delete from user where u_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setInt(1, uid);
			// 返回受影响的记录行数
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回受影响的记录行数
		return row;
	}

	/**
	 * 查询总用户人数
	 * @return 总用户人数
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * 分页查询方法
	 * @param page
	 * @return 查询到的结果集合
	 */
	public List<User> listByPage(int page) {// page代表当前页
		List<User> list = new ArrayList<User>();
		// 开始行数，表示每页第一条记录在数据库中的行数
		int rowBegin = 0;
		if (page > 1) {
			// 按页数取得开始行数，设每页可以显示10条记录
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
