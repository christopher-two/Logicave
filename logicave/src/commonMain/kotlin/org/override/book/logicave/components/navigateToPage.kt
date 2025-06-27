package org.override.book.logicave.components

import androidx.navigation.NavHostController
import org.override.book.logicave.utils.Pages

internal fun navigateToPage(navController: NavHostController, page: Pages) {
    navController.navigate(page.routeActual) {
        // Evitar múltiples instancias de la misma pantalla
        launchSingleTop = true
        // Restaurar estado si es necesario
        restoreState = true
    }
}

