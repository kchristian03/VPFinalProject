package com.uc.vpfinalproject.model

data class DataRegisterResponse(
    val expires_at: String,
    val name: String,
    val token: String,
    val username: String
)