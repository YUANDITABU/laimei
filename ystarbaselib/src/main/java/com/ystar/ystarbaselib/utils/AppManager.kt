package com.ystar.ystarbaselib.utils

import android.app.Activity
import java.util.*

/**
 * @Author ystar
 * @Date 2021/11/29 13:58
 * @discriable
 */
class AppManager {

    private var activityStack: Stack<Activity?>? = null

    private fun AppManager() {}

    private object SingleHolder {
        val instance = AppManager()
    }

    fun getInstance(): AppManager? {
        return SingleHolder.instance
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
        }
    }

    /**
     * 是否有activity
     */
    fun isActivity(): Boolean {
        return if (activityStack != null) {
            !activityStack!!.isEmpty()
        } else false
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                finishActivity(activityStack!![i])
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    fun getActivity(cls: Class<*>): Activity? {
        if (activityStack != null) {
            for (activity in activityStack!!) {
                if (activity!!.javaClass == cls) {
                    return activity
                }
            }
        }
        return null
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {
            finishAllActivity()
        } catch (e: Exception) {
            activityStack!!.clear()
            e.printStackTrace()
        }
    }


}