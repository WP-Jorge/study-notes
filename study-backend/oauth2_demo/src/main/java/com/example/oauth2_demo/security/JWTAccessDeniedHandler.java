package com.example.oauth2_demo.security;

import com.example.oauth2_demo.security.JSONAuthentication;
import com.example.oauth2_demo.utils.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限校验处理器
 */
@Component
public class JWTAccessDeniedHandler extends JSONAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //装入token
        // R<String> data = R.failed("权限不足:"+accessDeniedException.getMessage());
        
        R r = R.unauthorized("权限不足：" + accessDeniedException.getMessage());
        
        //输出
        this.WriteJSON(request, response, r);
    }
}
