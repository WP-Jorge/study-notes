package cn.cslg.applysystem.security;

import cn.cslg.applysystem.exception.MyAuthenticationException;
import cn.cslg.applysystem.exception.MyAuthorizationException;
import cn.cslg.applysystem.utils.JwtTokenUtil;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.utils.RedisUtil;
import cn.cslg.applysystem.utils.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class JWTAuthorizationFIlter extends OncePerRequestFilter {
	
	@Qualifier("myUserDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Qualifier("myJSONAuthentication")
	@Autowired
	private JSONAuthentication jsonAuthentication;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		// 获取token
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		// 查看token是否存在
		if (!(headerToken == null || "".equals(headerToken))) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			// 判断令牌是否过期，默认是一周
			// 比较好的解决方案是：
			// 登录成功获得token后，将token存储到数据库（redis）
			// 将数据库版本的token设置过期时间为15~30分钟
			// 如果数据库中的token版本过期，重新刷新获取新的token
			// 注意：刷新获得新token是在token过期时间内有效
			// 如果token本身的过期（1周），强制登录，生成新token
			boolean check = false;
			try {
				check = this.jwtTokenUtil.isTokenExpired(token);
				if (check) {
					R result = R.unauthorized("token过期，请重新登录");
					jsonAuthentication.WriteJSON(httpServletRequest, httpServletResponse, result);
					return;
				}
			} catch (Exception e) {
				// new Throwable("令牌已过期，请重新登录。" + e.getMessage());
				R result = R.unauthorized("token过期，请重新登录");
				jsonAuthentication.WriteJSON(httpServletRequest, httpServletResponse, result);
				return;
			}
			if (!check) {
				String username = jwtTokenUtil.getUsernameFromToken(token);
				String redisToken = redisUtil.getToken(username);
				if (redisToken == null || "".equals(redisToken) || !redisToken.equals(token)) {
					R result = R.unauthorized("token无效，请重新登录");
					jsonAuthentication.WriteJSON(httpServletRequest, httpServletResponse, result);
					return;
				}
				// if (!redisToken.equals(token)) {
				// 	throw new MyAuthorizationException(403, "token无效，请重新登录");
				// 	// new Throwable("token无效，请重新登录");
				// }
				// 判断用户不为空，且SecurityContextHolder授权信息还是空的
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					// 通过用户信息得到UserDetails
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					// 验证令牌有效性
					boolean validata = false;
					try {
						validata = jwtTokenUtil.validateToken(token, userDetails);
					} catch (Exception e) {
						// new Throwable("验证token无效:" + e.getMessage());
						R result = R.unauthorized("token验证无效，请重新登录");
						jsonAuthentication.WriteJSON(httpServletRequest, httpServletResponse, result);
						return;
					}
					if (validata) {
						// 将用户信息存入 authentication 方便以后使用
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
						// 将authentication 存入 ThreadLocal 方便后续获取用户信息
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
				}
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
