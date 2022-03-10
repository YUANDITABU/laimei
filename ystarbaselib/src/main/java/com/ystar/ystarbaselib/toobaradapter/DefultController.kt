package com.ystar.ystarbaselib.toobaradapter

import android.view.View
import com.ystar.ystarbaselib.baseact.Builders
import com.ystar.ystarbaselib.databinding.BaseViewDefulttiltlebarBinding
import com.ystar.ystarbaselib.databinding.BaseViewSearchtiltlebarBinding
import com.ystar.ystarbaselib.utils.ClickOne

/**
 * @Author ystar
 * @Date 2022/3/3 11:07
 * @discriable 默认的标题栏
 * @param defultTiltlearBinding 布局UI
 * @param builder 外部建造者的参数
 */
class DefultController(mViewBinding: BaseViewDefulttiltlebarBinding,builder: Builders) {

    init {
        //公共部分
        common(mViewBinding,builder)
        //右边部分 iv-tv-iv
        right(mViewBinding,builder)
    }


    private fun common(mViewBinding: BaseViewDefulttiltlebarBinding,builder: Builders){
        //标题
        builder.title?.apply {
            mViewBinding.title.text=this
        }?:apply{
            mViewBinding.title.text="没空"
        }
        //背景颜色
        mViewBinding.consContainer.setBackgroundResource(builder.backgroundColor)
        mViewBinding.vLine.visibility = if (builder.isshowline) View.VISIBLE else View.GONE
        //返回按钮
        mViewBinding.ivBack.setImageResource(builder.backIcon)
        mViewBinding.ivBack.ClickOne { builder.mActivity.onBackPressed() }

    }

    private fun right(mViewBinding: BaseViewDefulttiltlebarBinding,builder: Builders){
        if (!builder.getisHasRight()){
            mViewBinding.linRight.visibility = View.INVISIBLE
            return
        }

        mViewBinding.linRight.visibility = View.VISIBLE

        builder.getivRight()?.apply {
            mViewBinding.ivRight.visibility = View.VISIBLE
            mViewBinding.ivRight.setImageResource(this)
            mViewBinding.ivRight.ClickOne {
                     builder.getIvRightLister().invoke()
            }
        }?:apply{
            mViewBinding.ivRight.visibility = View.GONE
        }

        builder.getivRightOne()?.apply {
            mViewBinding.ivRight1.visibility = View.VISIBLE
            mViewBinding.ivRight1.setImageResource(this)
            mViewBinding.ivRight1.ClickOne {
                builder.getIvRightOneLister().invoke()
            }
        }?:apply{
            mViewBinding.ivRight1.visibility = View.GONE
        }


        builder.getTvRitht()?.apply {
            mViewBinding.tvRight.visibility=View.VISIBLE
            mViewBinding.tvRight.text= builder.getTvRitht()
            mViewBinding.tvRight.setTextColor(builder.tvRightColor)
            mViewBinding.tvRight.ClickOne {
                builder.getTvRithtLister().invoke()
            }
        }?:apply {
            mViewBinding.tvRight.visibility = View.GONE
        }


    }


}

/**
 * @Author ystar
 * @Date 2022/3/3 11:07
 * @discriable 搜索标题栏
 * @param defultTiltlearBinding 布局UI
 * @param builder 外部建造者的参数
 */
class SearchController(mViewBinding: BaseViewSearchtiltlebarBinding, builder: Builders) {

    init {
        //公共部分
        common(mViewBinding,builder)
        //右边部分 iv-tv-iv
        right(mViewBinding,builder)
    }


    private fun common(mViewBinding: BaseViewSearchtiltlebarBinding,builder: Builders){
        //标题
        builder.title?.apply {
            mViewBinding.title.text=this
        }?:apply{
            mViewBinding.title.text="没空"
        }
        //背景颜色
        mViewBinding.consContainer.setBackgroundResource(builder.backgroundColor)
        mViewBinding.vLine.visibility = if (builder.isshowline) View.VISIBLE else View.GONE
        //返回按钮
        mViewBinding.ivBack.setImageResource(builder.backIcon)
        mViewBinding.ivBack.ClickOne { builder.mActivity.onBackPressed() }

    }

    private fun right(mViewBinding: BaseViewSearchtiltlebarBinding,builder: Builders){
        if (!builder.getisHasRight()){
            mViewBinding.linRight.visibility = View.INVISIBLE
            return
        }

        mViewBinding.linRight.visibility = View.VISIBLE

        builder.getivRight()?.apply {
            mViewBinding.ivRight.visibility = View.VISIBLE
            mViewBinding.ivRight.setImageResource(this)
            mViewBinding.ivRight.ClickOne {
                builder.getIvRightLister().invoke()
            }
        }?:apply{
            mViewBinding.ivRight.visibility = View.GONE
        }

        builder.getivRightOne()?.apply {
            mViewBinding.ivRight1.visibility = View.VISIBLE
            mViewBinding.ivRight1.setImageResource(this)
            mViewBinding.ivRight1.ClickOne {
                builder.getIvRightOneLister().invoke()
            }
        }?:apply{
            mViewBinding.ivRight1.visibility = View.GONE
        }


        builder.getTvRitht()?.apply {
            mViewBinding.tvRight.visibility=View.VISIBLE
            mViewBinding.tvRight.text= builder.getTvRitht()
            mViewBinding.tvRight.setTextColor(builder.tvRightColor)
            mViewBinding.tvRight.ClickOne {
                builder.getTvRithtLister().invoke()
            }
        }?:apply {
            mViewBinding.tvRight.visibility = View.GONE
        }


    }


}