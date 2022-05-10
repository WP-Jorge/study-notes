package com.example.oauth2_demo.security;

import com.example.oauth2_demo.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份校验失败处理器，如 token 错误
 */
@Component
public class JWTAuthenticationEntryPoint extends JSONAuthentication  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        R r = R.unauthorized("访问此资源需要进行身份验证（" + authException.getMessage() + "）！");
        
        // R<String> data = R.failed("访问此资源需要完全身份验证（"+authException.getMessage()+"）！");
        //输出
        this.WriteJSON(request, response, r);
    }
}
