package com.example.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Jorge
 * @since 2021-01-27
 */
@Data
// @EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
