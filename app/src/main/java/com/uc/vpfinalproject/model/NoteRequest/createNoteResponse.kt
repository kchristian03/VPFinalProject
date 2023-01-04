package com.uc.vpfinalproject.model.NoteRequest

import com.google.gson.annotations.SerializedName
import retrofit2.http.Header

data class createNoteResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data?,
) {
    data class Data(
        @Header("Authorization") val token: String,
        @SerializedName("ID") val ID: Int,
        @SerializedName("CreatedAt") val CreatedAt: String,
        @SerializedName("UpdatedAt") val UpdatedAt: String,
        @SerializedName("DeletedAt") val DeletedAt: String?,
        @SerializedName("Title")     val Title: String,
        @SerializedName("Content")   val Content: String,
    )
}
