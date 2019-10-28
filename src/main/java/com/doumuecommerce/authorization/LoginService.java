package com.doumuecommerce.authorization;

import com.doumuecommerce.utils.DesUtils;

public class LoginService {

    public String getLoginKey(String name, String password){
        String secretKey = "thinkgem,jeesite,com";
        String username = DesUtils.encode(name, secretKey);
        String userpassword = DesUtils.encode(password, secretKey);
        return "&username=" + username + "&password=" + userpassword;
    }

    public String getAppLoginURLByFirstTime(String url, String loginKey){
        return  url + "?__login=true&__ajax=json&param_deviceType=mobileApp" + loginKey;
    }

    public String getAppLoginURLBySecondTime(String url, String loginKey, String sessionId){
        return  url + "?__login=true&__ajax=json&param_deviceType=mobileApp" + loginKey + "&__sid=" + sessionId;
    }
}
