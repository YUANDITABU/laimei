package com.ystar.ystarbaselib.baseact

import android.text.TextUtils
import android.view.View
import android.view.ViewStub
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ColorUtils
import com.gyf.immersionbar.ImmersionBar
import com.ystar.ystarbaselib.R
import com.ystar.ystarbaselib.base.BaseController
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseViewDefulttiltlebarBinding
import com.ystar.ystarbaselib.databinding.BaseViewSearchtiltlebarBinding
import com.ystar.ystarbaselib.toobaradapter.DefultController
import com.ystar.ystarbaselib.toobaradapter.SearchController
import com.ystar.ystarbaselib.utils.ClickOne
import com.ystar.ystarbaselib.utils.ClickUtils
import com.ystar.ystarbaselib.view.*

/**
 * @Author ystar
 * @Date 2022/3/3 9:15
 * @discriable
 */


/**
 * sealed 密封类
 * 默认版本
 */
sealed class TitleType {
    class Defult : TitleType()
    class Search : TitleType()

}


/**
 * 中间健保存数据
 * @property builders Builders
 * @constructor
 */
class Helper(var builders: Builders) {

    fun build() {
        when (builders.type){
            is TitleType.Defult->{
                builders.viewStub.layoutResource = R.layout.base_view_defulttiltlebar
                if (builders.viewStub.parent != null) {
                    val inflate = builders.viewStub.inflate()
                    val baseViewDefulttiltlebarBinding: BaseViewDefulttiltlebarBinding =
                        BaseViewDefulttiltlebarBinding.bind(inflate)
                    DefultController(baseViewDefulttiltlebarBinding, builders)
                }
            }
            is TitleType.Search->{
                builders.viewStub.setLayoutResource(R.layout.base_view_searchtiltlebar)
                val inflate = builders.viewStub.inflate()
                val baseViewSearchtiltlebarBinding: BaseViewSearchtiltlebarBinding =
                    BaseViewSearchtiltlebarBinding.bind(inflate)
                SearchController(baseViewSearchtiltlebarBinding,builders)
            }


        }

            builders.immersionBar.navigationBarEnable(false) //是否可以修改导航栏颜色，默认为true
            .navigationBarWithKitkatEnable(false) //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
            .navigationBarWithEMUI3Enable(false) //是否可以修改emui3.x手机导航栏颜色，默认为true
        builders.immersionBar.fitsSystemWindows(true) //解决状态栏和布局重叠问题,true有距离，false全屏
        builders.immersionBar.statusBarColor(builders.backgroundColor) //状态栏颜色
        builders.immersionBar.keyboardEnable(true) //解决软键盘与底部输入框冲突问题
        builders.immersionBar.statusBarDarkFont(true).init()

    }

}

/**
 *外部参数(后面继续添加)
 * @property mActivity AppCompatActivity
 * @property viewStub ViewStub 布局
 * @property type TitleType  布局类型 默认还是 搜索
 * @property tvRightColor Int 状态栏管理 ImmersionBar
 * @property backgroundColor Int     默认背景
 * @property title String?     标题
 * @property isshowline Boolean 是否显示底部线条
 * @property backIcon Int 默认返回按钮
 * @property isHasRight Boolean  是否有右边视图
 * @property ivRight Int? //右边第一个图标  private  不让直接设置必须调用 ivRight方法
 * @property ivRightLister Function0<Unit> //第一个图标点击事件 private 不让直接设置必须调用 ivRight方法
 * @property ivRightOne Int? 右边第二个图标
 * @property ivRightOneLister Function0<Unit> 第二个图片点击事件
 * @property tvRitht String?  内容
 * @property tvRightLister Function0<Unit>  点击时间
 * @property tvRightColor Int //字体颜色
 * @constructor
 */
class Builders(val mActivity: AppCompatActivity, val viewStub: ViewStub, var type: TitleType, var immersionBar: ImmersionBar) {

    var backgroundColor: Int = R.color.base_white
    var title: String? = null
    var isshowline: Boolean = false
    var backIcon: Int = R.mipmap.base_defult_black


    private var isHasRight = false


    private var ivRight: Int? = null


    private lateinit var ivRightLister: () -> Unit

    private var ivRightOne: Int? = null

    private lateinit var ivRightOneLister: () -> Unit

    private var tvRitht: String? = null

    private lateinit var tvRightLister: () -> Unit

    var tvRightColor: Int = R.color.base_black


    //ivRight对外方法
    fun getivRight(): Int? {
        return ivRight
    }

    //ivRightLister对外方法
    fun getIvRightLister() = ivRightLister

    //ivRight设置方法
    fun ivRight(ivRight: Int, lister: () -> Unit) {
        this.ivRight = ivRight
        this.ivRightLister = lister
        this.isHasRight = true
    }

    fun getivRightOne(): Int? {
        return ivRightOne
    }


    fun getIvRightOneLister() = ivRightOneLister

    fun ivRightOne(ivRightone: Int, lister: () -> Unit) {
        this.ivRightOne = ivRightone
        this.ivRightOneLister = lister
        this.isHasRight = true
    }


    fun getTvRitht(): String? {
        return tvRitht
    }


    fun getTvRithtLister() = tvRightLister

    fun tvRight(tvRitht: String, lister: () -> Unit) {
        tvRight(tvRitht, tvRightColor, lister)
    }

    fun tvRight(tvRitht: String, textcolor: Int, lister: () -> Unit) {
        this.tvRitht = tvRitht
        this.tvRightLister = lister
        this.tvRightColor = textcolor
        this.isHasRight = true
    }

    fun getisHasRight(): Boolean {
        return isHasRight

    }


}


 class TitleBarController(
    mActivity: AppCompatActivity,
    mViewBinding: ActivityYstarBaseBinding,
    mViewModel: BaseViewmodel
) : BaseController<ActivityYstarBaseBinding, BaseViewmodel>(mActivity, mViewBinding, mViewModel) {

    val immersionBar: ImmersionBar = ImmersionBar.with(mActivity)


    fun create(block: Builders.() -> Builders) =
        Helper(block(Builders(mActivity, mViewBinding.vsToolbar, TitleType.Defult(),immersionBar)))

    fun create(type: TitleType, block: Builders.() -> Builders) =
        Helper(block(Builders(mActivity, mViewBinding.vsToolbar, type,immersionBar)))





}