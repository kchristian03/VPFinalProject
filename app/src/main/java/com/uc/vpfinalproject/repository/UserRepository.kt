package com.uc.vpfinalproject.repository

import com.uc.vpfinalproject.model.*
import com.uc.vpfinalproject.retrovit.RestApi
import com.uc.vpfinalproject.retrovit.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class UserRepository {
    suspend fun loginUser(loginRequest:DataLoginRequest): Response<DataLoginResponse>? {
        return  RestApi.getApi()?.login(loginRequest = loginRequest)
    }

    suspend fun registerUser(registerRequest: DataRegisterRequest): Response<DataRegisterResponse>? {
        return  RestApi.getApi()?.register(registerRequest = registerRequest)
    }

    suspend fun getUser(token: String): Response<DataUserResponse>? {
        return  RestApi.getApi()?.getUser(token = token)
    }

    suspend fun logoutUser(token: String): Response<DataLogoutResponse>? {
        return  RestApi.getApi()?.logoutUser(token = token)
    }
}