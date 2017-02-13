package com.vstar.sacredsun_android_phone.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.vstar.sacredsun_android_phone.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.layout1)
    public void clickLayout() {

        float percent = (layout.getWidth())/(layout.getHeight());

        layout.animate().scaleX(2*percent).scaleY(2f)
                .translationX(0.5f)
                .translationY(0.5f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(1000)
                .start();

//        Intent intent = new Intent(TestActivity.this,JumpActivity.class);
//        startActivity(intent);
//        overridePendingTransition(0,0);

    }


}
