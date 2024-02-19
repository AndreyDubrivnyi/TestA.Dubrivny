package com.example.testadubrivny.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testadubrivny.db.NumberFact

@Dao
interface NumberFactDao {
    @Query("SELECT * FROM number_facts")
    fun getAllFacts(): LiveData<List<NumberFact>>

    @Insert
    suspend fun insertFact(numberFact: NumberFact)

    @Query("DELETE FROM number_facts")
    suspend fun deleteAll()

    @Query("SELECT * FROM number_facts WHERE id = :factId")
    fun getFactById(factId: Int): LiveData<NumberFact>

}