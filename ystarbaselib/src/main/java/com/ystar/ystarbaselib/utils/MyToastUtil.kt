package com.ystar.ystarbaselib.utils

import android.view.Gravity
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.ystar.ystarbaselib.R
import com.ystar.ystarbaselib.base.BaseApplication

/**
 * @Author ystar
 * @Date 2021/11/26 15:00
 * @discriable
 */
class MyToastUtil {



    

        companion object{
            @JvmStatic
            fun showCenter(string: String?) {

                ToastUtils.make()
                    .setTextColor(ColorUtils.getColor(R.color.base_white))
                    .setBgColor(ColorUtils.getColor(R.color.base_black))
                    .setGravity(Gravity.CENTER, 0, 0).show(string)
            }

            @JvmStatic
            fun showLongCenter(string: String?) {
                ToastUtils.make()
                    .setTextColor(ColorUtils.getColor(R.color.base_white))
                    .setBgColor(ColorUtils.getColor(R.color.base_black))
                    .setDurationIsLong(true)
                    .setGravity(Gravity.CENTER, 0, 0).show(string)
            }




        }



}