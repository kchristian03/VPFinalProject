package com.uc.vpfinalproject.model.note

import com.google.gson.annotations.SerializedName


data class CreateNoteRequest(
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("user_id") val user_id: Int?
)
