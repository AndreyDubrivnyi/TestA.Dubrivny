package com.example.testadubrivny.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testadubrivny.db.AppDatabase
import com.example.testadubrivny.db.NumberFact
import com.example.testadubrivny.repository.NumberRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val numberFactDao = AppDatabase.getDatabase(application).numberFactDao()
    private val repository = NumberRepository(numberFactDao)
    val allFacts: LiveData<List<NumberFact>> = repository.allFacts

    fun getFactForNumber(number: String) {
        viewModelScope.launch {
            try {
                val response = repository.getFactForNumber(number)
                insertFactInDatabase(response)
            } catch (e: Exception) {

            }
        }
    }

    private fun insertFactInDatabase(fact: String) {
        viewModelScope.launch {
            val numberFact = NumberFact(fact = fact)
            repository.insert(numberFact)
        }
    }

    fun getRandomFact() {
        viewModelScope.launch {
            try {
                val response = repository.getRandomFact()
                insertFactInDatabase(response)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching random fact", e)
            }
        }
    }
}