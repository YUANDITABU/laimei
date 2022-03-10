package com.ystar.ystarbaselib.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.Utils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.ystar.ystarbaselib.R

/**
 * @Author ystar
 * @Date 2021/11/26 14:39
 * @discriable
 */
 open class BaseApplication : Application() {
    //static 代码段可以防止内存泄露
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            //  layout.setPrimaryColorsId(R.color.base_white, R.color.base_black);//全局设置主题颜色
            layout.setEnableOverScrollDrag(false)
            layout.setEnableAutoLoadMore(true)
            layout.setDisableContentWhenRefresh(false)
            MaterialHeader(context)
                .setScrollableWhenRefreshing(true)
                .setColorSchemeColors(ColorUtils.getColor(R.color.base_threm)) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header

            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            //   return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context)
        }


    }




    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }






    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}