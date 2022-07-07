package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Data
@TableName("album")
@ApiModel(value = "Album对象", description = "")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("专辑id")
    @TableId("album_id")
    private Long albumId;

    @ApiModelProperty("专辑名称")
    @TableField("album_name")
    private String albumName;
    
    @ApiModelProperty("专辑图片")
    @TableField("album_pic")
    private String albumPic;
    
    @ApiModelProperty("专辑介绍")
    @TableField("album_description")
    private String albumDescription;

    @ApiModelProperty("专辑总播放量")
    @TableField("total_views")
    private Integer totalViews;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;
    
}
