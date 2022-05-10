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
 * @date 2021��5��18�� ����1:34:26
 */
public class NoticeDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;

	/**
	 * �����ѯ����
	 * @return ��ѯ����ļ���
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
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		// ����һ��list����
		return list;
	}

	/**
	 * ��ȡ����ͨ��
	 * @return ���ز�ѯ�������һ��Notice����
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
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return n;
	}

	/**
	 * ɾ������
	 * @param nid ��ѯ��id
	 * @return ���ز�ѯӰ�������
	 */
	public int deleteNotice(int nid) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "delete from notice where n_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setInt(1, nid);
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
	 * ��ȡ��ǰ֪ͨ��¼����
	 * @return ����notice���еļ�¼����
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
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * ��������
	 * @param title �������
	 * @param contents ��������
	 * @param bywho ���淢����
	 * @return ����sql���ִ�к�Ӱ�������
	 */
	public int add(String title, String contents, int bywho) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "insert into notice (n_title,n_contents,n_date,n_bywho) values (?,?,?,?)";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, title);
			ps.setString(2, contents);
			ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.setInt(4, bywho);
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

}
