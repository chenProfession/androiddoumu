package com.doumuecommerce.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doumuecommerce.utils.DesUtils;
import com.doumuecommerce.welcome.R;

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
                userPWD = editTextUserPWD.getText().toString().trim();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userPWD)){
                    Toast.makeText(LoginActivity.this,"请输入用户密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                /** 用户和密码加密 thinkgem,jeesite,com **/
                String secretKey = "thinkgem,jeesite,com";
                String username = DesUtils.encode(userName, secretKey);
                String password = DesUtils.encode(userPWD, secretKey);

            }
        });
    }
}
