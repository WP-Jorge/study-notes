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
@TableName("category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("歌曲分类id")
    @TableId("category_id")
    private Long categoryId;

    @ApiModelProperty("歌曲分类名称")
    @TableField("category_name")
    private String categoryName;
    
    @ApiModelProperty("歌曲分类类型")
    @TableField("category_type")
    private Integer categoryType;
    
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;


}
