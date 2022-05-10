package com.example.boxmusic.security;

import com.example.boxmusic.service.auth.MyUserDetails;
import com.example.boxmusic.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthorizationFilter extends OncePerRequestFilter {
	
	@Qualifier("myUserDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Qualifier("jsonUtil")
	@Autowired
	private JsonUtil jsonUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		// 获取 token
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		// 查看 token 是否存在
		if (!(headerToken == null || "".equals(headerToken))) {
			// postMan 测试时，自动加入的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			// 判断令牌是否过期，默认是一周
			// 比较好的解决方案是：
			// 登录成功获得 token 后，将 token 存储到数据库（redis）
			// 将数据库版本的 token 设置过期时间为 15~30 分钟
			// 如果数据库中的 token 版本过期，重新刷新获取新的 token
			// 注意：刷新获得新 token 是在 token 过期时间内有效
			// 如果 token 本身的过期（1周），强制登录，生成新 token
			boolean check = false;
			try {
				check = this.jwtTokenUtil.isTokenExpired(token);
				if (check) {
					R result = R.warning("token 过期，请重新登录");
					httpServletRequest.setAttribute("result", result);
					filterChain.doFilter(httpServletRequest, httpServletResponse);
					return;
				}
			} catch (Exception e) {
				// new Throwable("令牌已过期，请重新登录。" + e.getMessage());
				R result = R.warning("token 过期，请重新登录");
				httpServletRequest.setAttribute("result", result);
				filterChain.doFilter(httpServletRequest, httpServletResponse);
				return;
			}
			if (!check) {
				String username = jwtTokenUtil.getUsernameFromToken(token);
				String redisToken = redisUtil.getToken(username);
				if (redisToken == null || "".equals(redisToken) || !redisToken.equals(token)) {
					R result = R.warning("token 无效，请重新登录");
					httpServletRequest.setAttribute("result", result);
					filterChain.doFilter(httpServletRequest, httpServletResponse);
					return;
				}
				// if (!redisToken.equals(token)) {
				// 	throw new MyAuthorizationException(403, "token无 效，请重新登录");
				// 	// new Throwable("token无效，请重新登录");
				// }
				// 判断用户不为空，且 SecurityContextHolder 授权信息还是空的
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					// 通过用户信息得到 UserDetails
					MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(username);
					// 验证令牌有效性
					boolean validata = false;
					try {
						validata = jwtTokenUtil.validateToken(token, userDetails);
					} catch (Exception e) {
						// new Throwable("验证 token 无效:" + e.getMessage());
						R result = R.warning("token 验证无效，请重新登录");
						httpServletRequest.setAttribute("result", result);
						filterChain.doFilter(httpServletRequest, httpServletResponse);
						return;
					}
					if (validata) {
						// 将用户信息存入 authentication 方便以后使用
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
						// 将 authentication 存入 ThreadLocal 方便后续获取用户信息
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
				}
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
