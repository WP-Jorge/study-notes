package com.example.boxmusic.pojo.entity;

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
@TableName("singer")
@ApiModel(value = "Singer对象", description = "")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("歌手id")
    @TableId("singer_id")
    private Long singerId;

    @ApiModelProperty("歌手名称")
    @TableField("singer_name")
    private String singerName;
    
    @ApiModelProperty("歌手图片")
    @TableField("singer_pic")
    private String singerPic;

    @ApiModelProperty("歌手歌曲总播放量")
    @TableField("total_views")
    private Integer totalViews;

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
