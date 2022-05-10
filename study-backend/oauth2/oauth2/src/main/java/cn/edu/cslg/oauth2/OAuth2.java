package cn.edu.cslg.oauth2;

import cn.edu.cslg.security.WxAesUtil;
import cn.edu.cslg.util.HexBinTool;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * OAuth2认证和真实身份获取接口
 */
@WebServlet(name = "oauth2", urlPatterns = "/oauth2")
public class OAuth2 extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * OAuth2认证和真实身份获取接口
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        if (unionid == null || unionid.trim().equals("")) {
            String redirectUrl = request.getRequestURL().toString().split("\\?")[0];
            String pattern = "http://student.cslg.edu.cn/application/portal?redirect_url=%s&scope=%s";
            // String portalUrl = String.format(pattern, "www.baidu.com", session.getAttribute("scope"));
            String portalUrl = String.format(pattern, redirectUrl, "1");
            response.sendRedirect(portalUrl);
            return;
        }

        String scope = request.getParameter("scope");
        // String oauth_session_scope = (String) session.getAttribute("scope");
        String oauth_session_scope = "1";
        if (scope == null || oauth_session_scope == null || !scope.equals(oauth_session_scope)) {
            throw new ServletException("非法访问");
        }

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
            byte cb[] = WxAesUtil.getInstance().encryptZeroPadding(requestData, appid, appSecret);
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
                for (String s : person.keySet()) {
                    System.out.println((String) person.get(s));
                }
                //返回的JSON中包含unionid,person_code,real_name,nickname,identify,status等字段
                // String personCode = (String) person.get("person_code");
                // String personName = (String) person.get("real_name");
                // session.setAttribute("PersonCode", personCode);
                // session.setAttribute("PersonName", personName);
                
                
                
                //重定向到系统首页
                // this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("非法访问");
            }
        }
    }
}
