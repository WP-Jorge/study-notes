package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MusicLevelCountsVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String level;
	
	private Integer num;
	
}
