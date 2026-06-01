package com.example.bookd.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookd.viewmodel.BookdViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(
    viewModel: BookdViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var synopsis by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf(false) }
    var authorError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Añadir Nuevo Libro") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { 
                    title = it
                    titleError = it.isBlank()
                },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth(),
                isError = titleError,
                supportingText = { if (titleError) Text("El título es obligatorio") }
            )

            OutlinedTextField(
                value = author,
                onValueChange = { 
                    author = it
                    authorError = it.isBlank()
                },
                label = { Text("Autor") },
                modifier = Modifier.fillMaxWidth(),
                isError = authorError,
                supportingText = { if (authorError) Text("El autor es obligatorio") }
            )

            OutlinedTextField(
                value = synopsis,
                onValueChange = { synopsis = it },
                label = { Text("Sinopsis") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Button(
                onClick = {
                    if (title.isNotBlank() && author.isNotBlank()) {
                        viewModel.addBook(title, author, synopsis)
                        onBack()
                    } else {
                        titleError = title.isBlank()
                        authorError = author.isBlank()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Libro")
            }
        }
    }
}
