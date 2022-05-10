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
@TableName("music_playlist")
@ApiModel(value = "MusicPlaylist对象", description = "")
public class MusicPlaylist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("音乐歌单id")
    @TableId("music_playlist_id")
    private Long musicPlaylistId;

    @ApiModelProperty("音乐id")
    @TableField("music_id")
    private Long musicId;

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
