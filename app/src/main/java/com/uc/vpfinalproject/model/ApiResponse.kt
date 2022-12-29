package com.uc.vpfinalproject.model

data class ApiResponse<Data>(
    val status: Boolean,
    val message: String,
    val data: Data)
