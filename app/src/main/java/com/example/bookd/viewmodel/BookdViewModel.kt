package com.example.bookd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bookd.data.local.BookDao
import com.example.bookd.data.local.entities.BookEntity
import com.example.bookd.data.local.entities.BookNoteEntity
import com.example.bookd.data.local.entities.BooksWithNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookdViewModel (private val dao : BookDao) : ViewModel() {

    val booksWithNotes: StateFlow<List<BooksWithNotes>> = dao.getBooksWithNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getBookWithNotes(bookId: Long): Flow<BooksWithNotes?> {
        return dao.getBookWithNotesById(bookId)
    }

    fun addBook(title : String, author : String, synopsis : String){
        viewModelScope.launch {
            dao.InsertBook(BookEntity(title = title, author = author, synopsis = synopsis))
        }
    }

    fun addNote(content : String, bookId : Long){
        viewModelScope.launch {
            val date = System.currentTimeMillis()
            dao.InsertNote(BookNoteEntity(bookId = bookId, content = content, dateTimestamp = date))
        }
    }

    fun deleteNote(note : BookNoteEntity){
        viewModelScope.launch {
            dao.deleteNote(note)
        }
    }
}

class BookdViewModelFactory(private val dao: BookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookdViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookdViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
