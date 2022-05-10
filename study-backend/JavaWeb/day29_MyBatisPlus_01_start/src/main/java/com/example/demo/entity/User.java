package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	// 对应数据库中的主键（uuid、自增id、雪花算法、redis、zookeeper）
	/**
	 * AUTO：数据库ID自增,数据库需要支持主键自增(如MySQL)，并设置主键自增
	 * NONE：该类型为未设置主键类型,默认使用雪花算法生成
	 * INPUT：用户输入ID,数据类型和数据库保持一致就行，该类型可以通过自己注册自动填充插件进行填充
	 * ID_WORKER：全局唯一ID (idWorker),数值类型  数据库中也必须是数值类型 否则会报错
	 * ID_WORKER_STR：字符串全局唯一ID (idWorker 的字符串表示)，数据库也要保证一样字符类型
	 *  UUID；全局唯一ID (UUID，不含中划线)
	 */
	
	// 设置自增
	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private Integer age;
	private String email;
	
	// 字段添加填充内容
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
	// 1.添加注解，@Version申明乐观锁
	// 2.注册组件
	@Version
	private Integer version;
	
	// 逻辑删除
	@TableLogic
	private Integer deleted;
	
	public User(long id, String name, Integer age, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public User(String name, Integer age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
}
