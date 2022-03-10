package com.ystar.ystarbaselib.basefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ystar.ystarbaselib.databinding.ActivityYstarBaseBinding
import com.ystar.ystarbaselib.databinding.BaseFragmentBinding
import com.ystar.ystarbaselib.view.BaseViewmodel
import com.ystar.ystarbaselib.view.StatusFragmentController
import java.lang.reflect.ParameterizedType

/**
 * @Author ystar
 * @Date 2022/3/4 9:14
 * @discriable
 */
open abstract class BaseFragment<VB : ViewBinding, VM : BaseViewmodel> : Fragment()  {

    var isonCreateed= false
    lateinit var mViewBinding: VB
    lateinit var mViewmodel: VM
    lateinit var ystarBaseBinding: BaseFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ARouter.getInstance().inject(this)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //这个是base的绑定
        reflex2Generics()
        isonCreateed=true
        return ystarBaseBinding.root

    }



    /**
     * 反射
     * 将<T : ViewBinding,V :BaseViewmodel> 泛型参数初始化
     */
    private fun reflex2Generics() {
        ystarBaseBinding = BaseFragmentBinding.inflate(layoutInflater)
        val type = javaClass.genericSuperclass as ParameterizedType
        //获取第一个参数泛型的的class ViewBinding
        val aClass = type.actualTypeArguments[0] as Class<VB>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
        mViewBinding = method.invoke(null, layoutInflater,ystarBaseBinding.flBaseContent,true) as VB
        //获取第二个参数泛型的的class Viewmodel
        val aClass1 = type.actualTypeArguments[1] as Class<VM>
        mViewmodel = ViewModelProvider(this).get(aClass1)
    }

}