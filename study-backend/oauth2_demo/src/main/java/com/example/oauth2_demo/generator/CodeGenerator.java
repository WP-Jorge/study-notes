package com.example.oauth2_demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

// 代码自动生成器
public class CodeGenerator {
	public static void main(String[] args) {
		// 构建一个代码生成器对象
		AutoGenerator generator = new AutoGenerator();
		// 配置策略
		// 1.全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		// globalConfig.setOutputDir(projectPath + "/src/main/java");
		globalConfig.setOutputDir(projectPath + "/src/main/java");
		globalConfig.setAuthor("demo_team");
		globalConfig.setOpen(false);
		globalConfig.setFileOverride(false); // 是否覆盖
		globalConfig.setServiceName("%sService"); // 去掉Service的I前缀
		globalConfig.setIdType(IdType.AUTO);
		globalConfig.setDateType(DateType.ONLY_DATE);
		globalConfig.setSwagger2(true);
		
		// 2.设置数据源
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl("jdbc:mysql:///oauth2?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
		dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("111111");
		dataSourceConfig.setDbType(DbType.MYSQL);
		
		// 3.包的配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setModuleName("oauth2_demo");
		packageConfig.setParent("com.example");
		packageConfig.setEntity("entity");
		packageConfig.setMapper("mapper");
		packageConfig.setService("service");
		packageConfig.setController("controller");
		
		// 4.配置策略
		StrategyConfig strategy = new StrategyConfig();
		strategy.setInclude("user", "role", "permission", "user_role", "role_permission"); // 设置要映射的表名,多个可用", "分割
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true); // 自动lombok
		strategy.setRestControllerStyle(true); // 驼峰命名
		strategy.setLogicDeleteFieldName("deleted");// 配置逻辑删除
		// TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
		// TableFill updateTime = new TableFill("update_time", FieldFill.UPDATE);
		// ArrayList<TableFill> tableFills = new ArrayList<>();
		// tableFills.add(createTime);
		// tableFills.add(updateTime);
		// strategy.setTableFillList(tableFills);
		// strategy.setVersionFieldName("version"); // 乐观锁
		strategy.setControllerMappingHyphenStyle(true); // localhost:8080/hello_id_1
		
		// 5.加入配置到构造器
		generator.setStrategy(strategy);
		generator.setGlobalConfig(globalConfig);
		generator.setPackageInfo(packageConfig);
		generator.setDataSource(dataSourceConfig);
		
		// 执行代码构造器
		generator.execute();
	}
}

