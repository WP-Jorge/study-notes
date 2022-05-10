package com.team001.vo;

import lombok.Data;

@Data
public class UserLogin {

    /**
     * 学号或教师好
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
