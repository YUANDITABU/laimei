package com.ystar.ystarbaselib.view

import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.gyf.immersionbar.ImmersionBar
import com.ystar.ystarbaselib.R
import com.ystar.ystarbaselib.base.BaseController
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseViewDefulttiltlebarBinding
import com.ystar.ystarbaselib.databinding.BaseViewSearchtiltlebarBinding
import com.ystar.ystarbaselib.utils.ClickUtils
import kotlinx.android.synthetic.main.base_view_searchtiltlebar.view.*

/**
 * @Author ystar
 * @Date 2021/11/26 10:58
 * @discriable 标题栏设置
 */

// 默认 和搜索
enum class TITLEType {
    Defult,
    Search
}




class Titcontroller(
    mAcitivity: AppCompatActivity,
    bindview: ActivityYstarBaseBinding,
    mViewmodel: BaseViewmodel,
) : BaseController<ActivityYstarBaseBinding, BaseViewmodel>(mAcitivity, bindview, mViewmodel) {








    //标题栏————————————————————————start——————————————————————状态栏
    inner class TitleBarBuilder {
        var backgroundColor: Int = R.color.base_white //默认颜色
        var backIcon: Int = R.mipmap.base_defult_black //默认返回按钮
        var titleColor: Int = R.color.base_black //字体颜色
        //标题
        var title: String? = null
        var iv_right  = 0//右侧按钮

        var iv_right1 //右侧按钮
                = 0
        var tvrightColor: Int = R.color.base_black //字体颜色
        var tv_right //右侧字体
                : String? = null
        var seachhint //右侧字体
                : String? = null
        var ishasright = false //是否又右边视图
        var isshowline = false //是否显示线条
        var mlister //文字
                : ToolbarListerTv? = null
        var mlister1 // 图标
                : ToolbarListerIv? = null
        var mlister2 //图标1
                : ToolbarListerIv1? = null
        var searchLister: ToolbarSearchTv? = null
        var titletype: TITLEType = TITLEType.Defult
        fun setSeachhint(s: String?): TitleBarBuilder {
            seachhint = s
            return this
        }

        fun setTitletype(titletype: TITLEType): TitleBarBuilder {
            this.titletype = titletype
            return this
        }

        fun setRightTVlister(lister: ToolbarListerTv?): TitleBarBuilder {
            mlister = lister
            return this
        }

        fun setSearchLister(searchLister: ToolbarSearchTv?): TitleBarBuilder {
            this.searchLister = searchLister
            return this
        }




        fun setRightIVlister(lister: ToolbarListerIv?): TitleBarBuilder {
            mlister1 = lister
            return this
        }

        fun setRightIVlister1(lister: ToolbarListerIv1?): TitleBarBuilder {
            mlister2 = lister
            return this
        }

        fun setTvRight(tv_right: String?): TitleBarBuilder {
            this.tv_right = tv_right
            ishasright = true
            return this
        }

        fun setTvRightColor(tvrightColor: Int): TitleBarBuilder {
            this.tvrightColor = tvrightColor
            ishasright = true
            return this
        }

        fun setIVRight(iv_right: Int): TitleBarBuilder {
            this.iv_right = iv_right
            ishasright = true
            return this
        }

        fun setIVRight1(iv_right1: Int): TitleBarBuilder {
            this.iv_right1 = iv_right1
            ishasright = true
            return this
        }

        //背景颜色
        fun setBackgroundcolor(backgroundColor: Int): TitleBarBuilder {
            this.backgroundColor = backgroundColor
            return this
        }

        //标题颜色
        fun setTitlecolor(titleColor: Int): TitleBarBuilder {
            this.titleColor = titleColor
            return this
        }

        //返回按钮
        fun setbackIcon(backIcon: Int): TitleBarBuilder {
            this.backIcon = backIcon
            return this
        }

        //设置标题
        fun setTitle(title: String?): TitleBarBuilder {
            this.title = title
            return this
        }

        fun build() {

            when (titletype) {
                TITLEType.Defult -> {
                    DefultToolbar(mActivity , mViewBinding, this)
                }
                TITLEType.Search -> {

                    SearchToolbar(mActivity, mViewBinding, this)
                } }
            StatusBarBuilder()
                .setFullScreen(true)
                .setStatusBarColor(backgroundColor)
                .builder()
        }
    }


    inner   class StatusBarBuilder {
        var statusBarDarkFont = true //状态栏字体是深色，不写默认为亮色ture字体黑色，false 白色
        var statusBarColor: Int = R.color.base_white //状态栏颜色 默认白色
        var isFullScreen = true //解决状态栏和布局重叠问题,true流距离，false全屏
        var isKeyboardEnable = false ////解决软键盘与底部输入框冲突问题，默认为false；
        var keyboardMode ///单独指定软键盘模式
                = 0


        fun setStatusBarDarkFont(statusBarDarkFont: Boolean): StatusBarBuilder {
            this.statusBarDarkFont = statusBarDarkFont
            return this
        }

        fun setStatusBarColor(statusBarColor: Int): StatusBarBuilder {
            this.statusBarColor = statusBarColor
            return this
        }

        fun setFullScreen(fullScreen: Boolean): StatusBarBuilder {
            isFullScreen = fullScreen
            return this
        }

        fun setKeyboardEnable(keyboardEnable: Boolean): StatusBarBuilder {
            isKeyboardEnable = keyboardEnable
            return this
        }

        fun setKeyboardMode(keyboardMode: Int): StatusBarBuilder {
            this.keyboardMode = keyboardMode
            return this
        }


        fun builder(): ImmersionBar? {
            val immersionBar: ImmersionBar = ImmersionBar.with(mActivity)
            immersionBar.navigationBarEnable(false) //是否可以修改导航栏颜色，默认为true
                .navigationBarWithKitkatEnable(false) //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
                .navigationBarWithEMUI3Enable(false) //是否可以修改emui3.x手机导航栏颜色，默认为true
            immersionBar.fitsSystemWindows(this.isFullScreen) //解决状态栏和布局重叠问题,true有距离，false全屏
            immersionBar.statusBarColor(this.statusBarColor) //状态栏颜色
            immersionBar.keyboardEnable(this.isKeyboardEnable) //解决软键盘与底部输入框冲突问题
            immersionBar.keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) //单独指定软键盘模式
            immersionBar.statusBarDarkFont(this.statusBarDarkFont).init()
            return immersionBar
        }

    }


    //标题栏————————————————————————end——————————————————————状态栏


    interface ToolbarListerTv {
        fun ClickBtn_TV()
    }

    interface ToolbarSearchTv {
        fun search(keyword: String?)
    }

    interface ToolbarListerIv {
        fun ClickBtn_Iv()
    }

    interface ToolbarListerIv1 {
        fun ClickBtn_Iv1()
    }


}








