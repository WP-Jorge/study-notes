package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user_playlist")
@ApiModel(value = "UserPlaylist对象", description = "")
public class UserPlaylist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户歌单id")
    @TableId("user_playlist_id")
    private Long userPlaylistId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("歌单id")
    @TableField("playlist_id")
    private Long playlistId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;


}
