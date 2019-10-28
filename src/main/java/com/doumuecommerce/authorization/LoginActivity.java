package com.doumuecommerce.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doumuecommerce.R;
import com.doumuecommerce.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //private TextView textViewMainTile;
    private TextView textViewFindPWD;
    private Button buttonLogin;
    private String userName;
    private String userPWD;
    private EditText editTextUserName;
    private EditText editTextUserPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /** 设置界面为竖屏 **/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init(){

        textViewFindPWD = (TextView) findViewById(R.id.textViewForgetPWD);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextUserPWD = (EditText) findViewById(R.id.editTextPWD);

        /**
         * description:找回密码控件的点击事件
         */
        textViewFindPWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: 跳转到找回密码的界面
            }
        });

        /**
         * description:登录按钮的点击事件
         */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editTextUserName.getText().toString().trim();
                userName = editTextUserPWD.getText().toString().trim();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userPWD)){
                    Toast.makeText(LoginActivity.this,"请输入用户密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                /** 用户和密码加密 登录 **/
                new Thread(){
                    @Override
                    public void run() {
                        final String httpUrl = "http://demo.jeesite.com/js/a/login";
                        LoginService loginService = new LoginService();

                        String url = loginService.getAppLoginURLByFirstTime(httpUrl,loginService.getLoginKey(userName,userName));

                        HttpUtils httpUtils = new HttpUtils();
                        String resultString = httpUtils.sendRequestGet(url);
                        try {
                            JSONObject jsonObject = new JSONObject(resultString);
                            String status = jsonObject.getString("result");
                            Gson gson = new Gson();
                            if("true".equals(status)){
                                ResultLoginSuccess resultLoginSuccess = gson.fromJson(resultString,ResultLoginSuccess.class);
                                /** 保存登录状态和sessionId **/
                                saveLoginStatus(true,resultLoginSuccess.getSessionid());
                                //Intent it=new Intent(LoginActivity.this, MainActivity.class);
                            }
                            if("false".equals(status)){
                                ResultLoginError resultLoginError = gson.fromJson(resultString,ResultLoginError.class);
                                saveLoginStatus(false,resultLoginError.getSessionid());
                                Toast.makeText (getApplicationContext(),"用户或密码错误", Toast.LENGTH_LONG ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


            }
        });
    }

    private void saveLoginStatus(boolean status,String sessionId){
        SharedPreferences loginTImes = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = loginTImes.edit();
        editor.putBoolean("result",status);
        editor.putString("sessionId",sessionId);
        editor.commit();

    }
}
