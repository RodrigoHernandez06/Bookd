package com.example.bookd.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookd.viewmodel.BookdViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    viewModel: BookdViewModel,
    onAddBookClick: () -> Unit,
    onBookClick: (Long) -> Unit
) {
    val booksWithNotes by viewModel.booksWithNotes.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredBooks = remember(searchQuery, booksWithNotes) {
        if (searchQuery.isEmpty()) {
            booksWithNotes
        } else {
            booksWithNotes.filter {
                it.bookEntity.title.contains(searchQuery, ignoreCase = true) ||
                it.bookEntity.author.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Bookd - Biblioteca") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBookClick) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Libro")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar libro o autor") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredBooks) { bookWithNotes ->
                    val book = bookWithNotes.bookEntity
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onBookClick(book.id) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = book.title, style = MaterialTheme.typography.titleLarge)
                            Text(text = book.author, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
