package org.whut.platform.fundamental.android.cas;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CasClient
{
    private static final String CAS_LOGIN_URL_PART = "tickets";
    private static final String CAS_LOGOUT_URL_PART = "tickets";

    private static final int REQUEST_TIMEOUT = 5*1000;//设置请求超时10秒钟
    private static final int SO_TIMEOUT = 10*1000;  //设置等待数据超时时间10秒钟

    private DefaultHttpClient httpClient;

    private String casBaseURL;

    public DefaultHttpClient getHttpClient()
    {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        return client;
    }

    public CasClient (String casBaseUrl)
    {
        this.httpClient = getHttpClient();
        this.casBaseURL = casBaseUrl;
    }

    public boolean login(String username,String password,String sessionGenerateService){
        String tgt = getTGT(username, password);
        if(tgt==null){
            return false;
        }

        String st = getST( sessionGenerateService, tgt);
        if(st==null){
            return false;
        }

        String sessionId = generateSession(sessionGenerateService,st);
        if(sessionId==null){
            return false;
        }
        return true;
    }

    //获取httpResponse的返回字符串
    private String getResponseBody(HttpResponse response){
        StringBuilder sb = new StringBuilder();
        try {
            InputStream instream = response.getEntity().getContent();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(instream), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            instream.close();
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
        return sb.toString();

    }

    /**
     *获得ticket granting ticket
     */
    public String getTGT(String username, String password)
    {
        String tgt = null;
        HttpPost httpPost = new HttpPost (casBaseURL + CAS_LOGIN_URL_PART);
        try
        {
            List <NameValuePair> nvps = new ArrayList <NameValuePair> ();
            nvps.add(new BasicNameValuePair ("username", username));
            nvps.add(new BasicNameValuePair ("password", password));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = getResponseBody(response);
            switch (response.getStatusLine().getStatusCode())
            {
                case 201:
                {
                    Matcher matcher = Pattern.compile(".*action='.*/tickets/(TGT-.*\\.whut\\.org).*")
                            .matcher(responseBody.replaceAll("\"", "'"));
                    if (matcher.matches()){
                        tgt = matcher.group(1);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tgt;
    }

    public String getST(String service,String tgt){
        if (tgt == null) return null;
        String st = null;
        HttpPost httpPost = new HttpPost (casBaseURL + CAS_LOGIN_URL_PART+"/" + tgt);
        try
        {
            List <NameValuePair> nvps = new ArrayList <NameValuePair> ();
            nvps.add(new BasicNameValuePair ("web", service));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = getResponseBody(response);

            switch (response.getStatusLine().getStatusCode())
            {
                case 200:
                {
                    st = responseBody;
                    break;
                }
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return st;
    }

    //返回session编号
    public String generateSession(String service,String st){
        String sessionId = "";
        HttpGet httpGet = new HttpGet (service+"?ticket="+st);
        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            EntityUtils.toString(response.getEntity() ,"utf-8");
            switch (response.getStatusLine().getStatusCode())
            {
                case 200:
                {
                    List<Cookie> cookies = httpClient.getCookieStore().getCookies();
                    if (! cookies.isEmpty()){
                        for (Cookie cookie : cookies){
                            if(cookie.getName().equals("JSESSIONID")){
                                sessionId = cookie.getValue();
                                break;
                            }
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sessionId;
    }

    //发送 GET 请求
    public String doGet(String service){
        HttpGet httpGet = new HttpGet (service);
        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = getResponseBody(response);
            switch (response.getStatusLine().getStatusCode())
            {
                case 200:
                {
                    return responseBody;
                }
                default:
                    break;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //发送 POST 请求
    public String doPost(String service,HashMap<String,String> params){
        HttpPost httpPost = new HttpPost (service);
        try
        {
            List <NameValuePair> nvps = new ArrayList <NameValuePair> ();
            for(String key:params.keySet()){
                nvps.add(new BasicNameValuePair (key, params.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = getResponseBody(response);
            switch (response.getStatusLine().getStatusCode())
            {
                case 200:
                {
                    return responseBody;
                }
                default:
                    break;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //预先设置post编码并发送post请求

    /**
     * 从cas server 退出
     */
    public boolean logout ()
    {
        boolean logoutSuccess = false;
        HttpDelete httpDelete = new HttpDelete(casBaseURL + CAS_LOGOUT_URL_PART);
        try
        {
            HttpResponse response = httpClient.execute(httpDelete);
            logoutSuccess = (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
        }
        catch (Exception e)
        {
            logoutSuccess = false;
        }
        return logoutSuccess;
    }

    public static void main(String args[]){
		CasClient casClient = new CasClient( "http://www.cseicms.com/inspectCas/v1/");
 		boolean loginResult = casClient.login("zhaowei", "123456", "http://www.cseicms.com/inspectManagement/j_spring_cas_security_check");
		if(loginResult){
			String message = casClient.doGet("http://www.cseicms.com/inspectManagement/rs/user/currentUser");
			System.out.println(message);
            message = casClient.doGet("http://www.cseicms.com/inspectManagement/rs/inspectUser/currentUser");
            System.out.println(message);
//            HashMap<String,String> params = new HashMap<String, String>();
//            params.put("resource","cas/**");
//            params.put("type","web");
//            params.put("description","cas client test from android");
//            message  = casClient.doPost("http://www.cseicms.com/riskPlatform/rs/power/add",params);
//            System.out.print(message);
		}
    }

}