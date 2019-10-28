package com.doumuecommerce.authorization;


import java.io.Serializable;

public class ResultLoginSuccess extends Result implements Serializable {

    private User user;

    /** 构造方法 **/
    public ResultLoginSuccess() {
    }
    /** 构造方法 **/
    public ResultLoginSuccess(User user, String result, String message, String sessionid) {
        super(result, message, sessionid);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
