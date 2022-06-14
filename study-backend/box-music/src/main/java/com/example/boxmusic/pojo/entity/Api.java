package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("api")
@ApiModel(value = "Api对象", description = "")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源id")
    @TableId(value = "api_id", type = IdType.AUTO)
    private Long apiId;

    @ApiModelProperty("父级资源id")
    @TableField("parent_api_id")
    private Long parentApiId;

    @ApiModelProperty("资源名称")
    @TableField("api_name")
    private String apiName;

    @ApiModelProperty("资源路径")
    @TableField("api_path")
    private String apiPath;

    @ApiModelProperty("请求方法")
    @TableField("api_method")
    private String apiMethod;

    @ApiModelProperty("资源类型（0：菜单，1：资源）")
    @TableField("api_type")
    private Integer apiType;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;
}
