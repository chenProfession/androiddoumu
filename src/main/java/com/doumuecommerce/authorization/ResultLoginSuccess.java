package com.doumuecommerce.authorization;

import java.io.Serializable;

public class ResultLoginSuccess extends Result implements Serializable {
    private User user;

    public ResultLoginSuccess() {
    }

    public ResultLoginSuccess(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
