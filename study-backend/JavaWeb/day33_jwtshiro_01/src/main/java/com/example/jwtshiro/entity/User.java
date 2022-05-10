package com.example.jwtshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	// 给属性加注释
	// value: 描述 name：也可以是描述 required：是否必须 example：示例 hidden：是否隐藏
	private Integer userId;
	private String username;
	private String password;
	private String salt;
	
	// 定义角色集合
	@TableField(exist = false)
	private List<Role> roles;
	
}
