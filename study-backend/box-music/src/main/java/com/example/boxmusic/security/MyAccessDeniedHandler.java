package com.example.boxmusic.security;

import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    
    @Qualifier("jsonUtil")
    @Autowired
    private JsonUtil jsonUtil;
    
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R r = R.warning("权限不足，请联系管理员");
        jsonUtil.WriteJSON(request, response, r);
    }
}
