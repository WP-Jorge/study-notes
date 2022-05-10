package com.team001.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team001.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDetail {


    /**
     * 学号或教师好
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 系
     */
    private String dept;

    /**
     * 用户状态：1可用，0冻结
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 登陆次数
     */
    private Integer login;

    private List select;

}
