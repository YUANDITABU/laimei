package com.ystar.ystarnetworklib.data

/**
 * @Author ystar
 * @Date 2021/10/29 16:13
 * @discriable
 */


fun  BaseResponse<*>.issuc():Boolean?=code==200


 data  class BaseResponse<Respond>(var data: Respond?, var code:Int, var msg: String){

}



