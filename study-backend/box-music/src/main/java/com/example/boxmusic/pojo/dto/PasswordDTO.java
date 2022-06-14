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
@ApiModel(value="PasswordDTO对象", description="接收由前端传输到后端的 User 数据对象")
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[\\w]{6,12}$", message = "密码格式不正确，需满足6-12个数字字母下划线")
    @ApiModelProperty(value = "密码")
    private String password;
    
}
