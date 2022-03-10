package com.ystar.ystarbaselib.baseact

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ystar.ystarbaselib.utils.ProgressDialgUtil

/**
 * @Author ystar
 * @Date 2022/3/3 9:09
 * @discriable baseact 生命周期监听
 */
class BaseLifecycleObserver :LifecycleObserver {


    //onDestory 方式一切dialog处理
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        ProgressDialgUtil.getInstance().dismiss()
    }
}