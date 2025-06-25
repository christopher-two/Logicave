package org.override.book.logicave

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.DrawableResource
import org.override.book.logicave.components.Navigation

@Composable
fun LogicaveRoot(
    images: List<DrawableResource>
) {
    val viewModel = LogicaveViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LogicaveScreen(
        state = state,
        images = images,
        onAction = viewModel::onAction
    )
}

@Composable
internal fun LogicaveScreen(
    state: LogicaveState,
    images: List<DrawableResource>,
    onAction: (LogicaveAction) -> Unit,
) {
    val navController = rememberNavController()
    Navigation(
        navController = navController,
        images = images
    )
}