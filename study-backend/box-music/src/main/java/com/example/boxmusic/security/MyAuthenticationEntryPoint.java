package com.example.boxmusic.security;

import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证处理器，没有进行认证会进入此处理器
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    @Qualifier("jsonUtil")
    @Autowired
    private JsonUtil jsonUtil;
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        R result = (R) request.getAttribute("result");
        if (result != null) {
            jsonUtil.WriteJSON(request, response, result);
            return;
        }
        R r = R.warning("访问此资源需要进行身份验证");
        jsonUtil.WriteJSON(request, response, r);
    }
}
