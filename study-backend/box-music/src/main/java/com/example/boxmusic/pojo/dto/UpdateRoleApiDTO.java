package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateRoleApiDTO对象", description="接收由前端传输到后端的 RoleApi 数据对象")
public class UpdateRoleApiDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty("角色id")
    private Long roleId;
    
    @NotNull(message = "权限id不能为空")
    @ApiModelProperty("权限id")
    private Long apiId;

}
