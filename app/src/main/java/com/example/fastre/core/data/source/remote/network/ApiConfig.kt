package com.example.fastre.core.data.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
<<<<<<< HEAD
    val instance: ApiService by lazy {
=======
    fun provideApiService(): ApiService {
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.101.251.230:8000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
<<<<<<< HEAD

        retrofit.create(ApiService::class.java)
=======
        return retrofit.create(ApiService::class.java)
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}