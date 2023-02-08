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
@TableName("collection")
@ApiModel(value = "Collection对象", description = "")
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏id")
    @TableId("collection_id")
    private Long collectionId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;
    
    @ApiModelProperty("音乐id")
    @TableField("music_id")
    private Long musicId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;


}
