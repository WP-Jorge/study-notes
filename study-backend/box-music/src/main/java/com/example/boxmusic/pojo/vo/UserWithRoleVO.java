package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserWithRoleVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String username;
	
	private List<RoleVO> roleList;
}
