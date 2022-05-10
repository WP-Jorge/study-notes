package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("playlist")
@ApiModel(value = "Playlist对象", description = "")
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("歌单id")
    @TableId("playlist_id")
    private Long playlistId;

    @ApiModelProperty("歌单名称")
    @TableField("playlist_name")
    private String playlistName;
    
    @ApiModelProperty("歌单封面")
    @TableField("playlist_pic")
    private String playlistPic;
    
    @ApiModelProperty("所属用户")
    @TableField("user_id")
    private Long userId;
    
    @ApiModelProperty("是否公开（0：未公开，1：公开）")
    @TableField("opened")
    private Integer opened;
    
    @ApiModelProperty("歌单总浏览量")
    @TableField("total_views")
    private Integer totalViews;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;


}
