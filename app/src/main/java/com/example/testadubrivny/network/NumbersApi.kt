package com.example.testadubrivny.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object NumbersApi {
    val retrofitService: NumbersApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersApiService::class.java)
    }
}