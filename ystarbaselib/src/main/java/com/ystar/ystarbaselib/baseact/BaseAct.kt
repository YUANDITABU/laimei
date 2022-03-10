package com.ystar.ystarbaselib.baseact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseViewDefulttiltlebarBinding
import com.ystar.ystarbaselib.view.BaseViewmodel
import com.ystar.ystarbaselib.view.StatusManagerController
import com.ystar.ystarbaselib.view.Titcontroller
import java.lang.reflect.ParameterizedType

open class BaseAct<VB : ViewBinding, VM : BaseViewmodel> : AppCompatActivity() {
    lateinit var mViewBinding: VB
    lateinit var mViewmodel: VM
    lateinit var ystarBaseBinding: ActivityYstarBaseBinding

    val statusController: StatusManagerController by lazy {
        StatusManagerController(
            this,
            ystarBaseBinding,
            mViewmodel
        )
    }
    val titcontroller: Titcontroller by lazy { Titcontroller(this, ystarBaseBinding, mViewmodel) }
    val titleBarController: TitleBarController by lazy { TitleBarController(this, ystarBaseBinding, mViewmodel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(BaseLifecycleObserver())
        reflex2Generics()

    }

    /**
     * 反射
     * 将<T : ViewBinding,V :BaseViewmodel> 泛型参数初始化
     */
    private fun reflex2Generics() {
        //base布局
        ystarBaseBinding = ActivityYstarBaseBinding.inflate(layoutInflater)
        setContentView(ystarBaseBinding.root)
        //添加
        val type = javaClass.genericSuperclass as ParameterizedType
        //获取第一个参数泛型的的class ViewBinding
        val aClass = type.actualTypeArguments[0] as Class<VB>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java,
            ViewGroup::class.java,Boolean::class.java)
        mViewBinding = method.invoke(null, layoutInflater,ystarBaseBinding.flBaseContent,true) as VB

        //获取第二个参数泛型的的class Viewmodel
        val aClass1 = type.actualTypeArguments[1] as Class<VM>
        mViewmodel = ViewModelProvider(this).get(aClass1)
    }


}
