package com.ystar.ystarbaselib.basefragment

import com.ystar.ystarbaselib.databinding.BaseViewDefulteLoadingBinding
import com.ystar.ystarbaselib.utils.MyToastUtil
import com.ystar.ystarbaselib.view.BaseViewmodel

/**
 * @Author ystar
 * @Date 2022/3/4 10:12
 * @discriable
 */
class TestFragment: LazyFragment<BaseViewDefulteLoadingBinding, BaseViewmodel>() {

    override fun loadData() {
    MyToastUtil.showCenter("HAHAHA")
    }


}