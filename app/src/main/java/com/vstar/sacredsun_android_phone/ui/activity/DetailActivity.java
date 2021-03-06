package com.vstar.sacredsun_android_phone.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

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
import rx.Observable;
import rx.Subscription;

/**
 * Created by tanghuailong on 2017/2/13.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.identity)
    TextView identity;
    @BindString(R.string.POSITION)
    String areaPosition;
    @BindInt(R.integer.single_limit)
    int singleLimit;
    @BindString(R.string.work_shop_code_positive)
    String positive;
    @BindString(R.string.work_shop_code_negative)
    String negative;

    private StoveAdapter adapter;
    private List<DeviceEntity> list = new ArrayList<>();
    //默认为负一区
    private int belongArea = 1;
    private Subscription subscription = null;

    private static final String LOG_TAG = "DetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            belongArea = bundle.getInt(areaPosition);
        }
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.getItemAnimator().setChangeDuration(0);
        adapter = new StoveAdapter(list, this, PageCategory.SINGLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart");
        initData(belongArea);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"onStop");
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestory");
    }

    private void initData(int postion) {
        switch (postion) {
            case AppConstant.POSITIVE_ONE:
                identity.setText(getString(R.string.position_one));
                queryDataFromServer(positive, 1);
                break;
            case AppConstant.POSITIVE_TWO:
                identity.setText(getString(R.string.position_two));
                queryDataFromServer(positive, 2);
                break;
            case AppConstant.NEGATIVE_ONE:
                identity.setText(getString(R.string.negative_one));
                queryDataFromServer(negative, 1);
                break;
            case AppConstant.NEGATIVE_TWO:
                identity.setText(getString(R.string.negative_two));
                queryDataFromServer(negative, 2);
                break;
        }
    }

    private void queryDataFromServer(String positive, int which) {

        subscription = HttpMethods.getInstane().getService(MobileApi.class)
                .getDeviceBasicData(positive)
                .compose(RxHelper.io_main())
                .retryWhen(errors -> errors.flatMap(error -> Observable.timer(5, TimeUnit.SECONDS)))
                .repeatWhen(completed -> completed.delay(5, TimeUnit.SECONDS))
                .subscribe((r) -> {
                    Log.d(LOG_TAG, "onNext");
                    if (r.getItems().size() != 0) {
                        if (which == 1) {
                            List<DeviceEntity> addToList = FunctionUtil.getHeaderList(r.getItems(),singleLimit);
                            updateRecyclerView(list,addToList, adapter);
                        } else if (which == 2) {
                            List<DeviceEntity> addToList = FunctionUtil.getFooterList(r.getItems(), singleLimit);
                            updateRecyclerView(list,addToList, adapter);
                        }
                    }
                }, (e) -> {
                    Log.e(LOG_TAG, "some error occur", e);
                }, () -> {
                    Log.d(LOG_TAG, "complete");
                });
    }

    private void updateRecyclerView(List<DeviceEntity> myList,List<DeviceEntity> listAdd, StoveAdapter adapter) {
        myList.clear();
        myList.addAll(listAdd);
        adapter.notifyItemRangeChanged(0, list.size());
    }
}
