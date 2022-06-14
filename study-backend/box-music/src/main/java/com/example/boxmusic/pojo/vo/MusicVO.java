package com.example.boxmusic.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.boxmusic.pojo.entity.Singer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class MusicVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long musicId;
	
	private String musicTitle;
	
	private String musicPic;
	
	private String lyric;
	
	private String album;
	
	private String genre;
	
	private Double duration;
	
	private Integer size;
	
	private String level;
	
	private String musicFormat;
	
	private Integer bitrate;
	
	private Integer totalViews;
	
	private String musicUrl;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer deleted;
	
	private List<CategoryVO> categories;
	
	private List<SingerVO> singers;
	
}
