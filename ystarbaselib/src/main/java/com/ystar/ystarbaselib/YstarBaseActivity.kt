package com.ystar.ystarbaselib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.AppUtils
import com.ystar.ystarbaselib.baseact.BaseLifecycleObserver
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding.inflate
import com.ystar.ystarbaselib.utils.MyToastUtil
import com.ystar.ystarbaselib.view.BaseViewmodel
import com.ystar.ystarbaselib.view.StatusManagerController
import com.ystar.ystarbaselib.view.Titcontroller





open abstract  class YstarBaseActivity1<VB : ViewBinding,VM:BaseViewmodel>: YstarBaseActivity<VB>() {
    var mViewModel:VM?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel=getViewModel();

    }


    abstract fun getViewModel(): VM


}


open abstract  class YstarBaseActivity<VB : ViewBinding> : AppCompatActivity() {
   lateinit  var mBindview: VB
     var  mActivity :AppCompatActivity?=null
    var  ystarBaseBinding :ActivityYstarBaseBinding?=null

    var isLance = false //是否主页 主页设置双击退出
    var titcontroller :Titcontroller?=null
    var statusController :StatusManagerController?=null


       lateinit var actname:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ystarBaseBinding=  inflate(layoutInflater)
        setContentView(ystarBaseBinding!!.root)
        mBindview=getViewBinding();
        lifecycle.addObserver(BaseLifecycleObserver())
        mActivity=this //子类
         titcontroller = Titcontroller(this, ystarBaseBinding!!, BaseViewmodel())
         statusController = StatusManagerController(this, ystarBaseBinding!!, BaseViewmodel())
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        //保存持久化

    }

    override fun onResume() {
        super.onResume()
        //
    }



     abstract fun getViewBinding(): VB

     abstract fun setname(): String

    override fun onBackPressed() {
            onmybackpress(isLance)
    }








    /**
     * 双击退出
     */
    private var times: Long = 0
    open fun onmybackpress(isLance: Boolean) {
        if (isLance) {
            if (System.currentTimeMillis() - times > 1000) {
                MyToastUtil.showCenter("再次点击退出程序")
                times = System.currentTimeMillis()
            } else {
                AppUtils.exitApp()
            }
        } else {
            finish()
        }
    }

}
