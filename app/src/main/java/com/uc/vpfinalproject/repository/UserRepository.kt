package com.uc.vpfinalproject.repository

import com.uc.vpfinalproject.retrovit.EndPointApi
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getUserDetail(
    ) = api.getUser()

    suspend fun login(
    ) = api.login()

    suspend fun register(
    ) = api.register()
}