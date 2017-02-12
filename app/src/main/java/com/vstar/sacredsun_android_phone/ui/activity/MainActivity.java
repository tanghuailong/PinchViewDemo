package com.vstar.sacredsun_android_phone.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.vstar.sacredsun_android_phone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.position_one_recyclerview)
    RecyclerView recyclerViewPO;
    @BindView(R.id.position_two_recyclerview)
    RecyclerView recyclerViewPT;
    @BindView(R.id.negative_one_recyclerview)
    RecyclerView recyclerViewNO;
    @BindView(R.id.negative_two_recyclerview)
    RecyclerView recyclerViewNT;

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        settingRecyclerView(recyclerViewPO);
        settingRecyclerView(recyclerViewPT);
        settingRecyclerView(recyclerViewNO);
        settingRecyclerView(recyclerViewNT);
    }

    private void settingRecyclerView(RecyclerView recyclerView) {

    }
}
