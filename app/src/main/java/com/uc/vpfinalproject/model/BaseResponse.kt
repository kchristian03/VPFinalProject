package com.uc.vpfinalproject.model

sealed class BaseResponse<out T>{
    data class Success<out T>(val data: T? = null) : BaseResponse<T>()
    data class Error(val msg: String?) : BaseResponse<Nothing>()
    data class Loading(val nothing: Nothing?=null) : BaseResponse<Nothing>()
}
