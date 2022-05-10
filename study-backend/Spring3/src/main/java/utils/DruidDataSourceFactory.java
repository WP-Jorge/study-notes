package utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSourceFactory implements DataSourceFactory {
	private Properties props;
	@Override
	public DataSource getDataSource() {
		DruidDataSource dds = new DruidDataSource();
		dds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dds.setUrl("jdbc:mysql:///account?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
		dds.setUsername("root");
		dds.setPassword("111111");
		// 其他配置可以根据MyBatis主配置文件进行配置
		try {
			dds.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dds;
	}
	
	@Override
	public void setProperties(Properties props) {
		this.props = props;
	}
}