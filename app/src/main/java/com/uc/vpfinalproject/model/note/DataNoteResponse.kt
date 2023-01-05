package com.uc.vpfinalproject.model.note

data class DataNoteResponse(
    val data: ArrayList<Data>,
    val message: String,
    val success: Boolean
)