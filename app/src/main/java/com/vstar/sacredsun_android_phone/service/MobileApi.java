package com.vstar.sacredsun_android_phone.service;

import com.vstar.sacredsun_android_phone.entity.DeviceEntity;
import com.vstar.sacredsun_android_phone.entity.HttpResult;
import com.vstar.sacredsun_android_phone.entity.WorkShopName;

import retrofit2.http.POST;
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
//    @GET("equipmentmgt/control/initKanban")
    @POST("http://www.mocky.io/v2/58a414362900000b093e4af1")
    Observable<HttpResult<WorkShopName,DeviceEntity>> getDeviceBasicData(@Query("workshopCode") String workshopCode);

}
