package com.uc.vpfinalproject.repository

import com.uc.vpfinalproject.model.auth.*
import com.uc.vpfinalproject.retrovit.RestApi
import retrofit2.Response

class UserRepository {
    suspend fun pingServer(): Response<DataPingResponse> {
        return RestApi.getApi()?.pingServer()!!
    }

    suspend fun loginUser(loginRequest: DataLoginRequest): Response<DataLoginResponse>? {
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