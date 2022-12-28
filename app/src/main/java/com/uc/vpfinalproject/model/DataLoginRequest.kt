package com.uc.vpfinalproject.model

import com.google.gson.annotations.SerializedName

data class DataLoginRequest(
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("remember") val remember: Boolean?
) {}