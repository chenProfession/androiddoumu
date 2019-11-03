package com.doumuecommerce.authorization;

public class ResultLogin {

    private String result;
    private String message;
    private String sessionid;

    public ResultLogin() {
    }

    public ResultLogin(String result, String message, String sessionid) {
        this.result = result;
        this.message = message;
        this.sessionid = sessionid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
