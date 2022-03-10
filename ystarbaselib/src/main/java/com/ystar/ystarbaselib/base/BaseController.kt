package com.ystar.ystarbaselib.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.ystar.ystarbaselib.view.BaseViewmodel

/**
 *
 * @Author ystar
 * @Date 2021/11/26 9:51
 * @discriable
 */
open  class BaseController< VB ,  VM>(val mActivity: AppCompatActivity, val mViewBinding: VB,val mViewModel: VM)