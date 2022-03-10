package com.ystar.ystarbaselib.basefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ystar.ystarbaselib.view.BaseViewmodel

/**
 * @Author ystar
 * @Date 2022/3/4 10:57
 *
 * MyPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList);
 * @discriable 懒加载fragment
 */
open abstract  class LazyFragment<VB : ViewBinding, VM : BaseViewmodel> : BaseFragment<VB, VM>() {

    private var isFirst=true




    override fun onResume() {
        super.onResume()
        load()
    }


    /**
     * onresume 调用实现方法
     */
    private fun load(){
        // 加载数据时判断是否完成view的初始化，以及是不是第一次加载此数据
        if (isonCreateed &&isFirst) {
            loadData()
            // 加载第一次数据后改变状态，后续不再重复加载
            isFirst = false
        }

    }

    /**
     *
     *对外懒加载方法
     */
    abstract  fun  loadData()

}