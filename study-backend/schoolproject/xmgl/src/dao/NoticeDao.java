package dao;

import entity.Notice;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbconnection.DbConnect;

/**
 * @author ***
 * @date 2021年5月18日 下午1:34:26
 */
public class NoticeDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;

	/**
	 * 排序查询所有
	 * @return 查询结果的集合
	 */
	public List<Notice> listAll() {
		List<Notice> list = new ArrayList<Notice>();
		String sql = "select * from notice order by n_id desc";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Notice n = new Notice();
				n.setNid(rs.getInt("n_id"));
				n.setNtitle(rs.getString("n_title"));
				n.setNcontents(rs.getString("n_contents"));
				n.setNdate(rs.getDate("n_date"));
				n.setNbywho(rs.getInt("n_bywho"));
				list.add(n);
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
	 * 获取最新通告
	 * @return 返回查询结果，是一个Notice对象
	 */
	public Notice getNewNotice() {
		Notice n = new Notice();
		String sql = "SELECT A.* FROM notice A, (SELECT n_id, max(n_date) max_day FROM notice GROUP BY n_id) B WHERE A.n_id = B.n_id AND A.n_date = B.max_day";

		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				n.setNid(rs.getInt("n_id"));
				n.setNtitle(rs.getString("n_title"));
				n.setNcontents(rs.getString("n_contents"));
				n.setNdate(rs.getDate("n_date"));
				n.setNbywho(rs.getInt("n_bywho"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return n;
	}

	/**
	 * 删除数据
	 * @param nid 查询的id
	 * @return 返回查询影响的行数
	 */
	public int deleteNotice(int nid) {
		int row = 0;
		// 预处理SQL语句
		String sql = "delete from notice where n_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setInt(1, nid);
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
	 * 获取当前通知记录条数
	 * @return 返回notice表中的记录个数
	 */
	public int getCount() {
		int lineCount = 0;
		String sql = "select COUNT(*) from notice";
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
	 * 发布公告
	 * @param title 公告标题
	 * @param contents 公告内容
	 * @param bywho 公告发布者
	 * @return 返回sql语句执行后影响的行数
	 */
	public int add(String title, String contents, int bywho) {
		int row = 0;
		// 预处理SQL语句
		String sql = "insert into notice (n_title,n_contents,n_date,n_bywho) values (?,?,?,?)";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setString(1, title);
			ps.setString(2, contents);
			ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.setInt(4, bywho);
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

}
