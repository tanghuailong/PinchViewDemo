package com.vstar.sacredsun_android_phone.entity;

/**
 * Created by tanghuailong on 2017/1/15.
 */

/**
 * 设备运行状态
 */
public enum Status {
    UNUSED,                   //闲置
    PRE_SOLIDIFICATION,       //预固化
    SOLIDIFICATION,           //固化
    DRYING,                   //干燥
    WARNING,                  //告警
    ENDING                    //结束
}
