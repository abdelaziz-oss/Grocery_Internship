package com.example.groceryshop.authentication.login_screen.repository

import com.example.groceryshop.authentication.login_screen.models.LoginRequest
import com.example.groceryshop.authentication.login_screen.models.LoginResponse
import com.example.groceryshop.authentication.login_screen.retrofit.RetrofitInstance
import com.example.groceryshop.authentication.signup_screen.models.SignUpRequest
import com.example.groceryshop.authentication.signup_screen.models.SignUpResponse

class AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response =
                RetrofitInstance.AuthApi.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun SignUp(email: String, username: String, password: String): Result<Unit> {
        return try {
            val response = RetrofitInstance.AuthApi.signUp(SignUpRequest(email, username, password))
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
