package com.doumuecommerce.authorization;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doumuecommerce.R;
import com.doumuecommerce.management.ManageActivity;
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
    private long lastClick;

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
                if (System.currentTimeMillis() - lastClick <= 1000)
                {
                    Toast.makeText(LoginActivity.this,"点那么快干什么!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                lastClick = System.currentTimeMillis();

                userName = editTextUserName.getText().toString().trim();
                userPWD = editTextUserPWD.getText().toString().trim();
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

                        String url = loginService.getAppLoginURLByFirstTime(httpUrl,loginService.getLoginKey(userName,userPWD));

//                        if(i > 0 && sessionId != null){
//                            url = loginService.getAppLoginURLBySecondTime(httpUrl,loginService.getLoginKey(userName,userPWD),sessionId);
//                        }

                        HttpUtils httpUtils = new HttpUtils();
                        String resultString = httpUtils.sendRequestGet(url);
                        if(resultString != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(resultString);
                                String status = jsonObject.getString("result");
                                Gson gson = new Gson();
                                if ("true".equals(status)) {
                                    ResultLoginSuccess resultLoginSuccess = gson.fromJson(resultString, ResultLoginSuccess.class);
                                    Intent it = new Intent(LoginActivity.this, ManageActivity.class);

                                    /** 使用Bundle在Activity之间传值 **/
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("resultLoginSuccess", resultLoginSuccess);
                                    it.putExtras(bundle);

                                    it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(it);
                                }
                                if ("false".equals(status)) {
                                    ResultLoginError resultLoginError = gson.fromJson(resultString, ResultLoginError.class);
                                    threadRunToToast("用户或密码错误");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            threadRunToToast("网络错误，请重试......");
                        }
                    }
                }.start();
            }
        });

    }


    /**
     * 在 主线程 子线程 中提示，属于UI操作
     */
    private void threadRunToToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
