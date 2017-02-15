package com.vstar.sacredsun_android_phone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vstar.sacredsun_android_phone.R;
import com.vstar.sacredsun_android_phone.entity.DeviceEntity;
import com.vstar.sacredsun_android_phone.util.PageCategory;
import com.vstar.sacredsun_android_phone.util.StatusMap;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanghuailong on 2017/1/10.
 */

public class StoveAdapter extends RecyclerView.Adapter<StoveAdapter.ViewHolder>{

    private static final String LOG_TAG = "StoveAdapter";
    private List<DeviceEntity> lists;
    private Context context;
    private PageCategory category;
    private OnRecyclerViewItemClickListener listener;

    public StoveAdapter(List<DeviceEntity> lists, Context context, PageCategory category) {
        this(lists,context,category,null);
    }

    public StoveAdapter(List<DeviceEntity> lists, Context context,PageCategory category,OnRecyclerViewItemClickListener listener) {
        this.lists = lists;
        this.context = context;
        this.category = category;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.stove_item, parent, false);
        //根据不同的页面加载不同的布局
        if(category == PageCategory.SINGLE) {
            view = layoutInflater.inflate(R.layout.stove_item_big, parent, false);
        }
        ViewHolder holder = new ViewHolder(view);
//        view.setOnClickListener(v ->  {
//            listener.onItemClick(v,lists.get(holder.getAdapterPosition()).getAssetsCode());
//        });
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DeviceEntity stoveItem = lists.get(position);
        if(stoveItem != null) {
            holder.container.setBackgroundResource(StatusMap.statusAndView.get(stoveItem.getStatus().name()));
            holder.stoveNum.setText(stoveItem.getAssetsCode());
            holder.stoveRunStatus.setText(StatusMap.abbreAndDesc.get(stoveItem.getStatus().name()));
            holder.stoveTempSetting.setText(stoveItem.getTemperature());
            holder.stoveTempValue.setText(stoveItem.getTemperature1());
            holder.stoveHumiSetting.setText(stoveItem.getHumidity());
            holder.stoveHumiValue.setText(stoveItem.getHumidity1());
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stove_item_container)
        LinearLayout container;
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
