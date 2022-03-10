package com.ystar.ystarbaselib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ystar.ystarbaselib.databinding.BaseFragmentBinding
import com.ystar.ystarbaselib.view.BaseViewmodel
import com.ystar.ystarbaselib.view.StatusFragmentController

/**
 * @Author ystar
 * @Date 2021/11/29 10:05
 * @discriable
 */
open  abstract  class YstarBaseFragment<VB : ViewBinding> : Fragment() {
    var mBindview: VB? = null
    var  mActivity : AppCompatActivity?=null
    var  ystarBaseBinding : BaseFragmentBinding?=null

    //是否第一次加载数据 懒加载
    protected var isFirstLoad = true
    protected var isFirstLoad1 = true

    var statusController : StatusFragmentController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // ARouter.getInstance().inject(this)
        mActivity = activity as AppCompatActivity?
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        ystarBaseBinding = BaseFragmentBinding.inflate(layoutInflater) //这个是base的绑定
        mBindview = getViewBinding()
        statusController = StatusFragmentController(activity as AppCompatActivity, ystarBaseBinding!!, BaseViewmodel())
        return ystarBaseBinding!!.root
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isFirstLoad1 && isVisibleToUser) {
            onLazyView1()
            isFirstLoad1 = false
        }
    }

    override fun onResume() {
        super.onResume()
        //这个就是同个getUserVisibleHint 懒加载
        if (isFirstLoad1 && userVisibleHint) {
            onLazyView1()
            isFirstLoad1 = false
        }
        //FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 通过setmaxlife 去实现懒加载
        if (isFirstLoad) {
            onLazyView()
            isFirstLoad = false
        }
    }



    protected open fun onLazyView1() {}
    protected open fun onLazyView() {}
    abstract fun getViewBinding(): VB
}