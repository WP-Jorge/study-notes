package com.team001.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * <p>
 * 
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("m_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ExcelIgnore
    @TableId(value = "uno", type = IdType.AUTO)
    private Integer uno;

    /**
     * 学号或教师好
     */
    @NotBlank(message = "用户名不能为空")
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelIgnore
    private String password;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    @ExcelProperty("姓名")
    private String name;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 系
     */
    @ExcelProperty("系")
    private String dept;

    /**
     * 用户状态：1可用，0冻结
     */
    @ExcelIgnore
    private Integer enabled;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private LocalDateTime createTime;

    /**
     * 用户手机
     */
    @ExcelProperty("手机")
    @NotBlank(message = "手机不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$",message = "手机格式错误")
    private String phone;

    /**
     * 登陆次数
     */
    @ExcelIgnore
    private Integer login;

    @ExcelIgnore
    @TableField(exist = false)
     //一对多默认为延迟加载,即@Lazy/@Lazy(true)/或此时不标注
    private List<Role> roles;
}
