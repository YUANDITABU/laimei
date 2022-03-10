package com.ystar.ystarnetworklib

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonSyntaxException
import com.ystar.ystarnetworklib.data.BaseResponse
import com.ystar.ystarnetworklib.utils.ProgressDialgUtil
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author ystar
 * @Date 2022/2/8 9:39
 * @discriable
 * 用类rxjava联表实现网络数据转化处理 最终输出为BaseResponse<T>
 *
 */


//######################数据转化处理############################

/**
 * 中间链辅助类
 * @param T
 * @property item Deferred<BaseResponse<T>>
 * @constructor
 */
class Helper<T>(val item: Deferred<BaseResponse<T>>) {

    //转为BaseResponse<T> 的扩展 并实现
    suspend fun sub(action: BaseResponse<T>.() -> Unit) {
        try {
            action(item.await())
        } catch (ex: Exception) {
            action(ex.ex2ErrorBaseResponse())
        } finally {
            //如果有弹窗就取消
            ProgressDialgUtil.getInstance().dismiss()
        }

    }
}

//不带弹窗请求
fun <R> createRequest(action: () -> Deferred<BaseResponse<R>>) = Helper(action())

//带弹窗请求
fun <R> createRequest(appCompatActivity: AppCompatActivity, msg: String, action: () -> Deferred<BaseResponse<R>>): Helper<R> {
    ProgressDialgUtil.getInstance().create(appCompatActivity, msg).show()
    return Helper(action())
}






//######################异常处理############################
/**
 * 将Exception 转化为baseResonse
 * @receiver Exception
 * @return BaseResponse<T>
 */
fun <T> Exception.ex2ErrorBaseResponse(): BaseResponse<T> {
    Log.i("txt", "${this.toString()}")

    var msg = ErrorType.OtherError.msg
    var code = ErrorType.OtherError.code
    when (this) {
        is UnknownHostException -> {
            msg = ErrorType.UnknownHost.msg
            code = ErrorType.UnknownHost.code;

        }
        is SocketTimeoutException -> {
            msg = ErrorType.SocketTimeout.msg
            code = ErrorType.SocketTimeout.code;


        }
        is NetworkErrorException -> {
            msg = ErrorType.NetworkError.msg
            code = ErrorType.NetworkError.code;

        }
        is ConnectException -> {
            msg = ErrorType.ConnectError.msg
            code = ErrorType.ConnectError.code;


        }
        is HttpException -> {
            msg = ErrorType.HttpError.msg
            code = ErrorType.HttpError.code;


        }
        is JsonSyntaxException -> {
            msg = ErrorType.JsonError.msg
            code = ErrorType.JsonError.code;


        }
    }
    return BaseResponse(null,code,msg)
}



/**
 * 异常枚举 便于 修改 查看
 * @property code Int
 * @property msg String
 * @constructor
 */
private enum class ErrorType(val  code: Int, val msg: String) {
    OtherError(1000, "OtherError"),
    UnknownHost(1001, "UnknownHostException"),
    SocketTimeout(1002, "SocketTimeoutException"),
    NetworkError(1003, "NetworkErrorException"),
    ConnectError(1004, "未连接到服务器"),
    JsonError(1005, "数据解析错误"),
    HttpError(1006, "HTTP 404 Not Found")
}