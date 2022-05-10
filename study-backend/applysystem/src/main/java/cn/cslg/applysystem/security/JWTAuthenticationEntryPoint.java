package cn.cslg.applysystem.security;

import cn.cslg.applysystem.utils.R;
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
 * 身份校验失败处理器，如 token 错误
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    @Qualifier("myJSONAuthentication")
    @Autowired
    private JSONAuthentication jsonAuthentication;
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        R r = R.unauthorized("访问此资源需要进行身份验证");
        
        // R<String> data = R.failed("访问此资源需要完全身份验证（"+authException.getMessage()+"）！");
        //输出
        jsonAuthentication.WriteJSON(request, response, r);
    }
    
    
}
