package com.example.groceryshop.authentication.data.network.remote

import com.example.groceryshop.authentication.data.models.LoginRequest
import com.example.groceryshop.authentication.data.models.LoginResponse
import com.example.groceryshop.authentication.data.models.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("user/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<Unit>
}