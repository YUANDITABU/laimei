package com.ystar.lib_network.kotlinapi

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 *
 * @Author ystar
 * @Date 2021/11/23 11:40
 * 网络请求 构造 还是采用retrofit+okhttp
 *  BaseRepositoty 一个仓库的基类
 *  DefultInterceptor  提供一个默认的拦截器(感觉应该够用，一般都是一个), 页可以根据具体业务添加或者新增  :Interceptor
 */

//top-level

//默认超市时间 30秒
var defulttime: Long = 30 * 1000
///hell0/hello
const val BASE_URL: String = "http://192.168.2.19:8082/dev/"


/**
 * @Author ystar
 * @Date 2021/11/23 11:40
 * @discriable open  可继承
 * 具体实现可参照TestBaseRepository
 */
open class BaseRepository<T>(val mApiService: T)


/**
 * inline 内联
 * reified 具体的class 类型
 */

inline fun <reified Apiserivice> createApi(): Apiserivice {
    return createApi(defulttime, DefultInterceptor())
}



inline fun <reified Apiserivice> createApi(time: Long, interceptor: Interceptor): Apiserivice {
    var builder: OkHttpClient.Builder = OkHttpClient.Builder()
    builder.connectTimeout(time, java.util.concurrent.TimeUnit.MILLISECONDS)
    builder.readTimeout(time, java.util.concurrent.TimeUnit.MILLISECONDS)
    builder.writeTimeout(time, java.util.concurrent.TimeUnit.MILLISECONDS)
    var httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    //DEBUG设置参数查看拦截器
    builder.addInterceptor(httpLoggingInterceptor)
    builder.addInterceptor(interceptor)
    //如果需要设置拦截器就加上
    //如果需要设置拦截器就加上
    return createRetrofit(BASE_URL, builder).create(Apiserivice::class.java)
}

fun createRetrofit(baseurl: String, builder: OkHttpClient.Builder): Retrofit {
    return Retrofit.Builder()
        .client(builder.build())
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

}


/**
 * 默认拦截器
 * @Author ystar
 * @Date 2021/10/29 17:02
 * @discriable
 */
class DefultInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        original.headers()
        //添加公共参数

/*     if (RoombdManner.getInstance().isLogin()){
            KLog.a("token:  "+"Teacher " + RoombdManner.getInstance().getToken());
            builder.addHeader("Authorization", "Teacher " + RoombdManner.getInstance().getToken());
        }

        builder.addHeader("version", AppUtils.getAppVersionName());*/
        builder.addHeader("platform", "1")
        builder.method(original.method(), original.body())
        Log.i("hahahaha", "token:  " + "Teacher ")
        return chain.proceed(builder.build())
    }
}








