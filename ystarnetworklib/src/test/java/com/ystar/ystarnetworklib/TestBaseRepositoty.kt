package com.ystar.ystarnetworklib

import androidx.appcompat.app.AppCompatActivity
import com.ystar.lib_network.kotlinapi.BaseRepository
import com.ystar.lib_network.kotlinapi.createApi
import com.ystar.ystarnetworklib.data.BaseResponse

/**
 * @Author ystar
 * @Date 2022/1/13 11:31
 * @discriable 示例
 *
 */

class TestBaseRepository : BaseRepository<TestApiservice>(createApi()) {
    //单例
    companion object {
        val instance: TestBaseRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TestBaseRepository()
        }
    }


/*

  例子：
  仓库 请求
      具体请求
     手写类rxjava链表*/

/*
    suspend inline  fun schoolCampus1 (appCompatActivity: AppCompatActivity, crossinline  block: BaseResponse<MutableList<String>>.()->Unit){
        create(appCompatActivity,"获取数据，稍等"){
         //   mApiService.schoolCampus()
        }.sub {
            block(this)
        }
    }
*/


/*
  例子：
    具体在viewmodel 里面调用

        fun schoolCampus(appCompatActivity: AppCompatActivity) {
        viewModelScope.launch { //协程
            TestBaseRepository.instance.schoolCampus1(appCompatActivity){
                if (issuc()){
                    schoolBean.value = data
                }else{
                    KerrorData.value = ErrorData(code,msg)
                }
            }


    }*/

}