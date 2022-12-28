package com.uc.vpfinalproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uc.vpfinalproject.model.*
import com.uc.vpfinalproject.repository.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject


class AuthViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<DataLoginResponse>> = MutableLiveData()
    val registerResult: MutableLiveData<BaseResponse<DataRegisterResponse>> = MutableLiveData()
    val logoutResult: MutableLiveData<BaseResponse<DataLogoutResponse>> = MutableLiveData()
    val getDataResult: MutableLiveData<BaseResponse<DataUserResponse>> = MutableLiveData()

    fun loginUser(username: String, password: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = DataLoginRequest(
                    username = username,
                    password = password,
                    remember = true
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    loginResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    loginResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun registerUser(name: String, email: String, username: String, password: String) {

        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val registerRequest = DataRegisterRequest(
                    name = name,
                    email = email,
                    username = username,
                    password = password,
                    repeat = password,
                    streak = 0
                )
                val response = userRepo.registerUser(registerRequest = registerRequest)
                if (response?.code() == 200) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    registerResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    registerResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun logoutUser(token: String) {

        logoutResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val response = userRepo.logoutUser(token)
                if (response?.code() == 200) {
                    logoutResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    logoutResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    logoutResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                logoutResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun getDataUser(token: String) {
        getDataResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val response = userRepo.getUser(token)
                if (response?.code() == 200) {
                    getDataResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    getDataResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    getDataResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                getDataResult.value = BaseResponse.Error(ex.message)
            }
        }
    }


}