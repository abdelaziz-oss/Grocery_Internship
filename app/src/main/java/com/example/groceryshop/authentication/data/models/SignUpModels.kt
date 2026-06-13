package com.example.groceryshop.authentication.data.models

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String
)

data class SignUpResponse(val status: String, val token: String)