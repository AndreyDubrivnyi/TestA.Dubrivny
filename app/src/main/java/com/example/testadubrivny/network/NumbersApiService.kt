package com.example.testadubrivny.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    @GET("{number}")
    suspend fun getFactForNumber(@Path("number") number: String): String

    @GET("random/math")
    suspend fun getRandomFact(): String
}
