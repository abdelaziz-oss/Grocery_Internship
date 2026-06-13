package com.example.groceryshop.authentication.data.repository

import com.example.groceryshop.authentication.data.models.LoginRequest
import com.example.groceryshop.authentication.data.models.LoginResponse
import com.example.groceryshop.authentication.data.models.SignUpRequest
import com.example.groceryshop.authentication.data.network.remote.AuthApiService
import com.example.groceryshop.authentication.domain.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApiService: AuthApiService) :
    AuthRepository {
    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = authApiService.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, username: String, password: String): Result<Unit> {
        return try {
            val response = authApiService.signUp(SignUpRequest(email, username, password))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Sign up failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
