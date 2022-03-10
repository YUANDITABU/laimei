package com.ystar.ystarbaselib.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author ystar
 * @Date 2021/11/26 11:01
 * @discriable
 */
open class BaseViewmodel : ViewModel() {
   var  mpage :Int =1
   var  mpagesize :Int = 20

}