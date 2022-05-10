package com.example.mps.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private String email;
	
	// @TableField(fill = FieldFill.INSERT)
	private Date createTime;
	
	// @TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	
	// @Version
	private Integer version;
	
	// @TableLogic
	private Integer deleted;
}
