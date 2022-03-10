package com.ystar.ystarbaselib.view

import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ystar.ystarbaselib.R
import com.ystar.ystarbaselib.base.BaseController
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseFragmentBinding
import com.ystar.ystarbaselib.databinding.BaseViewDefulteLoadingBinding

/**
 * @Author ystar
 * @Date 2021/11/29 11:40
 * @discriable
 */
class StatusFragmentController(
    mAcitivity: AppCompatActivity,
    bindview: BaseFragmentBinding,
    mViewmodel: BaseViewmodel
) : BaseController<BaseFragmentBinding,BaseViewmodel>(mAcitivity, bindview, mViewmodel) {
    var baseViewDefulteLoadingBinding : BaseViewDefulteLoadingBinding?=null



    fun showLoading() {
        if (mViewBinding.vsShowview.parent != null) {
            mViewBinding.vsShowview.layoutResource = R.layout.base_view_defulte_loading
            val inflate = mViewBinding.vsShowview.inflate()
            baseViewDefulteLoadingBinding= BaseViewDefulteLoadingBinding.bind(inflate)
        }
    }

    /**
     *
     * @param showmsg String 显示msg
     */
    fun showError(showmsg: String){
        showError(showmsg, R.mipmap.ic_null)
    }
    /**
     *
     * @param res Int 显示图片
     */
    fun showError(res: Int) {
        showError(null, res)

    }


    fun showError(showmsg: String?, res: Int) {
        if (mViewBinding.vsShowview.getParent() != null) {
            mViewBinding.vsShowview.layoutResource = R.layout.base_view_defulte_loading
            val inflate = mViewBinding.vsShowview.inflate()
            baseViewDefulteLoadingBinding= BaseViewDefulteLoadingBinding.bind(inflate)

        }

        baseViewDefulteLoadingBinding?.let {
            it.ivProgress.visibility = View.GONE
            it.linEmpty.visibility = View.VISIBLE
            it.ivEmpty.visibility = View.VISIBLE

            if (res == 0)  it.ivEmpty.setImageResource(R.mipmap.ic_null) else it.ivEmpty.setImageResource(
                res)

            //设置文字显示与内容
            it.tvEmpty.text = showmsg
            if (TextUtils.isEmpty(showmsg) )  it.tvEmpty.visibility== View.GONE else it.tvEmpty.visibility== View.VISIBLE
        }
    }

    fun dismissView() {
        mViewBinding.vsShowview.setVisibility(View.GONE)
    }

}