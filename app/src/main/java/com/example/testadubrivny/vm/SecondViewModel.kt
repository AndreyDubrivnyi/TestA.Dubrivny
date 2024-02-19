package com.example.testadubrivny.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.testadubrivny.db.AppDatabase
import com.example.testadubrivny.db.NumberFact

class SecondViewModel(application: Application) : AndroidViewModel(application) {
    private val numberFactDao = AppDatabase.getDatabase(application).numberFactDao()

    fun getFactById(factId: Int): LiveData<NumberFact> {
        return numberFactDao.getFactById(factId)
    }
}