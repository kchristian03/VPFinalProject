package com.uc.vpfinalproject.model.note

import com.google.gson.annotations.SerializedName

data class EditNoteResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data?,
) {
    data class Data(
        @SerializedName("ID") val ID: Int,
        @SerializedName("CreatedAt") val CreatedAt: String,
        @SerializedName("UpdatedAt") val UpdatedAt: String,
        @SerializedName("DeletedAt") val DeletedAt: String?,
        @SerializedName("Title") val Title: String,
        @SerializedName("Content") val Content: String,
    )
}
