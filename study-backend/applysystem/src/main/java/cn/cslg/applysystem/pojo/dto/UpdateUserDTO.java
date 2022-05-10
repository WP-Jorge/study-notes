package cn.cslg.applysystem.pojo.dto;

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
@ApiModel(value="UpdateUserDTO对象", description="接收由前端传输到后端更新User信息的数据对象")
public class UpdateUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^(Z[0-9]{8})|(ZB[0-9]{7})|([0-9]{9})$", message = "用户名格式错误")
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @NotNull(message = "专业id不能为空")
    @ApiModelProperty(value = "专业id")
    private Integer majorId;
    
    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "[男|女]{1}", message = "性别格式错误，可选“男”或“女”")
    @ApiModelProperty(value = "性别")
    private String sex;
    
    @NotNull(message = "年龄不能为空")
    @Range(min = 10, max = 120, message = "年龄需要在10-120之内")
    @ApiModelProperty(value = "年龄")
    private Integer age;
    
    @NotBlank(message = "姓名不能为空")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{2,4}", message = "姓名必须为2-4位的汉字")
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @Pattern(regexp = "^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$", message = "手机号码格式错误")
    @ApiModelProperty(value = "手机号码")
    private String tel;
    
    @Email(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @NotNull(message = "是否是管理员不能为空")
    @Range(min = 0, max = 1, message = "isAdmin格式错误，可选0或1")
    @ApiModelProperty(value = "是否是管理员（0：不是，1：是）")
    private Integer isAdmin;
    
    @NotNull(message = "用户状态不能为空")
    @Range(min = 0, max = 1, message = "用户状态格式错误，可选0或1")
    @ApiModelProperty(value = "用户状态（0：冻结，1：正常）")
    private Integer status;
    
}
