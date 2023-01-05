package com.uc.vpfinalproject.model.note

import com.google.gson.annotations.SerializedName

data class GetNoteResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<Data>,
)

