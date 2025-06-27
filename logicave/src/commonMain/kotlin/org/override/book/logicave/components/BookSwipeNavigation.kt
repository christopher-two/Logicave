package org.override.book.logicave.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.override.book.logicave.extensions.controllerPage
import org.override.book.logicave.utils.Pages
import kotlin.math.abs
import kotlin.math.sign

@Composable
internal fun BookSwipeNavigation(
    navController: NavHostController,
    initialPageIndex: Int = 0
) {
    val pages = Pages.entries.toTypedArray()
    var currentPageIndex by remember(initialPageIndex) { mutableIntStateOf(initialPageIndex) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }

    val swipeThreshold = 120f

    // Sincronizar el índice de página con la navegación actual
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry?.destination?.route) {
        val currentRoute = currentBackStackEntry?.destination?.route
        val pageIndex = pages.indexOfFirst { it.routeActual == currentRoute }
        if (pageIndex != -1 && pageIndex != currentPageIndex) {
            currentPageIndex = pageIndex
        }
    }

    // Animación para el offset
    val animatedOffsetX by animateFloatAsState(
        targetValue = if (isAnimating) 0f else offsetX,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        finishedListener = { isAnimating = false }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C1810))
            .controllerPage(
                onLeftClick = {
                    if (currentPageIndex > 0 && !isAnimating) {
                        navigateToPage(navController, pages[currentPageIndex - 1])
                    }
                },
                onRightClick = {
                    if (currentPageIndex < pages.size - 1 && !isAnimating) {
                        navigateToPage(navController, pages[currentPageIndex + 1])
                    }
                },
                sideZoneWidthDp = 50
            )
    ) {
        // Renderizar hasta 3 páginas para el efecto de stack
        for (i in maxOf(0, currentPageIndex - 1)..minOf(pages.size - 1, currentPageIndex + 1)) {
            val page = pages[i]
            val isCurrentPage = i == currentPageIndex
            val pageOffset = i - currentPageIndex

            BookPage(
                page = page,
                offsetX = if (isCurrentPage) animatedOffsetX else 0f,
                pageOffset = pageOffset,
                isAnimating = isAnimating,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(if (isCurrentPage) 10f else (5f - abs(pageOffset)))
                    .then(
                        if (isCurrentPage) {
                            Modifier.pointerInput(currentPageIndex) {
                                detectDragGestures(
                                    onDragStart = { isAnimating = false },
                                    onDragEnd = {
                                        val shouldSwipe = abs(offsetX) > swipeThreshold
                                        if (shouldSwipe) {
                                            val direction = -offsetX.sign.toInt()
                                            val newIndex = currentPageIndex + direction

                                            if (newIndex in 0 until pages.size) {
                                                navigateToPage(navController, pages[newIndex])
                                            }
                                        }
                                        isAnimating = true
                                        offsetX = 0f
                                    }
                                ) { _, dragAmount ->
                                    if (!isAnimating) {
                                        offsetX += dragAmount.x
                                        offsetX = offsetX.coerceIn(-400f, 400f)
                                    }
                                }
                            }
                        } else Modifier
                    )
            )
        }

        PageIndicator(
            totalPages = pages.size,
            currentPage = currentPageIndex,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}