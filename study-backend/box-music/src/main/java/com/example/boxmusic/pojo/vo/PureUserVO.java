package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PureUserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String username;
	
}
