package com.example.boxmusic.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

// 代码自动生成器
public class CodeGenerator {
	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:mysql://106.15.184.155/box_music?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111")
				.globalConfig(builder -> {
					builder.author("Jorge")
							.outputDir(System.getProperty("user.dir") + "/src/main/java")
							.enableSwagger()
							.commentDate("yyyy-MM-dd")
							.disableOpenDir()
							.enableSwagger()
							.dateType(DateType.ONLY_DATE);
				})
				.packageConfig(builder -> {
					builder.moduleName(null)
							.parent("com.example.boxmusic")
							.entity("entity")
							.mapper("mapper")
							.service("service")
							.controller("controller")
							.pathInfo((Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")));
				})
				.strategyConfig(builder -> {
					builder.addInclude()
							.entityBuilder()
							.enableLombok()
							.logicDeleteColumnName("deleted")
							.enableTableFieldAnnotation()
							.serviceBuilder()
							.formatServiceFileName("%sService")
							.formatServiceImplFileName("%sServiceImpl")
							.controllerBuilder()
							.formatFileName("%sController")
							.enableRestStyle()
							.mapperBuilder()
							.enableBaseResultMap()
							.superClass(BaseMapper.class)
							.formatMapperFileName("%sMapper")
							.enableMapperAnnotation()
							.formatXmlFileName("%sMapper");
				})
				.execute();
	}
}

