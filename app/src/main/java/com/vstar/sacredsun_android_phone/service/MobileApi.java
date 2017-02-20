package com.vstar.sacredsun_android_phone.service;

import com.vstar.sacredsun_android_phone.entity.DeviceEntity;
import com.vstar.sacredsun_android_phone.entity.EmptyResult;
import com.vstar.sacredsun_android_phone.entity.HttpResult;
import com.vstar.sacredsun_android_phone.entity.LoginEntity;
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
    //用户登陆
    @GET("staffmgt/control/clientLogin")
    Observable<HttpResult<LoginEntity,EmptyResult>> userLogin(@Query("userLoginId")String userLoginId,@Query("password") String password,@Query("workCenterCode") String workCenterCode);
    //用户登出
    @GET("staffmgt/control/clientLogout")
Observable<HttpResult> userLoginout(@Query("session") String session);
}
