package com.ystar.ystarbaselib.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit


/***
 * @Author ystar
 * @Date 2022/3/3 13:55

 * @discriable   rxbinding2 设置防多次点击 view 扩展函数
 */
fun View.ClickOne(block: () -> Unit){
    RxView.clicks(this).throttleFirst(1000, TimeUnit.MILLISECONDS)
        .subscribe { block() }

}

/**
 * @Author ystar
 * @Date 2022/3/3 13:55
 * @param framgent 添加fmramgnet
 * @param frameId  目标framgent布局的id
 * @discriable   *添加framgnet 到act FragmentManager 扩展函数
 */
fun  FragmentManager.addFragmentToActivity(fragment: Fragment, frameId: Int){
    val transaction = this.beginTransaction()
    transaction.add(frameId,fragment)
    transaction.commitAllowingStateLoss()
}
