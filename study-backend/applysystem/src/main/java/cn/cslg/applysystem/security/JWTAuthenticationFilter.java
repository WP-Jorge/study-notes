package cn.cslg.applysystem.security;

import cn.cslg.applysystem.exception.MyAuthenticationException;
import cn.cslg.applysystem.exception.MyPasswordErrorException;
import cn.cslg.applysystem.service.UserService;
import cn.cslg.applysystem.utils.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	UserService userService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (request.getContentType().equals(Value.CONTENT_TYPE_JSON_UTF8) || request.getContentType().equals(Value.CONTENT_TYPE_JSON)) {
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
			// authenticationBean对象
			Map<String, String> authenticationBean = null;
			try (InputStream is = request.getInputStream()) {
				// 将用户认证数据转换为Map对象
				authenticationBean = mapper.readValue(is, Map.class);
			} catch (IOException e) {
				throw new MyAuthenticationException(e.getMessage());
			}
			// 获取authenticationBean成功后将用户名密码交给UsernamePasswordAuthenticationToken管理
			try {
				if (!authenticationBean.isEmpty()) {
					// 获取账号密码
					String username = authenticationBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
					String password = authenticationBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
					String isAdmin = authenticationBean.get(Value.IS_ADMIN);
					// 调用自己的service登录方法 检查账号密码是否正确
					Boolean isChecked = userService.checkLogin(username, password, Integer.parseInt(isAdmin));
					if (isChecked) {
						// 验证正确后将UsernamePasswordAuthenticationToken交给security管理
						usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
						setDetails(request, usernamePasswordAuthenticationToken);
						return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
					}
				}
			} catch (MyPasswordErrorException e) {
				throw new MyPasswordErrorException(e.getMessage());
			} catch (Exception e) {
				throw new MyAuthenticationException(e.getMessage());
			}
			return null;
		} else {
			return this.attemptAuthentication(request, response);
		}
	}
}
