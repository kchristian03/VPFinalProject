package com.uc.vpfinalproject.model

data class DataLoginResponse(
    val expires_at: String,
    val id: Int,
    val name: String,
    val token: String,
    val user_id: Int
)