package com.example.bookd.data.local

import androidx.room.*
import com.example.bookd.data.local.entities.BookEntity
import com.example.bookd.data.local.entities.BooksWithNotes
import com.example.bookd.data.local.entities.BookNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertBook(book : BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertNote(notes : BooksWithNotes)

    @Query("SELECT * FROM books")
    fun getAllDishes() : Flow<List<BookEntity>>

    @Transaction
    @Query("SELECT * FROM books")
    fun getBooksWithNotes() : Flow<List<BooksWithNotes>>

    @Query("SELECT * FROM book_notes WHERE bookId = :bookId")
    fun getNotesByBook(bookId: Int): Flow<List<BookNoteEntity>>
}

