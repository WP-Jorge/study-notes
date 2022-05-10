package jdbc_operation;

import jdbc_utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class Business_JDBC {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		try {
			// 1.获取连接
			connection = JDBCUtils.getConnection();
			
			// 开始事务
			connection.setAutoCommit(false);
			
			// 2.定义sql
			// 2.1 张三 - 500
			String sql1 = "update account set balance - ?";
			// 2.2 李四 + 500
			String sql2 = "update account set balance + ?";
			// 3.获取执行对象
			preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement2 = connection.prepareStatement(sql2);
			// 4.设置参数
			preparedStatement1.setDouble(1, 500);
			preparedStatement1.setInt(2, 1);
			preparedStatement2.setDouble(1, 500);
			preparedStatement2.setInt(2, 2);
			// 5.执行sql语句
			preparedStatement1.executeUpdate();
			// 6. 手动制造异常
			int i = 3 / 0; // 张三转账500给李四，由于这个错误，李四没有得到那个500
			preparedStatement2.executeUpdate();
			
			// 提交错误
			connection.commit();
		} catch (Exception throwables) {
			// 事务的回滚
			try {
				if (connection != null) {
					// 如果发生异常，则回滚到初始状态
					connection.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throwables.printStackTrace();
		} finally {
			JDBCUtils.close(preparedStatement1, connection);
			JDBCUtils.close(preparedStatement2, null);
			
		}
	}
}
