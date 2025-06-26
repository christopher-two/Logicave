package org.override.book.logicave.components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.override.book.logicave.utils.Pages
import org.override.book.logicave.utils.RoutesLogic

// Composable principal de navegación
@Composable
internal fun BookNavigation(navController: NavHostController) {
    val pages = Pages.entries.toTypedArray()

    NavHost(
        navController = navController,
        startDestination = RoutesLogic.PAG1.route,
        modifier = Modifier.fillMaxSize(),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        // Ruta principal del libro con navegación por swipe
        composable("book_view") {
            BookSwipeNavigation(navController, initialPageIndex = 0)
        }

        // Rutas individuales para cada página
        pages.forEachIndexed { index, page ->
            composable(page.routeActual) {
                BookPageContent(
                    page = page,
                    pageIndex = index,
                    navController = navController
                )
            }
        }
    }
}
