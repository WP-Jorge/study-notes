package cn.cslg.applysystem.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserWithRoleVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String username;
	
	private String sex;
	
	private Integer age;
	
	private String name;
	
	private String tel;
	
	private String email;
	
	private Integer isAdmin;
	
	private Integer status;
	
	private MajorVO major;
	
	private List<RoleVO> role;
}
