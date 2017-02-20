package com.vstar.sacredsun_android_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vstar.sacredsun_android_phone.R;
import com.vstar.sacredsun_android_phone.service.MobileApi;
import com.vstar.sacredsun_android_phone.util.HttpMethods;
import com.vstar.sacredsun_android_phone.util.RxHelper;
import com.vstar.sacredsun_android_phone.util.SPHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanghuailong on 2017/2/20.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.username_layout)
    TextInputLayout usernameLayout;
    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button btn_login;

    private static final String LOG_TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void userLogin() {

        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        String workCenterCode = (String) SPHelper.get(this,getString(R.string.WORK_CENTER_CODE_SESSION),"");

       if(!TextUtils.isEmpty(usernameStr) && !TextUtils.isEmpty(passwordStr)){
           //登陆
           HttpMethods.getInstane().getService(MobileApi.class)
                   .userLogin(usernameStr,passwordStr,workCenterCode)
                   .compose(RxHelper.io_main())
                   .subscribe((r) -> {
                       if(!TextUtils.isEmpty(r.getItem().getSession())) {
                           SPHelper.putAndApply(LoginActivity.this,getString(R.string.USER_SESSION),r.getItem().getSession());
                           SPHelper.putAndApply(LoginActivity.this,getString(R.string.IS_LOGIN),true);
                           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                       }
                   },(e) -> {
                       Log.e(LOG_TAG,"in login some error happen",e);
                       Toast.makeText(LoginActivity.this, "登陆遇到一些问题", Toast.LENGTH_SHORT).show();
                   });

       }else {
           Toast.makeText(this,"用户名或密码输入不合法",Toast.LENGTH_SHORT).show();
       }

    }


}
