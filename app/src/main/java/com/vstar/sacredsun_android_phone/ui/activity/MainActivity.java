package com.vstar.sacredsun_android_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.vstar.sacredsun_android_phone.R;
import com.vstar.sacredsun_android_phone.adapter.StoveAdapter;
import com.vstar.sacredsun_android_phone.entity.DeviceEntity;
import com.vstar.sacredsun_android_phone.service.MobileApi;
import com.vstar.sacredsun_android_phone.util.AppConstant;
import com.vstar.sacredsun_android_phone.util.FunctionUtil;
import com.vstar.sacredsun_android_phone.util.HttpMethods;
import com.vstar.sacredsun_android_phone.util.PageCategory;
import com.vstar.sacredsun_android_phone.util.RxHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.position_one_recyclerview)
    RecyclerView recyclerViewPO;
    @BindView(R.id.position_two_recyclerview)
    RecyclerView recyclerViewPT;
    @BindView(R.id.negative_one_recyclerview)
    RecyclerView recyclerViewNO;
    @BindView(R.id.negative_two_recyclerview)
    RecyclerView recyclerViewNT;

    @BindView(R.id.area_one)
    LinearLayout areaOne;
    @BindView(R.id.area_two)
    LinearLayout areaTwo;
    @BindView(R.id.area_three)
    LinearLayout areaThree;
    @BindView(R.id.area_four)
    LinearLayout areaFour;

    @BindInt(R.integer.single_limit)
    int singleLimit;
    @BindString(R.string.work_shop_code_positive)
    String positive;
    @BindString(R.string.work_shop_code_negative)
    String negative;
    @BindString(R.string.POSITION)
    String areaPosition;

    //负区1
    private StoveAdapter negative_adapter_1;
    //负区2
    private StoveAdapter negative_adapter_2;
    //正区1
    private StoveAdapter positive_adapter_1;
    //正区2
    private StoveAdapter positive_adapter_2;

    private List<DeviceEntity> list_positive_1 = new ArrayList<>();
    private List<DeviceEntity> list_positive_2 = new ArrayList<>();
    private List<DeviceEntity> list_negative_1 = new ArrayList<>();
    private List<DeviceEntity> list_negative_2 = new ArrayList<>();

    private Subscription subscription1 = null;
    private Subscription subscription2 = null;

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        settingRecyclerView(recyclerViewPO, AppConstant.POSITIVE_ONE);
        settingRecyclerView(recyclerViewPT, AppConstant.POSITIVE_TWO);
        settingRecyclerView(recyclerViewNO, AppConstant.NEGATIVE_ONE);
        settingRecyclerView(recyclerViewNT, AppConstant.NEGATIVE_TWO);
        initData();
    }

    private void settingRecyclerView(RecyclerView recyclerView, @AppConstant.Position int position) {
        switch (position) {
            case AppConstant.POSITIVE_ONE:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                positive_adapter_1 = new StoveAdapter(list_positive_1, this, PageCategory.ALL);
//                positive_adapter_1.setHasStableIds(true);
                recyclerView.setAdapter(positive_adapter_1);
                recyclerView.getItemAnimator().setChangeDuration(0);
                break;
            case AppConstant.POSITIVE_TWO:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                positive_adapter_2 = new StoveAdapter(list_positive_2, this,PageCategory.ALL);
//                positive_adapter_2.setHasStableIds(true);
                recyclerView.setAdapter(positive_adapter_2);
                recyclerView.getItemAnimator().setChangeDuration(0);
                break;
            case AppConstant.NEGATIVE_ONE:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                negative_adapter_1 = new StoveAdapter(list_negative_1, this,PageCategory.ALL);
//                negative_adapter_1.setHasStableIds(true);
                recyclerView.setAdapter(negative_adapter_1);
                recyclerView.getItemAnimator().setChangeDuration(0);

                break;
            case AppConstant.NEGATIVE_TWO:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                negative_adapter_2 = new StoveAdapter(list_negative_2, this,PageCategory.ALL);
//                negative_adapter_2.setHasStableIds(true);
                recyclerView.setAdapter(negative_adapter_2);
                recyclerView.getItemAnimator().setChangeDuration(0);
                break;
            default:
                break;
        }
    }

    private void initData() {

        subscription1 = HttpMethods.getInstane().getService(MobileApi.class)
                .getDeviceBasicData(positive)
                .compose(RxHelper.io_main())
                .retryWhen(errors -> errors.flatMap(error -> Observable.timer(5, TimeUnit.SECONDS)))
                .repeatWhen(completed -> completed.delay(5, TimeUnit.SECONDS))
                .subscribe((r) -> {
                    Log.d(LOG_TAG, "onNext");
                    if (r.getItems().size() <= singleLimit) {
                        updateRecyclerView(list_positive_1, r.getItems(), positive_adapter_1);
                    } else {
                        updateRecyclerView(list_positive_1, FunctionUtil.getHeaderList(r.getItems(),singleLimit), positive_adapter_1);
                        updateRecyclerView(list_positive_2,FunctionUtil.getFooterList(r.getItems(),singleLimit),positive_adapter_2);
                    }
                }, (e) -> {
                    Log.e(LOG_TAG, "some error occur", e);
                });
        subscription2 = HttpMethods.getInstane().getService(MobileApi.class)
                .getDeviceBasicData(negative)
                .compose(RxHelper.io_main())
                .retryWhen(errors -> errors.flatMap(error -> Observable.timer(5, TimeUnit.SECONDS)))
                .repeatWhen(completed -> completed.delay(5, TimeUnit.SECONDS))
                .subscribe((r) -> {
                    if (r.getItems().size() <= singleLimit) {
                        updateRecyclerView(list_negative_1, r.getItems(), negative_adapter_1);
                    } else {
                        updateRecyclerView(list_negative_1, FunctionUtil.getHeaderList(r.getItems(),singleLimit), negative_adapter_1);
                        updateRecyclerView(list_negative_2,FunctionUtil.getFooterList(r.getItems(),singleLimit),negative_adapter_2);
                    }
                }, (e) -> {
                    Log.e(LOG_TAG, "some error occur", e);
                });
    }

    @OnClick({R.id.area_one,R.id.area_two,R.id.area_three,R.id.area_four})
    public void jumpToDetailPage(View view) {
        switch (view.getId()) {
            case R.id.area_one:
                jumpToActivity(AppConstant.NEGATIVE_ONE);
                break;
            case R.id.area_two:
                jumpToActivity(AppConstant.NEGATIVE_TWO);
                break;
            case R.id.area_three:
                jumpToActivity(AppConstant.POSITIVE_ONE);
                break;
            case R.id.area_four:
                jumpToActivity(AppConstant.POSITIVE_TWO);
                break;
        }
    }

    /**
     * Activity 跳转
     * @param point
     */
    private void jumpToActivity(@AppConstant.Position int point) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(areaPosition,point);
        startActivity(intent);
    }
    /**
     * 更新某块页面的数据
     * @param list  存放某块区域的数据
     * @param listAdd 刷新后拿到的数据
     * @param adapter  某块区域的Adapter
     */
    private void updateRecyclerView(List<DeviceEntity> list, List<DeviceEntity> listAdd, StoveAdapter adapter) {
        list.clear();
        list.addAll(listAdd);
        adapter.notifyItemRangeChanged(0,list_negative_1.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription1!=null && subscription1.isUnsubscribed()) {
            subscription1.unsubscribe();
        }
        if(subscription2!=null && subscription2.isUnsubscribed()){
            subscription2.unsubscribe();
        }
    }
}
