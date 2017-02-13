package com.vstar.sacredsun_android_phone.util;

import java.util.List;

/**
 * Created by tanghuailong on 2017/2/13.
 */

public class FunctionUtil {

    //获得一个List的前半部分
    public static <T> List<T> getHeaderList(List<T> list,int count) {
        return list.subList(0,count);
    }
    //获得一个List的后半部分
    public static  <T> List<T> getFooterList(List<T> list,int count) {
        return list.subList(count,list.size());
    }
}
