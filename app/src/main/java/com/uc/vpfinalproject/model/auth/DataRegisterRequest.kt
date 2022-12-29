package com.uc.vpfinalproject.model.auth

import com.google.gson.annotations.SerializedName

class DataRegisterRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("repeat") val repeat: String?,
    @SerializedName("streak") val streak: Int?
)