package com.app.uikit.borders

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CurvedBottomBox(
    modifier: Modifier = Modifier,
    curveHeight: Dp = 50.dp,
    color: Color
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(curveHeight + 100.dp) // Adjust as needed
    ) {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, curveHeight.toPx())
            quadraticBezierTo(
                size.width / 2f, curveHeight.toPx() + 100f,
                size.width, curveHeight.toPx()
            )
            lineTo(size.width, 0f)
            close()
        }

        // Apply transformation to rotate the canvas upside down
        rotate(degrees = 180f, pivot = Offset(size.width / 2f, size.height / 2f)){

        }

        drawPath(
            path = path,
            color = color
        )
    }
}