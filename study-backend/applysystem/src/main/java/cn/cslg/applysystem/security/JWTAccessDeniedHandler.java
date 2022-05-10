package cn.cslg.applysystem.security;

import cn.cslg.applysystem.utils.R;
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
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    
    @Qualifier("myJSONAuthentication")
    @Autowired
    private JSONAuthentication jsonAuthentication;
    
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //装入token
        // R<String> data = R.failed("权限不足:"+accessDeniedException.getMessage());
        
        R r = R.unauthorized("权限不足，请联系管理员");
        
        //输出
        jsonAuthentication.WriteJSON(request, response, r);
    }
}