/**
 * 默认
 */
class DefultToolbar(
    mAcitivity: AppCompatActivity,
    bindview: ActivityYstarBaseBinding,
    titleBarBuilder: Titcontroller.TitleBarBuilder,
) {

    init {
        //装在默认标题烂

        bindview.vsToolbar.layoutResource = R.layout.base_view_defulttiltlebar
        val inflate = bindview.vsToolbar.inflate()
        val baseViewDefulttiltlebarBinding: BaseViewDefulttiltlebarBinding =
            BaseViewDefulttiltlebarBinding.bind(inflate)

        //背景
        baseViewDefulttiltlebarBinding.consContainer.setBackgroundResource(titleBarBuilder.backgroundColor)
        //是否显示line
        baseViewDefulttiltlebarBinding.vLine.visibility =
            if (titleBarBuilder.isshowline) View.VISIBLE else View.GONE
        //如果设置了backicon
        titleBarBuilder.backIcon?.let {
            baseViewDefulttiltlebarBinding.ivBack.setImageResource(it)
            ClickUtils.setClick(baseViewDefulttiltlebarBinding.ivBack) {mAcitivity.onBackPressed()  }

        }
        //设置标题
        titleBarBuilder.title?.let {
            baseViewDefulttiltlebarBinding.title.setText(titleBarBuilder.title)
            baseViewDefulttiltlebarBinding.title.setTextColor(ColorUtils.getColor(titleBarBuilder.titleColor))
        }


        //是否有右边视图
        if (titleBarBuilder.ishasright) {
            //显示右边适配 目前暂时3个按钮 1个文字 2个图片
            baseViewDefulttiltlebarBinding.linRight.visibility = View.VISIBLE
            // 图片1设置
            if (titleBarBuilder.iv_right === 0) {
                baseViewDefulttiltlebarBinding.ivRight.visibility = View.GONE
            } else {
                baseViewDefulttiltlebarBinding.ivRight.visibility = View.VISIBLE
                baseViewDefulttiltlebarBinding.ivRight.setImageResource(titleBarBuilder.iv_right)
                ClickUtils.setClick(baseViewDefulttiltlebarBinding.ivRight) {
                        titleBarBuilder.mlister1?.ClickBtn_Iv()
                }
            }

            //图片2设置
            if (titleBarBuilder.iv_right1 === 0) {
                baseViewDefulttiltlebarBinding.ivRight1.visibility = View.GONE
            } else {
                baseViewDefulttiltlebarBinding.ivRight1.visibility = View.VISIBLE
                baseViewDefulttiltlebarBinding.ivRight1.setImageResource(titleBarBuilder.iv_right1)
                ClickUtils.setClick(baseViewDefulttiltlebarBinding.ivRight1) {
                        titleBarBuilder.mlister2?.ClickBtn_Iv1()

                }
            }
            //文字
            if (TextUtils.isEmpty(titleBarBuilder.tv_right)) {
                baseViewDefulttiltlebarBinding.tvRight.visibility = View.GONE
            } else {
                baseViewDefulttiltlebarBinding.tvRight.visibility = View.VISIBLE
                baseViewDefulttiltlebarBinding.tvRight.setText(titleBarBuilder.tv_right)
                baseViewDefulttiltlebarBinding.tvRight.setTextColor(ColorUtils.getColor(
                    titleBarBuilder.tvrightColor))
                ClickUtils.setClick(baseViewDefulttiltlebarBinding.tvRight) {

                        titleBarBuilder.mlister?.ClickBtn_TV()

                }
            }
        } else {
            baseViewDefulttiltlebarBinding.linRight.visibility = View.INVISIBLE
        }

    }
}

