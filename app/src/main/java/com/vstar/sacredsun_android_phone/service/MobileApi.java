package com.vstar.sacredsun_android_phone.service;

import com.vstar.sacredsun_android_phone.entity.DeviceEntity;
import com.vstar.sacredsun_android_phone.entity.HttpResult;
import com.vstar.sacredsun_android_phone.entity.WorkShopName;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tanghuailong on 2017/2/13.
 */

/**
 * 手机要访问的一些接口
 */
public interface MobileApi {

    //获取设备的基本生产信息和工艺信息
    @GET("equipmentmgt/control/initKanban")
    Observable<HttpResult<WorkShopName,DeviceEntity>> getDeviceBasicData(@Query("workshopCode") String workshopCode);

}
