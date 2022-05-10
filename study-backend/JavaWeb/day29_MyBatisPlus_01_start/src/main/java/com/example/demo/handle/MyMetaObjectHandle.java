package com.example.demo.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 1、使用数据库的自动添加，给创建时间添加CURRENT_TIMESTAMP，更新时间添勾选根据当前时间戳更新
 * 2、使用自定义处理如下
 */
@Slf4j
@Component
public class MyMetaObjectHandle implements MetaObjectHandler {
	// 插入时的填充策略
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("开始执行插入填充");
		this.setFieldValByName("createTime", new Date(), metaObject);
		this.setFieldValByName("updateTime", new Date(), metaObject);
		
	}
	
	// 更新时的填充策略
	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("开始执行更新填充");
		this.setFieldValByName("updateTime", new Date(), metaObject);
	}
}
