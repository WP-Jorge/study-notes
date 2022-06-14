package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Data
@TableName("music")
@ApiModel(value = "Music对象", description = "")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("音乐id")
    @TableId(value = "music_id", type = IdType.AUTO)
    private Long musicId;

    @ApiModelProperty("音乐名称")
    @TableField("music_title")
    private String musicTitle;

    @ApiModelProperty("音乐封面")
    @TableField("music_pic")
    private String musicPic;

    @ApiModelProperty("歌词")
    @TableField("lyric")
    private String lyric;
    
    @ApiModelProperty("专辑")
    @TableField("album")
    private String album;

    @ApiModelProperty("流派")
    @TableField("genre")
    private String genre;

    @ApiModelProperty("时长")
    @TableField("duration")
    private Double duration;

    @ApiModelProperty("大小")
    @TableField("size")
    private Integer size;

    @ApiModelProperty("音乐最高品质")
    @TableField("level")
    private String level;

    @ApiModelProperty("音乐最高品质格式")
    @TableField("music_format")
    private String musicFormat;

    @ApiModelProperty("音乐最高品质码率")
    @TableField("bitrate")
    private Integer bitrate;

    @ApiModelProperty("播放量")
    @TableField("total_views")
    private Integer totalViews;
    
    @ApiModelProperty("音乐地址")
    @TableField("music_url")
    private String musicUrl;
    
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
