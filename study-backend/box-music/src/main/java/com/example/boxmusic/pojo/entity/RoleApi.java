package com.example.boxmusic.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("role_api")
@ApiModel(value = "RoleApi对象", description = "")
public class RoleApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色接口id")
    @TableId(value = "role_api_id", type = IdType.AUTO)
    private Long roleApiId;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("请求接口id")
    @TableField("api_id")
    private Long apiId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;


}
