package com.example.testadubrivny.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_facts")
data class NumberFact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "fact") val fact: String
)