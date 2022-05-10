package com.example.boxmusic.security;

import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.ApiService;
import com.example.boxmusic.service.RoleService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.service.auth.MyUserDetails;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.JwtTokenUtil;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 登录成功进行的操作
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Qualifier("jsonUtil")
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	RoleService roleService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		
		// 获取账号信息
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// 取 token
		// 好的解决方案，登录成功后 token 存储到数据库中
		// 只要 token 还在过期内，不需要每次重新生成
		// 先去缓存中找
		String token = redisUtil.getToken(userDetails);
		// String token = TokenCacheUtils.getTokenFromCache(userDetails.getUsername());
		if (token == null) {
			System.out.println("初次登录，缓存中没有token，生成token");
			// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
			token = jwtTokenUtil.generateToken(userDetails);
			// 将token存入缓存中
			// redisTemplate.opsForValue().set(userDetails.getUsername(), token);
			// TokenCacheUtils.setToken(userDetails.getUsername(), token);
		} else {
			// token = jwtTokenUtil.refreshToken(token);
			// redisTemplate.opsForValue().set(userDetails.getUsername(), token);
			// redisUtil.pushToken(userDetails, token);
			token = redisUtil.refreshToken(userDetails, token);
		}
		redisUtil.pushToken(userDetails, token);
		
		// 将token封装成json数据发送给前端
		UserVO userInfo = userService.getUserInfoWithUsername(userDetails.getUsername());
		// List<ApiVO> apis = apiService.getApisWithUsername(userDetails.getUsername());
		// List<RoleVO> roles = roleService.getRolesWithUsername(userDetails.getUsername());
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("roles", userDetails.getRoles());
		map.put("apis", userDetails.getApis());
		map.put("userInfo", userInfo);
		map.put("token", token);
		R r = R.success("登录成功", map);
		// 输出
		jsonUtil.WriteJSON(httpServletRequest, httpServletResponse, r);
	}
	
}
