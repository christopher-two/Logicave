package org.override.book.logicave.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.override.book.logicave.utils.Pages
import kotlin.math.abs

@Composable
internal fun BookPage(
    page: Pages,
    offsetX: Float,
    pageOffset: Int,
    isAnimating: Boolean,
    modifier: Modifier = Modifier
) {
    val rotation = (offsetX / 10f).coerceIn(-15f, 15f)
    // Efecto Tinder: páginas de fondo más pequeñas y con menos opacidad
    val scale = when (pageOffset) {
        0 -> 1f // Página actual
        1 -> 1f // Página siguiente (ligeramente más pequeña)
        -1 -> 1f // Página anterior
        else -> 1f - (abs(pageOffset) * 0.05f)
    }
    val alpha = when (pageOffset) {
        0 -> 1f // Página actual completamente visible
        1 -> 0.8f // Página siguiente semi-transparente
        -1 -> 0.8f // Página anterior semi-transparente
        else -> 0.6f - (abs(pageOffset) * 0.1f)
    }

    Card(
        modifier = modifier
            .graphicsLayer {
                translationX = offsetX
                // Páginas de fondo ligeramente desplazadas hacia atrás y abajo
                translationY = if (pageOffset != 0) abs(pageOffset) * 8f else 0f
                rotationZ = if (pageOffset == 0) rotation else 0f
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
            .shadow(
                elevation = when (pageOffset) {
                    0 -> 12.dp // Página actual con más elevación
                    1, -1 -> 6.dp // Páginas adyacentes con menos elevación
                    else -> 2.dp
                },
                ambientColor = Color.Black.copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = when (pageOffset) {
                0 -> 12.dp
                1, -1 -> 6.dp
                else -> 2.dp
            }
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(page.imageResource),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = "Página ${page.name}"
            )

            // Efecto de sombra en los bordes
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        androidx.compose.ui.graphics.Brush.horizontalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.1f)
                            )
                        )
                    )
            )
        }
    }
}
