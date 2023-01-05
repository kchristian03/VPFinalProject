package com.uc.vpfinalproject.model.note

data class DataNote(
    val ID: Int,
    val CreatedAt: String,
    val UpdatedAt: String,
    val DeletedAt: String?,
    val Title: String,
    val Content: String,
)
