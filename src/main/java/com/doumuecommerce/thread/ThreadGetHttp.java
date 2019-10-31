package com.doumuecommerce.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.doumuecommerce.authorization.LoginService;
import com.doumuecommerce.authorization.ResultLoginError;
import com.doumuecommerce.authorization.ResultLoginSuccess;
import com.doumuecommerce.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ThreadGetHttp extends Thread {
    public Handler subHandler = null;
    public String httpUrl;
    public Intent it;
    public String userName;
    public String userPWD;
    public Activity activity;

    @Override
    public void run() {
        LoginService loginService = new LoginService();

        String url = loginService.getAppLoginURLByFirstTime(httpUrl,loginService.getLoginKey(userName,userPWD));

//                        if(i > 0 && sessionId != null){
//                            url = loginService.getAppLoginURLBySecondTime(httpUrl,loginService.getLoginKey(userName,userPWD),sessionId);
//                        }

        HttpUtils httpUtils = new HttpUtils();
        String resultString = httpUtils.sendRequestGet(url);
        try {
            JSONObject jsonObject = new JSONObject(resultString);
            String status = jsonObject.getString("result");
            Gson gson = new Gson();
            if("true".equals(status)){
                ResultLoginSuccess resultLoginSuccess = gson.fromJson(resultString,ResultLoginSuccess.class);

                /** 使用Bundle在Activity之间传值 **/
                Bundle bundle = new Bundle();
                bundle.putSerializable("resultLoginSuccess", resultLoginSuccess);
                it.putExtras(bundle);

                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(it);
            }
            if("false".equals(status)){
                ResultLoginError resultLoginError = gson.fromJson(resultString,ResultLoginError.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getApplicationContext(), "用户或密码错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
