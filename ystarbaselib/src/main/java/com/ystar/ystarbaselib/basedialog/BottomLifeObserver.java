package com.ystar.ystarbaselib.basedialog;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author ystar
 * 创建日期：2021/5/6
 * 描述：底部的
 */
public class BottomLifeObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny() {
        Log.d("BottomLifeObserver", "onAnyonAnyonAnyonAnyonAny");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.d("BottomLifeObserver", "oncreateoncreateoncreateoncreate");


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        Log.d("BottomLifeObserver", "onStartonStartonStartonStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.d("BottomLifeObserver", "onResumeonResumeonResumeonResume");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPuse() {
        Log.d("BottomLifeObserver", "onPuseonPuseonPuseonPuseonPuse");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.d("BottomLifeObserver", "onDestroyonDestroyonDestroyonDestroy");
    }

}
