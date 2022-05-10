package com.example.mp.config;

// import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
// import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// 自动管理事务
// @EnableTransactionManagement
// 扫面mapper文件夹
// @MapperScan("com.example.mp.mapper")
@Configuration
public class MybatisPlusConfig {
	// // 注册乐观锁插件
	// @Bean
	// public MybatisPlusInterceptor mybatisPlusInterceptor() {
	// 	MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
	// 	mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
	// 	return mybatisPlusInterceptor;
	// }
	//
	// // 配置MyBatisPlus分页插件
	// @Bean
	// public PaginationInterceptor paginationInterceptor() {
	// 	PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
	// 	// 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
	// 	// paginationInterceptor.setOverflow(false);
	// 	// 设置最大单页限制数量，默认 500 条，-1 不受限制
	// 	// paginationInterceptor.setLimit(500);
	// 	// 开启 count 的 join 优化,只针对部分 left join
	// 	paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
	// 	return paginationInterceptor;
	// }
	//
	
	// @Primary
	// @Bean(name = "sysSqlSessionFactory")
	// public SqlSessionFactory sysSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
	// 		throws Exception {
	// 	MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
	// 	bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
	// 	bean.setDataSource(dataSource);
	// 	return bean.getObject();
	// }
	
	
}
