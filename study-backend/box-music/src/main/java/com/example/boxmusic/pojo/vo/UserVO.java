package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String username;
	
	private String sex;
	
	private Integer age;
	
	private String tel;
	
	private String email;
	
	private String userPic;
	
	private Integer status;
	
}
