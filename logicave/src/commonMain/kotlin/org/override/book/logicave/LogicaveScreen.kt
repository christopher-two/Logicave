package org.override.book.logicave

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.override.book.logicave.components.BookNavigation

@Composable
fun LogicaveRoot() {
    val viewModel = LogicaveViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LogicaveScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
internal fun LogicaveScreen(
    state: LogicaveState,
    onAction: (LogicaveAction) -> Unit,
) {
    val navController = rememberNavController()
    BookNavigation(navController)
}