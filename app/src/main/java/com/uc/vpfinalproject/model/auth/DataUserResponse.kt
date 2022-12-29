package com.uc.vpfinalproject.model.auth

import com.google.gson.annotations.SerializedName

data class DataUserResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data?,
) {
    data class Data(
        @SerializedName("ID") val ID: Int,
        @SerializedName("CreatedAt") val CreatedAt: String,
        @SerializedName("UpdatedAt") val UpdatedAt: String,
        @SerializedName("DeletedAt") val DeletedAt: String?,
        @SerializedName("PersonalAccessToken") val PersonalAccessToken: String?,
        @SerializedName("Name") val Name: String,
        @SerializedName("Email") val Email: String,
        @SerializedName("Username") val Username: String,
    )
}