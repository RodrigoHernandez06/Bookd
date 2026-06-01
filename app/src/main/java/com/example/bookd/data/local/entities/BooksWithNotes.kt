package com.example.bookd.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BooksWithNotes(
    @Embedded
    val bookEntity: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val notes : List<BookNoteEntity>
)