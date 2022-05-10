package com.team001.config.auth;


import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败操作
 */
@Component
public class MyAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException{

        R<Object> data = new R<Object>();
//        if (e instanceof UsernameNotFoundException) {
//            //用户名不存在
//            data.setCode(-1);
//            data.setMsg("登录失败:"+e.getMessage());
//        }else if( e instanceof BadCredentialsException) {
//            //密码错误
//            data.setCode(-2);
//            data.setMsg("登录失败:"+e.getMessage());
//        }
//        else if (e instanceof LockedException) {
//            //账号锁定
//            data.setCode(-3);
//            data.setMsg("登录失败:"+e.getMessage());
//        } else if(e instanceof DisabledException){
//            //账号禁用
//            data.setCode(-4);
//            data.setMsg("登录失败:"+e.getMessage());
//        }
        data.setCode(-1);
        data.setMsg("登录失败:"+e.getMessage());

        //输出
        this.WriteJSON(request, response, data);
    }
}