package cn.edu.cslg.oauth2;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供方法对常熟理工微信认证平台进行调用
 */
public class OAuthHttpRequest {

    /**
     * 使用GET请求对常熟理工微信认证平台进行调用
     *
     * @param url     请求的URL
     * @param appid   appid用于加密
     * @param reqData 加密的请求数据
     * @return 包含真实身份信息结果（加密，需要在调用方进行解密）的JSON字符串。如果出现错误或异常，rep_code为-1，
     * rep_message为错误信息；如果运行成功，rep_code为0，rep_data为包含加密数据。
     */
    public String get(String url, String appid, String reqData) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", appid));
        params.add(new BasicNameValuePair("req_data", reqData));
        try {
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.ASCII));
            HttpGet httpGet = new HttpGet(url + "?" + str);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            } else {
                JSONObject errmsg = new JSONObject();
                errmsg.put("rep_code", -1);
                errmsg.put("rep_message", "No response data");
                return errmsg.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject errmsg = new JSONObject();
            errmsg.put("rep_code", -1);
            errmsg.put("rep_message", e.toString());
            return errmsg.toJSONString();
        } finally {
            //关闭连接,释放资源
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
