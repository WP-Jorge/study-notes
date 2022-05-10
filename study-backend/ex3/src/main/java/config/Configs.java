package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration // 1、配置类
@ComponentScan(basePackages = {"dao", "proxy", "service", "domain"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement // 3、开启事务
public class Configs {
	// 4、创建数据库连接池
	@Bean
	public DruidDataSource getDruidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///account?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
		dataSource.setUsername("root");
		dataSource.setPassword("111111");
		return dataSource;
	}
	
	// 5、创建JdbcTemplate对象
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		// 到ioc容器中根据类型找到dataSource
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// 6、注入dataSOurce
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
	
	// 7、创建事务管理器
	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		// 8、注入dataSource
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
}

