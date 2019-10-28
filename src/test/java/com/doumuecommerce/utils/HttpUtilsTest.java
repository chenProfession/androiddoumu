package com.doumuecommerce.utils;

import androidx.appcompat.app.AppCompatActivity;

import com.doumuecommerce.authorization.LoginService;
import com.doumuecommerce.authorization.ResultLoginError;
import com.doumuecommerce.authorization.ResultLoginSuccess;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class HttpUtilsTest {

    private static Logger logger = Logger.getLogger(HttpUtilsTest.class.getName());

    @Test
    public void sendRequestPost() {

        final String httpUrl = "http://demo.jeesite.com/js/a/login";
        final String name = "system";
        final String password = "admin";


        String secretKey = "thinkgem,jeesite,com";
        String username = DesUtils.encode(name, secretKey);
        String userpassword = DesUtils.encode(password, secretKey);

        /** 参数put到json串里 **/
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username",username);
            jsonObject.put("password",userpassword);
            jsonObject.put("__login","true");
            jsonObject.put("__ajax","json");
            jsonObject.put("param_deviceType","mobileApp");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        HttpUtils httpUtils = new HttpUtils();
        String resultString = httpUtils.sendRequestPost(httpUrl,jsonObject);
        logger.info(resultString);

        Assert.assertNotNull(resultString);
    }

    @Test
    public void sendRequestGet() {

        final String httpUrl = "http://demo.jeesite.com/js/a/login";
        final String name = "system";
        final String password = "admin";

        LoginService loginService = new LoginService();

        String url = loginService.getAppLoginURLByFirstTime(httpUrl,loginService.getLoginKey(name,password));

        HttpUtils httpUtils = new HttpUtils();
        String resultString = httpUtils.sendRequestGet(url);
        logger.info(resultString);

        try {
            JSONObject jsonObject = new JSONObject(resultString);
            String status = jsonObject.getString("result");
            logger.info(status);
            Gson gson = new Gson();
            if("true".equals(status)){
                ResultLoginSuccess resultLoginSuccess = gson.fromJson(resultString,ResultLoginSuccess.class);
                logger.info(resultLoginSuccess.getMessage());
                logger.info(resultLoginSuccess.getUser().getAvatarUrl());
                logger.info(resultLoginSuccess.getSessionid());
            }
            if("false".equals(status)){
                ResultLoginError resultLoginError = gson.fromJson(resultString,ResultLoginError.class);
                logger.info(resultLoginError.getMessage());
                logger.info(resultLoginError.getSessionid());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(resultString);
    }

    @Test
    public void sendRequestGetMap() {

        final String httpUrl = "http://demo.jeesite.com/js/a/login";
        final String name = "system";
        final String password = "admin";

        String secretKey = "thinkgem,jeesite,com";
        String username = DesUtils.encode(name, secretKey);
        String userpassword = DesUtils.encode(password, secretKey);

        Map<String, String> mapData = new HashMap<String,String>();
        mapData.put("username",username);
        mapData.put("password",userpassword);
        mapData.put("__login","true");
        mapData.put("__ajax","json");
        mapData.put("param_deviceType","mobileApp");

        HttpUtils httpUtils = new HttpUtils();
        String resultString = httpUtils.sendRequestGet(httpUrl,mapData);
        logger.info(resultString);

        Assert.assertNotNull(resultString);
    }
}