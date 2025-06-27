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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import org.override.book.logicave.extensions.controllerPage
import org.override.book.logicave.utils.Pages
import kotlin.math.abs
import kotlin.math.sign

// Composable para el contenido específico de cada página
@Composable
internal fun BookPageContent(
    page: Pages,
    pageIndex: Int,
    navController: NavHostController
) {
    val pages = Pages.entries.toTypedArray()
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }

    val swipeThreshold = 120f

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
                    if (pageIndex > 0 && !isAnimating) {
                        navigateToPage(navController, pages[pageIndex - 1])
                    }
                },
                onRightClick = {
                    if (pageIndex < pages.size - 1 && !isAnimating) {
                        navigateToPage(navController, pages[pageIndex + 1])
                    }
                },
                sideZoneWidthDp = 50
            )
            .pointerInput(pageIndex) {
                detectDragGestures(
                    onDragStart = { isAnimating = false },
                    onDragEnd = {
                        val shouldSwipe = abs(offsetX) > swipeThreshold
                        if (shouldSwipe) {
                            val direction = -offsetX.sign.toInt()
                            val newIndex = pageIndex + direction

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
    ) {
        // Renderizar páginas con efecto Tinder stack
        // Página siguiente (si existe) - en el fondo
        if (pageIndex < pages.size - 1) {
            BookPage(
                page = pages[pageIndex + 1],
                offsetX = 0f,
                pageOffset = 1,
                isAnimating = false,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            )
        }

        // Página anterior (si existe y se está arrastrando hacia la derecha)
        if (pageIndex > 0 && offsetX > 0) {
            BookPage(
                page = pages[pageIndex - 1],
                offsetX = 0f,
                pageOffset = -1,
                isAnimating = false,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
            )
        }

        // Página actual - siempre en el frente
        BookPage(
            page = page,
            offsetX = animatedOffsetX,
            pageOffset = 0,
            isAnimating = isAnimating,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(10f)
        )
    }
}