package com.doumuecommerce.authorization;

import java.io.Serializable;

public class ResultLoginError extends ResultLogin implements Serializable {

    private String username;
    private boolean rememberMe;
    private boolean rememberUserCode;
    private String params;
    private String shiroLoginFailure;
    private String isValidCodeLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isRememberUserCode() {
        return rememberUserCode;
    }

    public void setRememberUserCode(boolean rememberUserCode) {
        this.rememberUserCode = rememberUserCode;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getShiroLoginFailure() {
        return shiroLoginFailure;
    }

    public void setShiroLoginFailure(String shiroLoginFailure) {
        this.shiroLoginFailure = shiroLoginFailure;
    }

    public String getIsValidCodeLogin() {
        return isValidCodeLogin;
    }

    public void setIsValidCodeLogin(String isValidCodeLogin) {
        this.isValidCodeLogin = isValidCodeLogin;
    }

}
