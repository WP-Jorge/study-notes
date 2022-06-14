package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateRoleDTO对象", description="接收由前端传输到后端的 Role 数据对象")
public class UpdateRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty("角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @NotBlank(message = "角色名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{2,30})$", message = "角色名格式错误")
    @ApiModelProperty(value = "角色名")
    private String roleName;
    
}
