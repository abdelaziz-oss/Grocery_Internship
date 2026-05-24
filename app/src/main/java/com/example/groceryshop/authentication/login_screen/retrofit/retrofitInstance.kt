package com.example.groceryshop.authentication.login_screen.retrofit

import com.example.groceryshop.authentication.service.AuthApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://100.76.196.122:9000/"
    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    val AuthApi: AuthApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AuthApiService::class.java)
    }
}