/**
 * 搜索
 * @constructor
 */
class SearchToolbar(
    mAcitivity: AppCompatActivity,
    bindview: ActivityYstarBaseBinding,
    titleBarBuilder: Titcontroller.TitleBarBuilder,
){

    init {
        bindview.vsToolbar.setLayoutResource(R.layout.base_view_searchtiltlebar)
        val inflate = bindview.vsToolbar.inflate()
        val baseViewSearchtiltlebarBinding: BaseViewSearchtiltlebarBinding = BaseViewSearchtiltlebarBinding.bind(inflate)
        //背景
        baseViewSearchtiltlebarBinding.consContainer.setBackgroundResource(titleBarBuilder.backgroundColor)
        //是否显示line
        baseViewSearchtiltlebarBinding.vLine.visibility =
            if (titleBarBuilder.isshowline) View.VISIBLE else View.GONE
        //如果设置了backicon
        titleBarBuilder.backIcon?.let {
            baseViewSearchtiltlebarBinding.ivBack.setImageResource(it)
            ClickUtils.setClick(baseViewSearchtiltlebarBinding.ivBack) {mAcitivity.onBackPressed()  }
        }
        //设置标题
        titleBarBuilder.title?.let {
            baseViewSearchtiltlebarBinding.title.setText(titleBarBuilder.title)
            baseViewSearchtiltlebarBinding.title.setTextColor(ColorUtils.getColor(titleBarBuilder.titleColor))
        }

        //所搜提示
        titleBarBuilder.seachhint?.let {
            baseViewSearchtiltlebarBinding. etSearch.setHint(titleBarBuilder.seachhint)
        }

        if (titleBarBuilder.ishasright) { //是否有右边按钮
            baseViewSearchtiltlebarBinding.linRight.visibility=View.VISIBLE

            if (titleBarBuilder.iv_right === 0) {
                baseViewSearchtiltlebarBinding.ivRight.setVisibility(View.GONE)
            }
            else {
                baseViewSearchtiltlebarBinding.ivRight.setVisibility(View.VISIBLE)
                baseViewSearchtiltlebarBinding.ivRight.setImageResource(titleBarBuilder.iv_right)
                ClickUtils.setClick(baseViewSearchtiltlebarBinding.ivRight) {
                    if ( baseViewSearchtiltlebarBinding.rlSearch.visibility == View.GONE)  baseViewSearchtiltlebarBinding.rlSearch.visibility = View.VISIBLE
                    baseViewSearchtiltlebarBinding.rlSearch.requestFocus() //聚焦
                    KeyboardUtils.showSoftInput()
                    baseViewSearchtiltlebarBinding.tvRight.visibility = View.VISIBLE
                    baseViewSearchtiltlebarBinding. ivRight.setVisibility(View.GONE)
                    baseViewSearchtiltlebarBinding.etSearch.setText("")
                     titleBarBuilder.mlister1?.ClickBtn_Iv()
                }
            }




            if (titleBarBuilder.iv_right1 === 0) {
                baseViewSearchtiltlebarBinding.ivRight1.visibility = View.GONE
            } else {
                baseViewSearchtiltlebarBinding.ivRight1.visibility = View.VISIBLE
                baseViewSearchtiltlebarBinding.ivRight1.setImageResource(titleBarBuilder.iv_right1)
                ClickUtils.setClick(baseViewSearchtiltlebarBinding.ivRight1) {

                        titleBarBuilder.mlister2?.ClickBtn_Iv1()

                }


            }
            if (TextUtils.isEmpty(titleBarBuilder.tv_right)) {
                baseViewSearchtiltlebarBinding.tvRight.visibility = View.GONE
            } else {
                baseViewSearchtiltlebarBinding.tvRight.visibility = View.GONE
                baseViewSearchtiltlebarBinding.tvRight.text = titleBarBuilder.tv_right
                baseViewSearchtiltlebarBinding.tvRight.setTextColor(ColorUtils.getColor(titleBarBuilder.tvrightColor))
                ClickUtils.setClick(baseViewSearchtiltlebarBinding.tvRight) {
                    if (baseViewSearchtiltlebarBinding.rlSearch.visibility == View.VISIBLE) {
                        baseViewSearchtiltlebarBinding.rlSearch.visibility = View.GONE
                    }
                    baseViewSearchtiltlebarBinding.tvRight.visibility = View.GONE
                    baseViewSearchtiltlebarBinding.ivRight.setVisibility(View.VISIBLE)
                    KeyboardUtils.hideSoftInput(mAcitivity)
                    baseViewSearchtiltlebarBinding.etSearch.setText("")

                    titleBarBuilder.mlister?.ClickBtn_TV()
                }
            }

        } else {
            baseViewSearchtiltlebarBinding.linRight.visibility = View.INVISIBLE
        }


        ClickUtils.setClick(baseViewSearchtiltlebarBinding.iv2) { baseViewSearchtiltlebarBinding.etSearch.setText("") }
        //  if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        //  if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        baseViewSearchtiltlebarBinding.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (titleBarBuilder.searchLister != null) titleBarBuilder.searchLister!!.search(
                    baseViewSearchtiltlebarBinding.etSearch.getText().toString().trim { it <= ' ' })
            }
            false
        })


    }
}



