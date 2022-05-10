package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddUserDTO对象", description="接收由前端传输到后端的 User 数据对象")
public class UpdateUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5]{1,10})|(\\w{4,12})$", message = "用户名格式错误")
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[\\w]{6,12}$", message = "密码格式不正确，需满足6-12个数字字母下划线")
    @ApiModelProperty(value = "密码")
    private String password = "111111";
    
    // @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "(男|女|保密)", message = "性别格式错误，可选“男”或“女”或“保密”")
    @ApiModelProperty(value = "性别")
    private String sex;
    
    // @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 120, message = "年龄需要在0-120之内")
    @ApiModelProperty(value = "年龄")
    private Integer age;
    
    @Pattern(regexp = "^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$", message = "手机号码格式错误")
    @ApiModelProperty(value = "手机号码")
    private String tel;
    
    @Email(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "用户头像")
    private String userPic;
    
    @NotNull(message = "用户状态不能为空")
    @Range(min = 0, max = 1, message = "用户状态格式错误，可选 0 或 1 ")
    @ApiModelProperty(value = "用户状态（0：冻结，1：正常）")
    private Integer status = 1;
    
}
