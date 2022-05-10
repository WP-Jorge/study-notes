package cn.cslg.applysystem.security;

import cn.cslg.applysystem.utils.JwtTokenUtil;
import cn.cslg.applysystem.utils.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 封装输出JSON格式的类
 */
@Component("myJSONAuthentication")
public class JSONAuthentication {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 输出JSON
     * @param request
     * @param response
     * @param data
     * @throws IOException
     * @throws ServletException
     */
    public void WriteJSON(HttpServletRequest request,
                             HttpServletResponse response,
                             Object data) throws IOException, ServletException {
        //这里很重要，否则页面获取不到正常的JSON数据集
        response.setContentType(Value.CONTENT_TYPE_JSON_UTF8);
        response.setHeader(Value.ACCESS_CONTROL_ALLOW_ORIGIN, Value.WILDCARD_ALL);
        response.setHeader(Value.ACCESS_CONTROL_ALLOW_METHOD, Value.METHOD_POST_GET);
        //输出JSON
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(data));
        out.flush();
        out.close();
    }
}
