package com.example.groceryshop.authentication.login_screen.models

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val status: String,
    val token: String
)