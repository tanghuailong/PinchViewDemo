package com.vstar.sacredsun_android_phone.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vstar.sacredsun_android_phone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanghuailong on 2017/1/10.
 */

public class StoveAdapter {



    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stove_num)
        TextView stoveNum;
        @BindView(R.id.stove_run_status)
        TextView stoveRunStatus;
        @BindView(R.id.stove_temp_setting)
        TextView stoveTempSetting;
        @BindView(R.id.stove_temp_value)
        TextView stoveTempValue;
        @BindView(R.id.stove_humi_setting)
        TextView stoveHumiSetting;
        @BindView(R.id.stove_humi_value)
        TextView stoveHumiValue;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
