package com.vstar.sacredsun_android_phone.util;

import com.vstar.sacredsun_android_phone.R;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by tanghuailong on 2017/1/9.
 */

public class StatusMap {
    public  static final Map<String, Integer> statusAndView = new HashMap<String,Integer>() {{
        put("UNUSED", R.color.status_bar_idle);
        put("ENDING",R.color.status_bar_preheating);
        put("PRE_SOLIDIFICATION",R.color.status_bar_presolidify);
        put("SOLIDIFICATION",R.color.status_bar_solidify);
        put("DRYING",R.color.status_bar_dry);
        put("WARNING",R.color.status_bar_warning);
    }};
    public static final Map<String,String> abbreAndDesc = new HashMap<String,String>(){{
        put("UNUSED","闲置");
        put("ENDING","结束");
        put("PRE_SOLIDIFICATION","预固化");
        put("SOLIDIFICATION","固化");
        put("DRYING","干燥");
        put("WARNING","告警");
    }};

}
