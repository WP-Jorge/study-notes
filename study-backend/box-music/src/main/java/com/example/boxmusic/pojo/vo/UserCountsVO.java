package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCountsVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date day;
	
	private Integer num;
	
}
