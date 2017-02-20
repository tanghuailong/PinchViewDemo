package com.vstar.sacredsun_android_phone.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.vstar.sacredsun_android_phone.R;

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

    /**
     * 获取启动模式
     *
     * @param context
     * @return
     */
    public static StartMode getStartMode(Context context) {

        if ((Boolean) SPHelper.get(context, context.getString(R.string.FIRST_LAUNCH), false).equals(false)) {
            return StartMode.NO_SETTING;
        } else if (SPHelper.get(context, context.getString(R.string.IS_LOGIN), false).equals(false)) {
            return StartMode.NO_LOGIN;
        } else {
            return StartMode.NORMAL;
        }
    }

    /**
     * 获取edittext中的字符串
     *
     * @param editText
     * @return
     */
    public static String getEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * 显示Dialog
     *
     * @param title            dialog的标题
     * @param icon             dialog的图标
     * @param msg              dialog的内容
     * @param positiveListener 点击确认后操作
     */
    public static void showDialog(Context context, String title, int icon, String msg, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setIcon(icon)
                .setMessage(msg)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", positiveListener)
                .show();
    }
}
