package com.uc.vpfinalproject.model.note

import com.google.gson.annotations.SerializedName

data class EditNoteRequest(
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
)
