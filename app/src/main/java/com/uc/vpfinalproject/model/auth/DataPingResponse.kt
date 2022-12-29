package com.uc.vpfinalproject.model.auth

import com.google.gson.annotations.SerializedName

class DataPingResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Nothing? = null,
)