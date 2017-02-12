package com.vstar.sacredsun_android_phone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vstar.sacredsun_android_phone.R;
import com.vstar.sacredsun_android_phone.entity.DeviceEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanghuailong on 2017/1/10.
 */

public class StoveAdapter extends RecyclerView.Adapter<StoveAdapter.ViewHolder>{

    private List<DeviceEntity> lists;
    private Context context;
    private OnRecyclerViewItemClickListener listener;

    public StoveAdapter(List<DeviceEntity> lists, Context context,OnRecyclerViewItemClickListener listener) {
        this.lists = lists;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.stove_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(v ->  {
            listener.onItemClick(v,null);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DeviceEntity stoveItem = lists.get(position);
        if(stoveItem != null) {

        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

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
