package com.example.bookd.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "book_notes",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["bookId"])]
    )
data class BookNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId : Long = 0,
    val bookId : Long = 0,
    val content : String,
    val dateTimestamp : Long
)