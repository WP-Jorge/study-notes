package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
	
	private static SqlSessionFactory factory = null;
	
	static {
		try {
			String config = "mybatis.xml"; // 需要和项目中的文件名一致
			InputStream in = Resources.getResourceAsStream(config);
			
			// 创建SqlSession对象，使用SqlSessionFactoryBuild
			factory = new SqlSessionFactoryBuilder().build(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 获取不自动提交的SqlSession的方法
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		if (factory != null) {
			sqlSession = factory.openSession(); // 非自动提交事务
		}
		return sqlSession;
	}
	
	// 获取自定义提交的SqlSession的方法
	public static SqlSession getSqlSession(Boolean auto) {
		SqlSession sqlSession = null;
		if (factory != null) {
			sqlSession = factory.openSession(auto); // 非自动提交事务
		}
		return sqlSession;
	}
}
