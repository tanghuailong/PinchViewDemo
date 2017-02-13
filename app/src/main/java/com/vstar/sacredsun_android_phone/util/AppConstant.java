package com.vstar.sacredsun_android_phone.util;

import android.support.annotation.IntDef;

/**
 * Created by tanghuailong on 2017/2/13.
 */

public class AppConstant {

    public static final int POSITIVE_ONE = 1;
    public static final int POSITIVE_TWO = 2;
    public static final int NEGATIVE_ONE = 3;
    public static final int NEGATIVE_TWO = 4;

    @IntDef({POSITIVE_ONE,POSITIVE_TWO,NEGATIVE_ONE,NEGATIVE_TWO})
    public @interface Position{
    }
}
