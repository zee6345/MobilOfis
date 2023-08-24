package com.app.uikit.borders

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }

    this.then(Modifier.drawWithCache {
        onDrawBehind {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val strokeWidthWithDensity = strokeWidthPx / density.density
            val start = Offset(0f, size.height)
            val end = Offset(size.width, size.height)
            drawLine(
                color = color,
                start = start,
                end = end,
                strokeWidth = strokeWidthWithDensity,
                pathEffect = pathEffect
            )
        }
    })
})

fun Modifier.rightVerticalDashedBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    this.then(Modifier.drawWithCache {
        onDrawBehind {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val strokeWidthWithDensity = strokeWidthPx / density.density
            val start = Offset(size.width, 0f)
            val end = Offset(size.width, size.height)

            // Draw the dashed line
            drawLine(
                color = color,
                start = start,
                end = end,
                strokeWidth = strokeWidthWithDensity,
                pathEffect = pathEffect
            )
        }
    })
})