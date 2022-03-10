package com.ystar.ystarbaselib.utils;


import android.app.Activity;
import android.app.ProgressDialog;

/**
 * 默认的一个加载弹窗
 */
public class ProgressDialgUtil {
    ProgressDialog dialog;
   private static ProgressDialgUtil sington;
    public static ProgressDialgUtil getInstance() {
        if (sington == null) {
            synchronized (ProgressDialgUtil.class) {
                if (sington == null) {
                    sington = new ProgressDialgUtil();
                }
            }
        }
        return sington;
    }


    public ProgressDialog create(Activity context, String str) {
        if (context==null)
            return null;
        if (dialog == null)
            dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(str);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public boolean isshowing() {
        return dialog == null ? false : dialog.isShowing() ? true : false;
    }

    public void dismiss() {
        if (isshowing())
            dialog.dismiss();
        dialog=null;
    }
}
