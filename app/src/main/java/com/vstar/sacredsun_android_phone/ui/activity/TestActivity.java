package com.vstar.sacredsun_android_phone.ui.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.vstar.sacredsun_android_phone.R;
import com.vstar.sacredsun_android_phone.service.HintService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanghuailong on 2017/2/9.
 */
@Deprecated
public class TestActivity extends AppCompatActivity{

    @BindView(R.id.layout1)
    RelativeLayout layout;
    @BindView(R.id.start_service)
    Button startService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.start_service)
    public void clickLayout() {
        Intent intent = new Intent(this, HintService.class);
        startService(intent);
    }

    @OnClick(R.id.stop_service)
    public void test() {
        Intent intent = new Intent(this, HintService.class);
        stopService(intent);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }



}
