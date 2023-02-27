package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CategoryMusicCountsVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String categoryName;
	
	private Integer num;
	
}
