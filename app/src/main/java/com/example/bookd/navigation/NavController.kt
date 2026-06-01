package com.example.bookd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookd.ui.screens.AddBookScreen
import com.example.bookd.ui.screens.BookDetailScreen
import com.example.bookd.ui.screens.BookListScreen
import com.example.bookd.viewmodel.BookdViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: BookdViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "book_list"
    ) {
        composable("book_list") {
            BookListScreen(
                viewModel = viewModel,
                onAddBookClick = { navController.navigate("add_book") },
                onBookClick = { bookId ->
                    navController.navigate("book_detail/$bookId")
                }
            )
        }
        composable("add_book") {
            AddBookScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "book_detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId") ?: return@composable
            BookDetailScreen(
                bookId = bookId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
