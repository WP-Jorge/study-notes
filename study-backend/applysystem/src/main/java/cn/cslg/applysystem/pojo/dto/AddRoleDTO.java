package cn.cslg.applysystem.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value="AddRoleDTO对象", description="接收由前端传输到后端的Role数据对象")
public class AddRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    @Length(min = 1, max = 10, message = "角色名格式错误，长度必须在1-10之间")
    @NotBlank(message = "角色名不能为空")
    private String roleName;

}
