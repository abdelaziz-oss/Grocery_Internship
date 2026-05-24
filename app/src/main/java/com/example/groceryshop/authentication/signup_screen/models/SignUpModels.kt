package com.example.groceryshop.authentication.signup_screen.models

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String
)

data class SignUpResponse(val status: String, val token: String)