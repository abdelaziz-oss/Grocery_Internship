package com.example.groceryshop.authentication.service

import com.example.groceryshop.authentication.login_screen.models.LoginRequest
import com.example.groceryshop.authentication.login_screen.models.LoginResponse
import com.example.groceryshop.authentication.signup_screen.models.SignUpRequest
import com.example.groceryshop.authentication.signup_screen.models.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("user/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<Unit>
}