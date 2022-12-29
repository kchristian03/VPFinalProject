package com.uc.vpfinalproject.retrovit

import com.uc.vpfinalproject.helper.Const
import com.uc.vpfinalproject.model.*
import com.uc.vpfinalproject.model.auth.*
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    @GET(Const.PING_URL)
    suspend fun pingServer(): Response<DataPingResponse>

    @Headers("Content-Type: application/json")
    @POST(Const.LOGIN_URL)
    suspend fun login(@Body loginRequest: DataLoginRequest): Response<DataLoginResponse>

    @Headers("Content-Type: application/json")
    @POST(Const.REGISTER_URL)
    suspend fun register(@Body registerRequest: DataRegisterRequest): Response<DataRegisterResponse>

    @DELETE(Const.LOGOUT_URL)
    suspend fun logoutUser(@Header("Authorization") token: String): Response<DataLogoutResponse>

    @GET(Const.USER_URL)
    suspend fun getUser(@Header("Authorization") token: String): Response<DataUserResponse>

    companion object {
        fun getApi(): RestApi? {
            return ServiceBuilder.buildService?.create(RestApi::class.java)
        }
    }
}