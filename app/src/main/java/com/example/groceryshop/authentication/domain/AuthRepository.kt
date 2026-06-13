package com.example.groceryshop.authentication.domain

import com.example.groceryshop.authentication.data.models.LoginResponse


interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse>

    suspend fun signUp(
        email: String,
        username: String,
        password: String
    ): Result<Unit>
}