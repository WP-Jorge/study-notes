package com.example.boxmusic.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddRoleDTO对象", description="接收由前端传输到后端的 Role 数据对象")
public class AddRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "角色名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{2,30})$", message = "角色名格式错误")
    @ApiModelProperty(value = "角色名")
    private String roleName;
    
}
