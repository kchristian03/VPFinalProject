package com.uc.vpfinalproject.model

import com.google.gson.annotations.SerializedName

data class DataRegisterResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: DataRegisterResponse.Data?,
) {
    data class Data(
        @SerializedName("id") val id: Int,
        @SerializedName("user_id") val user_id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("token") val token: String,
        @SerializedName("expires_at") val expires_at: String,
    )
}