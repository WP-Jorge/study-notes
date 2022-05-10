package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "用户实体类", description = "用户实体类描述")
public class User {
	// 给属性加注释
	// value: 描述 name：也可以是描述 required：是否必须 example：示例 hidden：是否隐藏
	@ApiModelProperty(value = "id", name = "id", required = false, example = "1")
	private Integer id;
	@ApiModelProperty(value = "用户名", name = "username", required = false, example = "野猪乔治")
	private String username;
	@ApiModelProperty(value = "密码", name = "password", required = false, example = "111111", hidden = false)
	private String password;
	@ApiModelProperty(value = "盐", name = "salt", required = false, example = "ashdkjad")
	private String salt;
}
