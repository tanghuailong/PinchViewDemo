package com.vstar.sacredsun_android_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vstar.sacredsun_android_phone.util.FunctionUtil;
import com.vstar.sacredsun_android_phone.util.StartMode;


/**
 * Created by tanghuailong on 2017/2/20.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StartMode startMode = FunctionUtil.getStartMode(SplashActivity.this);
        switch (startMode) {
            case NO_SETTING:
                Intent settingIntent = new Intent(this,SettingActivity.class);
                startActivity(settingIntent);
                finish();
                break;
            case NO_LOGIN:
                Intent loginIntent = new Intent(this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
                break;
            case NORMAL:
                Intent normalIntent = new Intent(this,MainActivity.class);
                startActivity(normalIntent);
                finish();
                break;
        }
    }


}
