package dao;

import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnect;
/**
 * @author ***
 * @date 2021年5月18日 下午1:34:47
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * 分类排序查询所有
	 * @return 返回查询到的结果集合
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		// 返回一个list集合
		return list;
	}

	/**
	 * 更新上传文件 0:上传申请文件,1:上传结题文件
	 * @param filetype 文件类型
	 * @param filename 文件名
	 * @param pid 项目id
	 * @return 影响行数
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
			// 为?占位符设置值
			ps.setString(1, filename);
			ps.setInt(2, pid);
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
	 * 分配专家
	 * @param uid 专家id
	 * @param pid 项目id
	 * @return 影响行数
	 */
	public int pointExpert(int uid, int pid) {
		int row = 0;
		String sql = "update project set p_expert=? where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setInt(1, uid);
			ps.setInt(2, pid);
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
	 * 更改状态
	 * @param pStatus 更改的状态
	 * @param pid 项目id
	 * @return 影响行数
	 */
	public int updateStatus(int pStatus, int pid) {
		int row = 0;
		String sql = "update project set p_status=? where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setInt(1, pStatus);
			ps.setInt(2, pid);
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
	 * 删除项目
	 * @param pid 删除的项目id
	 * @return 影响行数
	 */
	public int deleteProject(int pid) {
		int row = 0;
		// 预处理SQL语句
		String sql = "delete from project where p_id=?";
		try {
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
			ps.setInt(1, pid);
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
	 * 添加项目
	 * @param p 添加的项目，为一个Project对象
	 * @return 影响行数
	 */
	public int add(Project p) {
		int row = 0;
		// 预处理SQL语句
		String sql = "insert into project (p_title,p_introduce,p_teacher,p_stu1,p_stu2,p_stu3,p_stu4 ) values (?,?,?,?,?,?,?)";
		try { // p_status,p_application_stuff,p_over_stuff,p_expert,
			con = dbcon.getConnection();
			ps = con.prepareStatement(sql);
			// 为?占位符设置值
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
	 * 查询某状态和专家的项目总数
	 * @param status 状态
	 * @param expert 专家数
	 * @return 查询到的总行数
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * 查询项目总数
	 * @return 项目总数
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * 老师分页查询方法
	 * @param status
	 * @param page
	 * @param uid
	 * @return 查询结果集合
	 */
	public List<Project> listByPage(int status, int page, int uid) {// page代表当前页
		List<Project> list = new ArrayList<Project>();
		// 开始行数，表示每页第一条记录在数据库中的行数
		int rowBegin = 0; 
		if (page > 1) {
			// 按页数取得开始行数，设每页可以显示8条记录
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// 查询所有
			sql = "select  *  from project where p_teacher=? order by p_id desc limit ?,? ";
		} else {
			// 按状态查询
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
	 * 专家分页查询方法
	 * @param status
	 * @param page
	 * @param uid
	 * @return 查询结果集合
	 */
	public List<Project> expertlistByPage(int status, int page, int uid) {// page代表当前页
		List<Project> list = new ArrayList<Project>();
		// 开始行数，表示每页第一条记录在数据库中的行数
		int rowBegin = 0; 
		if (page > 1) {
			// 按页数取得开始行数，设每页可以显示8条记录
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// 查询所有
			sql = "select  *  from project where p_teacher=? order by p_id desc limit ?,?";
		} else {
			// 按状态查询
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
	 * 通过id获取项目
	 * @param pid
	 * @return 查询到的结果，为一个Project对象
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return p;
	}

	/**
	 * 查询指导教师个人项目总数
	 * @param pTeacherUid
	 * @return 查询结果行数
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
			// 释放资源
			dbcon.close(rs, ps, con);
		}
		return lineCount;
	}

	/**
	 * 分页查询方法
	 * @param status
	 * @param page
	 * @return 查询结果集合
	 */
	public List<Project> listByPage(int status, int page) {// page代表当前页
		List<Project> list = new ArrayList<Project>();
		// 开始行数，表示每页第一条记录在数据库中的行数
		int rowBegin = 0; 
		if (page > 1) {
			// 按页数取得开始行数，设每页可以显示8条记录
			rowBegin = 8 * (page - 1); 
		}
		String sql = null;
		if (status == 0) {
			// 查询所有
			sql = "select  *  from project order by p_id desc limit ?,? ";
		} else {
			// 按状态查询
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
