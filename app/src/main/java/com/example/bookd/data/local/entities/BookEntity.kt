package com.example.bookd.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val title : String,
    val author : String,
    val synopsis : String
)