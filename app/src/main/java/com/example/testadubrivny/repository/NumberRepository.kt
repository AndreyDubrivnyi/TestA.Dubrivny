package com.example.testadubrivny.repository

import androidx.lifecycle.LiveData
import com.example.testadubrivny.db.NumberFact
import com.example.testadubrivny.db.NumberFactDao
import com.example.testadubrivny.network.NumbersApi

class NumberRepository(private val numberFactDao: NumberFactDao) {
    val allFacts: LiveData<List<NumberFact>> = numberFactDao.getAllFacts()

    suspend fun insert(numberFact: NumberFact) {
        numberFactDao.insertFact(numberFact)
    }

    suspend fun getFactForNumber(number: String): String {
        return NumbersApi.retrofitService.getFactForNumber(number)
    }

    suspend fun getRandomFact(): String {
        return NumbersApi.retrofitService.getRandomFact()
    }
}