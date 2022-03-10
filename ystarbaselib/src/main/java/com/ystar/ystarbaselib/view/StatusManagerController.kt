package com.ystar.ystarbaselib.view

import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ystar.ystarbaselib.R
import com.ystar.ystarbaselib.base.BaseController
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseViewDefulteLoadingBinding

/**
 * @Author ystar
 * @Date 2021/11/29 10:19
 * @discriable 状态页
 */
class StatusManagerController(
    mAcitivity: AppCompatActivity,
    val mBindview: ActivityYstarBaseBinding,
    mViewmodel: BaseViewmodel,
) : BaseController<ActivityYstarBaseBinding, BaseViewmodel>(mAcitivity, mBindview, mViewmodel) {

    lateinit var baseViewDefulteLoadingBinding: BaseViewDefulteLoadingBinding
    private   var  defultRes= R.mipmap.ic_null

    /**
     * 状态管理页-加载
     */
    fun showLoading() {
        initStubview()
    }

    /**
     * 状态管理页 -关闭
     *
     */
    fun closeStatusView() {
        mBindview.vsShowStatus.visibility = View.GONE
    }
    /**
     *状态管理页 -错误
     *
     */
    fun showError(showmsg: String) {
        showError(showmsg, defultRes)
    }

    /**
     *
     * @param res Int 显示图片
     */
    fun showError(res: Int) {
        showError(null, res)

    }


    fun showError(showmsg: String?, res: Int) {
        initStubview()
        baseViewDefulteLoadingBinding?.let {
            it.ivProgress.visibility = View.GONE
            it.linEmpty.visibility = View.VISIBLE
            it.ivEmpty.visibility = View.VISIBLE
            it.ivEmpty.setImageResource(if (res == 0) defultRes else res)
            //设置文字显示与内容
            showmsg?.let { showmsg ->
                it.tvEmpty.visibility == View.VISIBLE
                it.tvEmpty.text = showmsg
            } ?: {
                it.tvEmpty.visibility == View.GONE
            }

        }
    }

    /**
     * private 不对外
     *状态管理页- 初始化stubview
     */
    private fun initStubview() {
        //！=null 未被加载  如果被加载parent 返回 null
        if (mBindview.vsShowStatus.parent != null) {
            mBindview.vsShowStatus.layoutResource = R.layout.base_view_defulte_loading
            //加载viewstub
            val inflate = mBindview.vsShowStatus.inflate()
            //viewbind 绑定
            baseViewDefulteLoadingBinding = BaseViewDefulteLoadingBinding.bind(inflate)
        }

    }

}