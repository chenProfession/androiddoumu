package com.doumuecommerce.authorization;

import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private static Logger logger = Logger.getLogger(LoginService.class.getName());
    private LoginService loginService = new LoginService();

    @Test
    public void getLoginKey() {
        String lgoinKey = loginService.getLoginKey("system","admin");
        logger.info(lgoinKey);
        Assert.assertNotNull(lgoinKey);
    }

    @Test
    public void getAppLoginURLByFirstTime() {
        String url = "http://demo.jeesite.com/js/a/login";
        String loginURL = loginService.getAppLoginURLByFirstTime(url,loginService.getLoginKey("system","admin"));
        logger.info(loginURL);
        Assert.assertNotNull(loginURL);
    }

    @Test
    public void getAppLoginURLBySecondTime() {
        String url = "http://demo.jeesite.com/js/a/login";
        String sessionId = "720563558c5d48949a24611b5bfe4d3e";
        String loginURL = loginService.getAppLoginURLBySecondTime(url,loginService.getLoginKey("system","admin"),sessionId);
        logger.info(loginURL);
        Assert.assertNotNull(loginURL);
    }
}