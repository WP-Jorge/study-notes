package cn.cslg.applysystem.controller.wx;

import cn.cslg.applysystem.service.RoleService;
import cn.cslg.applysystem.service.UserService;
import cn.cslg.applysystem.service.auth.MyUserDetails;
import cn.cslg.applysystem.utils.JwtTokenUtil;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.utils.RedisUtil;
import cn.cslg.applysystem.utils.websocket.SocketServer;
import cn.cslg.applysystem.utils.wx.HexBinTool;
import cn.cslg.applysystem.utils.wx.OAuthHttpRequest;
import cn.cslg.applysystem.utils.wx.WxAesUtil;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WXController {
	
	private static String SCOPE = "weChatScope";
	
	@Autowired
	MyUserDetails userDetails;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	UserService userService;
	
	@ApiOperation("微信登录")
	@GetMapping("/wx/{userName}")
	public void wx(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "userName") String userName) throws IOException, ServletException {
		
		/*如果用户未登陆，session中没有unionid等信息，则跳到扫码认证界面
        扫描完成之后回到本Servlet，在参数中带回unionid和scope
        */
		// HttpSession session = request.getSession(false);
		// if (session.getAttribute("scope") == null) {
		//     // session.setAttribute("scope", UUID.randomUUID().toString());
		//     session.setAttribute("scope", "1");
		//
		// }
		String unionid = request.getParameter("unionid");
		if (unionid == null || "".equals(unionid.trim())) {
			String redirectUrl = request.getRequestURL().toString().split("\\?")[0];
			String pattern = "http://student.cslg.edu.cn/application/portal?redirect_url=%s&scope=%s";
			// String portalUrl = String.format(pattern, "www.baidu.com", session.getAttribute("scope"));
			String portalUrl = String.format(pattern, redirectUrl, SCOPE);
			response.sendRedirect(portalUrl);
			return ;
		}
		
		String scope = request.getParameter("scope");
		// String oauth_session_scope = (String) session.getAttribute("scope");
		// String oauth_session_scope = "1";
		if (scope == null || !scope.equals(SCOPE)) {
			throw new ServletException("非法访问");
		}
		// String username = request.getParameter("username");
		
		//信息办提供的加密用appid和appsecret
		String appid = "2018112932502446";
		String appSecret = "2018112989415602";
		
		//将时间戳和unionid组织成JSON并加密
		JSONObject json = new JSONObject();
		json.put("unionid", unionid);
		json.put("stamp", String.valueOf(System.currentTimeMillis() / 1000));
		String requestData = json.toJSONString().trim();
		String cipher = null;
		try {
			byte[] cb = WxAesUtil.getInstance().encryptZeroPadding(requestData, appid, appSecret);
			cipher = HexBinTool.encodeHexString(cb);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		//调用常熟理工学院OAuth2平台API进行真实身份获取
		String oauthUrl = "http://student.cslg.edu.cn/api/oauth2api/GetUserByAes";
		String result = new OAuthHttpRequest().get(oauthUrl, appid, cipher);
		
		JSONObject oauthResult = JSONObject.parseObject(result);
		if ((Integer) oauthResult.get("rep_code") == -1) {
			throw new ServletException((String) oauthResult.get("rep_message"));
		} else {
			try {
				//对返回的数据进行AES解密和JSON解码
				String repCipher = (String) oauthResult.get("rep_data");
				String plain = WxAesUtil.getInstance().decryptZeroPadding(repCipher, appid, appSecret, false);
				JSONObject person = JSONObject.parseObject(plain);
				// for (String s : person.keySet()) {
				// 	System.out.println(s);
				// 	System.out.println((String) person.get(s));
				// }
				//返回的JSON中包含unionid,person_code,real_name,nickname,identify,status等字段
				String personCode = (String) person.get("person_code");
				// String personName = (String) person.get("real_name");
				// session.setAttribute("PersonCode", personCode);
				// session.setAttribute("PersonName", personName);
				
				List<String> roles = roleService.getRolesByUsername(personCode);
				
				List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
				for (String role : roles) {
					authorities.add(new SimpleGrantedAuthority(role));
				}
				
				userDetails.setUsername(personCode);
				userDetails.setAuthorities(authorities);
				
				// 判断缓存中是否有token 有则刷新token，没有则生成
				String token = redisUtil.getToken(userDetails);
				if (token != null) {
					redisUtil.refreshToken(userDetails, token);
				} else {
					token = jwtTokenUtil.generateToken(userDetails);
				}
				redisUtil.pushToken(userDetails, token);
				// System.out.println(userDetails);
				// String token = (String) redisTemplate.opsForValue().get(userName);
				System.out.println(token);
				
				// 将token放入map中
				Page<Map> page = new Page<Map>(1, 1);
				UserVO userInfo = userService.getUserInfoByUsername(userDetails.getUsername());
				HashMap<String, Object> map = new HashMap<>();
				map.put("username", userDetails.getUsername());
				map.put("auth", userDetails.getAuthorities());
				map.put("userInfo", userInfo);
				map.put("token", token);

				R r = R.ok(map);
				
				
				/************websocket将数据响应给前端**************/
				
				JSONObject jsonObject = new JSONObject(map);
				
				// redisTemplate.opsForValue().set(userDetails.getUsername(), token);
				
				SocketServer.sendMessage(jsonObject.toJSONString(jsonObject), userName);
				// response.sendRedirect("http://");
				
				// return r;
				
				// 重定向到系统首页
				// this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (Exception e) {
				throw new ServletException("非法访问");
			}
		}
	}
}
