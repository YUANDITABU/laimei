package com.ystar.ystarbaselib.utils;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * author : Ystar
 * time   : 2019-06-11 rxbing
 * rxbinding2  防止重复点击
 */
public class ClickUtils {
    public  static  void  setClick(View view, CallBack callBack){
        RxView.clicks(view).throttleFirst(300, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                        if (callBack!=null)
                            callBack.onclick();
                });
    }

    public  static  void  SetOne(View view,CallBack callBack){
        RxView.clicks(view).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    if (callBack!=null)
                        callBack.onclick();
                });
    }



    public  interface  CallBack {
        void  onclick();
    }


}
