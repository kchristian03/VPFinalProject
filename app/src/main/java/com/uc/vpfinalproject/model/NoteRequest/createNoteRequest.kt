package com.uc.vpfinalproject.model.NoteRequest

import com.google.gson.annotations.SerializedName
import retrofit2.http.Header


data class createNoteRequest(
    @Header("Authorization") val token: String,
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("user_id") val user_id: Int?
)
