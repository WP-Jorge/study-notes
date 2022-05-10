package cn.cslg.applysystem.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
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
@ApiModel(value="UpdatePasswordDTO对象", description="接收由前端传输到后端更新User密码的数据对象")
public class UpdatePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Null(message = "无需传递用户名")
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @NotBlank(message = "原密码不能为空")
    @Pattern(regexp = "^[\\w]{6,12}$", message = "原密码格式不正确，需满足6-12个数字字母下划线")
    @ApiModelProperty(value = "原密码")
    private String rawPassword;
    
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^[\\w]{6,12}$", message = "新密码格式不正确，需满足6-12个数字字母下划线")
    @ApiModelProperty(value = "新密码")
    private String password;
}
