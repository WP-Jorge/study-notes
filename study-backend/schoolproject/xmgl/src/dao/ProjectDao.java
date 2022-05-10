package dao;

import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnect;
/**
 * @author ***
 * @date 2021��5��18�� ����1:34:47
 */
public class ProjectDao {
	DbConnect dbcon = new DbConnect();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection con = null;

	public int getCountByUid(int uid) {
		int lineCount = 0;
		String sql = "select COUNT(*) from project where p_teacher=? or p_expert=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setInt(2, uid);
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
	 * ���������ѯ����
	 * @return ���ز�ѯ���Ľ������
	 */
	public List<Project> listAll() {
		List<Project> list = new ArrayList<Project>();
		String sql = null;
		sql = "select * from project order by p_id desc";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project p = new Project();
				p.setPid(rs.getInt("p_id"));
				p.setPtitle(rs.getString("p_title"));
				p.setPintroduce(rs.getString("p_introduce"));
				p.setPteacher(rs.getInt("p_teacher"));
				p.setPexpert(rs.getInt("p_expert"));
				p.setPstatus(rs.getInt("p_status"));
				p.setPapplicationStuff(rs.getString("p_application_stuff"));
				p.setPoverStuff(rs.getString("p_over_stuff"));
				p.setPstu1(rs.getString("p_stu1"));
				p.setPstu2(rs.getString("p_stu2"));
				p.setPstu3(rs.getString("p_stu3"));
				p.setPstu4(rs.getString("p_stu4"));
				list.add(p);
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
	 * �����ϴ��ļ� 0:�ϴ������ļ�,1:�ϴ������ļ�
	 * @param filetype �ļ�����
	 * @param filename �ļ���
	 * @param pid ��Ŀid
	 * @return Ӱ������
	 */
	public int uploadFile(int filetype, String filename, int pid) {
		int row = 0;
		String sql = null;
		if (filetype == 0) {
			sql = "update project set p_application_stuff=? where p_id=?";
		}
		if (filetype == 1) {
			sql = "update project set p_over_stuff=? where p_id=?";
		}
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, filename);
			ps.setInt(2, pid);
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
	 * ����ר��
	 * @param uid ר��id
	 * @param pid ��Ŀid
	 * @return Ӱ������
	 */
	public int pointExpert(int uid, int pid) {
		int row = 0;
		String sql = "update project set p_expert=? where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setInt(1, uid);
			ps.setInt(2, pid);
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
	 * ����״̬
	 * @param pStatus ���ĵ�״̬
	 * @param pid ��Ŀid
	 * @return Ӱ������
	 */
	public int updateStatus(int pStatus, int pid) {
		int row = 0;
		String sql = "update project set p_status=? where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setInt(1, pStatus);
			ps.setInt(2, pid);
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
	 * ɾ����Ŀ
	 * @param pid ɾ������Ŀid
	 * @return Ӱ������
	 */
	public int deleteProject(int pid) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "delete from project where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setInt(1, pid);
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
	 * �����Ŀ
	 * @param p ��ӵ���Ŀ��Ϊһ��Project����
	 * @return Ӱ������
	 */
	public int add(Project p) {
		int row = 0;
		// Ԥ����SQL���
		String sql = "insert into project (p_title,p_introduce,p_teacher,p_stu1,p_stu2,p_stu3,p_stu4 ) values (?,?,?,?,?,?,?)";
		try { // p_status,p_application_stuff,p_over_stuff,p_expert,
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// Ϊ?ռλ������ֵ
			ps.setString(1, p.getPtitle());
			ps.setString(2, p.getPintroduce());
			ps.setInt(3, p.getPteacher());
			// ps.setInt(4, p.getP_status());
			// ps.setString(5, p.getP_application_stuff());
			// ps.setString(6, p.getP_over_stuff());
			// ps.setInt(7, p.getP_expert());
			ps.setString(4, p.getPstu1());
			ps.setString(5, p.getPstu2());
			ps.setString(6, p.getPstu3());
			ps.setString(7, p.getPstu4());
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
	 * ��ѯĳ״̬��ר�ҵ���Ŀ����
	 * @param status ״̬
	 * @param expert ר����
	 * @return ��ѯ����������
	 */
	public int getCountByStatusandExpert(int status, int expert) {
		int lineCount = 0;
		String sql = "select COUNT(*) from project where p_status=? and p_expert=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, expert);
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
	 * ��ѯ��Ŀ����
	 * @return ��Ŀ����
	 */
	public int getCount() {
		int lineCount = 0;
		String sql = "select COUNT(*) from project";
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
	 * ��ʦ��ҳ��ѯ����
	 * @param status
	 * @param page
	 * @param uid
	 * @return ��ѯ�������
	 */
	public List<Project> listByPage(int status, int page, int uid) {// page����ǰҳ
		List<Project> list = new ArrayList<Project>();
		// ��ʼ��������ʾÿҳ��һ����¼�����ݿ��е�����
		int rowBegin = 0; 
		if (page > 1) {
			// ��ҳ��ȡ�ÿ�ʼ��������ÿҳ������ʾ8����¼
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// ��ѯ����
			sql = "select  *  from project where p_teacher=? order by p_id desc limit ?,? ";
		} else {
			// ��״̬��ѯ
			sql = "select  *  from project where p_status=? and p_teacher=? order by p_id desc limit ?,? ";
		}
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			if (status == 0) {
				ps.setInt(1, uid);
				ps.setInt(2, rowBegin);
				ps.setInt(3, 8);
			} else {
				ps.setInt(1, status);
				ps.setInt(2, uid);
				ps.setInt(3, rowBegin);
				ps.setInt(4, 8);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project p = new Project();
				p.setPid(rs.getInt("p_id"));
				p.setPtitle(rs.getString("p_title"));
				p.setPintroduce(rs.getString("p_introduce"));
				p.setPteacher(rs.getInt("p_teacher"));
				p.setPexpert(rs.getInt("p_expert"));
				p.setPstatus(rs.getInt("p_status"));
				p.setPapplicationStuff(rs.getString("p_application_stuff"));
				p.setPoverStuff(rs.getString("p_over_stuff"));
				p.setPstu1(rs.getString("p_stu1"));
				p.setPstu2(rs.getString("p_stu2"));
				p.setPstu3(rs.getString("p_stu3"));
				p.setPstu4(rs.getString("p_stu4"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close(rs, ps, con);
		}
		return list;
	}

	/**
	 * ר�ҷ�ҳ��ѯ����
	 * @param status
	 * @param page
	 * @param uid
	 * @return ��ѯ�������
	 */
	public List<Project> expertlistByPage(int status, int page, int uid) {// page����ǰҳ
		List<Project> list = new ArrayList<Project>();
		// ��ʼ��������ʾÿҳ��һ����¼�����ݿ��е�����
		int rowBegin = 0; 
		if (page > 1) {
			// ��ҳ��ȡ�ÿ�ʼ��������ÿҳ������ʾ8����¼
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// ��ѯ����
			sql = "select  *  from project where p_teacher=? order by p_id desc limit ?,?";
		} else {
			// ��״̬��ѯ
			sql = "select  *  from project where p_status=? and p_expert=? order by p_id desc limit ?,? ";
		}
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			if (status == 0) {
				ps.setInt(1, uid);
				ps.setInt(2, rowBegin);
				ps.setInt(3, 8);
			} else {
				ps.setInt(1, status);
				ps.setInt(2, uid);
				ps.setInt(3, rowBegin);
				ps.setInt(4, 8);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project p = new Project();
				p.setPid(rs.getInt("p_id"));
				p.setPtitle(rs.getString("p_title"));
				p.setPintroduce(rs.getString("p_introduce"));
				p.setPteacher(rs.getInt("p_teacher"));
				p.setPexpert(rs.getInt("p_expert"));
				p.setPstatus(rs.getInt("p_status"));
				p.setPapplicationStuff(rs.getString("p_application_stuff"));
				p.setPoverStuff(rs.getString("p_over_stuff"));
				p.setPstu1(rs.getString("p_stu1"));
				p.setPstu2(rs.getString("p_stu2"));
				p.setPstu3(rs.getString("p_stu3"));
				p.setPstu4(rs.getString("p_stu4"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close(rs, ps, con);
		}
		return list;
	}

	/**
	 * ͨ��id��ȡ��Ŀ
	 * @param pid
	 * @return ��ѯ���Ľ����Ϊһ��Project����
	 */
	public Project getProById(int pid) {
		Project p = new Project();
		String sql = "select * from project where p_id=? ";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			while (rs.next()) {
				p.setPid(rs.getInt("p_id"));
				p.setPtitle(rs.getString("p_title"));
				p.setPintroduce(rs.getString("p_introduce"));
				p.setPteacher(rs.getInt("p_teacher"));
				p.setPstatus(rs.getInt("p_status"));
				p.setPexpert(rs.getInt("p_expert"));
				p.setPapplicationStuff(rs.getString("p_application_stuff"));
				p.setPoverStuff(rs.getString("p_over_stuff"));
				p.setPstu1(rs.getString("p_stu1"));
				p.setPstu2(rs.getString("p_stu2"));
				p.setPstu3(rs.getString("p_stu3"));
				p.setPstu4(rs.getString("p_stu4"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			dbcon.close(rs, ps, con);
		}
		return p;
	}

	/**
	 * ��ѯָ����ʦ������Ŀ����
	 * @param pTeacherUid
	 * @return ��ѯ�������
	 */
	public int getCount(int pTeacherUid) {
		int lineCount = 0;
		String sql = "select COUNT(*) from project where p_teacher=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pTeacherUid);
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
	 * @param status
	 * @param page
	 * @return ��ѯ�������
	 */
	public List<Project> listByPage(int status, int page) {// page����ǰҳ
		List<Project> list = new ArrayList<Project>();
		// ��ʼ��������ʾÿҳ��һ����¼�����ݿ��е�����
		int rowBegin = 0; 
		if (page > 1) {
			// ��ҳ��ȡ�ÿ�ʼ��������ÿҳ������ʾ8����¼
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// ��ѯ����
			sql = "select  *  from project order by p_id desc limit ?,? ";
		} else {
			// ��״̬��ѯ
			sql = "select  *  from project where p_status=? order by p_id desc limit ?,?";
		}
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			if (status == 0) {
				ps.setInt(1, rowBegin);
				ps.setInt(2, 8);
			} else {
				ps.setInt(1, status);
				ps.setInt(2, rowBegin);
				ps.setInt(3, 8);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project p = new Project();
				p.setPid(rs.getInt("p_id"));
				p.setPtitle(rs.getString("p_title"));
				p.setPintroduce(rs.getString("p_introduce"));
				p.setPteacher(rs.getInt("p_teacher"));
				p.setPexpert(rs.getInt("p_expert"));
				p.setPstatus(rs.getInt("p_status"));
				p.setPapplicationStuff(rs.getString("p_application_stuff"));
				p.setPoverStuff(rs.getString("p_over_stuff"));
				p.setPstu1(rs.getString("p_stu1"));
				p.setPstu2(rs.getString("p_stu2"));
				p.setPstu3(rs.getString("p_stu3"));
				p.setPstu4(rs.getString("p_stu4"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close(rs, ps, con);
		}
		return list;
	}
}
