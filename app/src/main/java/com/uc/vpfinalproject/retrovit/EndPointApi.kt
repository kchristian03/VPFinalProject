package com.uc.vpfinalproject.retrovit

import com.uc.vpfinalproject.model.ApiResponse
import com.uc.vpfinalproject.model.DataLoginResponse
import com.uc.vpfinalproject.model.DataRegisterResponse
import com.uc.vpfinalproject.model.DataUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPointApi {

    @POST("auth/login/")
    suspend fun login(
    ): Response<ApiResponse<DataLoginResponse>>

    @POST("auth/register/")
    suspend fun register(
    ): Response<ApiResponse<DataRegisterResponse>>

    @GET("user")
    suspend fun getUser(
    ): Response<ApiResponse<DataUserResponse>>
}