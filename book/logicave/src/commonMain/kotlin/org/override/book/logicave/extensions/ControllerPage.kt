package org.override.book.logicave.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.controllerPage(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    sideZoneWidthDp: Int = 80
): Modifier {
    val density = LocalDensity.current
    val sideZoneWidthPx = with(density) { sideZoneWidthDp.dp.toPx() }

    return this.then(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val screenWidth = size.width.toFloat()

                    when {
                        // Zona izquierda
                        offset.x <= sideZoneWidthPx -> {
                            onLeftClick()
                        }
                        // Zona derecha
                        offset.x >= screenWidth - sideZoneWidthPx -> {
                            onRightClick()
                        }
                        // Zona central - no hacer nada
                        else -> {
                            // Puedes agregar lógica para el centro si es necesario
                        }
                    }
                }
            }
    )
}
