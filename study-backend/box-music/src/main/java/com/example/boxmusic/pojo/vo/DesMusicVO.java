package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DesMusicVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long musicId;
	
	private String musicTitle;
	
	private String lyric;
	
	private Long albumId;
	
	private Double duration;
	
	private Integer size;
	
	private String level;
	
	private String musicFormat;
	
	private Integer bitrate;
	
	private String musicUrl;
	
	private String albumName;
	
	private String albumPic;
	
	private String albumDescription;
	
	private String categoryId;
	
	private String categoryName;
	
	private String categoryType;
	
	private String singerId;
	
	private String singerName;
	
	private String singerPic;
}
