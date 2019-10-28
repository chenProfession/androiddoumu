package com.doumuecommerce.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.doumuecommerce.R;
import com.doumuecommerce.authorization.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init(){
        textView = (TextView) findViewById(R.id.textVersion);
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            textView.setText("V" + packageInfo.versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            textView.setText("V");
        }
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        };
        timer.schedule(timerTask,3000);
    }
}
