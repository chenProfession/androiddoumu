package com.doumuecommerce.authorization;

public class ResultLoginSuccess extends Result {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
