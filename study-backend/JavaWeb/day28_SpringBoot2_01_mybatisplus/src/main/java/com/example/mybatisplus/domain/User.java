package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 如果数据库中的表明不与这个实体类中的类名一致，可使用@TableName("数据库中的表名")
 */
public class User {
	/**
	 * 所有属性都应该在数据库中有
	 * 使用@TableField(exist = false)注解申明字段在数据库中不存在
	 */
	@TableField(exist = false)
	private String email;
	@TableField(exist = false)
	private String password;
	
	private Integer id;
	private String name;
}
