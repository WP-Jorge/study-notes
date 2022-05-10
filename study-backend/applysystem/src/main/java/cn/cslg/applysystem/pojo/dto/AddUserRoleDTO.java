package cn.cslg.applysystem.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddUserRoleDTO对象", description="接收由前端传输到后端的UserRole数据对象")
public class AddUserRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

}
