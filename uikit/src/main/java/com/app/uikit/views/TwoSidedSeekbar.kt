package com.app.uikit.views

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomRangeSlider(
    modifier: Modifier,
    rangeColor: Color,
    backColor: Color,
    barHeight: Dp,
    circleRadius: Dp,
    cornerRadius: CornerRadius,
    progress1InitialValue: Float,
    progress2InitialValue: Float,
    onProgressChanged: (progress1: Float, progress2: Float) -> Unit
) {

    val circleRadiusInPx = with(LocalDensity.current) { circleRadius.toPx() }

    var progress1 by remember { mutableStateOf(progress1InitialValue) }
    var progress2 by remember { mutableStateOf(progress2InitialValue) }
    var width by remember { mutableStateOf(0f) }
    var height by remember { mutableStateOf(0f) }
    var leftCircleDragging by remember { mutableStateOf(false) }
    var rightCircleDragging by remember { mutableStateOf(false) }
    var leftCircleOffset by remember { mutableStateOf(Offset.Zero) }
    var rightCircleOffset by remember { mutableStateOf(Offset.Zero) }

    val scaleAnim1 by animateFloatAsState(
        targetValue = if (leftCircleDragging) 2f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val scaleAnim2 by animateFloatAsState(
        targetValue = if (rightCircleDragging) 2f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Canvas(
        modifier = modifier
            .height(barHeight)
            .pointerInteropFilter(
                onTouchEvent = { motionEvent ->

                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            val x = motionEvent.x
                            val y = motionEvent.y
                            val dis1 = sqrt(
                                (x - leftCircleOffset.x).pow(2) + (y - leftCircleOffset.y).pow(2)
                            )
                            val dis2 = sqrt(
                                (x - rightCircleOffset.x).pow(2) + (y - rightCircleOffset.y).pow(2)
                            )

                            if (dis1 < circleRadiusInPx) { // left circle clicked
                                leftCircleDragging = true
                            } else if (dis2 < circleRadiusInPx) { // right circle clicked
                                rightCircleDragging = true
                            }
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val x = motionEvent.x

                            if (leftCircleDragging) {
                                progress1 = if (x <= 0) {
                                    0f
                                } else if (x >= width * progress2) {
                                    progress2
                                } else {
                                    x / width
                                }
                                leftCircleOffset = leftCircleOffset.copy(x = width * progress1)
                            } else if (rightCircleDragging) {
                                progress2 = if (x >= width) {
                                    1f
                                } else if (x <= width * progress1) {
                                    progress1
                                } else {
                                    x / width
                                }
                                rightCircleOffset = rightCircleOffset.copy(x = width * progress2)
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            leftCircleDragging = false
                            rightCircleDragging = false
                            onProgressChanged(progress1, progress2)
                        }
                    }
                    true
                }
            )
            .onGloballyPositioned {
                leftCircleOffset = Offset(x = it.size.width * progress1, y = it.size.height / 2f)
                rightCircleOffset = Offset(x = it.size.width * progress2, y = it.size.height / 2f)
            }
    ) {
        width = this.size.width
        height = this.size.height

        drawRoundRect(
            color = backColor,
            cornerRadius = cornerRadius,
            topLeft = Offset(x = 0f, y = barHeight.toPx() / 4f),
            size = Size(width = width, height = barHeight.toPx() / 2f)
        )

        //draw inner rect (between two circles)
        drawRect(
            color = rangeColor,
            topLeft = Offset(x = width * progress1, y = 0f),
            size = Size(width = width * (progress2 - progress1), height = height)
        )

        //draw left circle
        scale(scaleAnim1, pivot = leftCircleOffset) {
            drawCircle(
                color = rangeColor.copy(alpha = 0.2f),
                radius = circleRadius.toPx(),
                center = leftCircleOffset
            )
        }
        drawCircle(
            color = rangeColor,
            radius = circleRadius.toPx(),
            center = leftCircleOffset
        )

        //draw right circle
        scale(scaleAnim2, pivot = rightCircleOffset) {
            drawCircle(
                color = rangeColor.copy(alpha = 0.2f),
                radius = circleRadius.toPx(),
                center = rightCircleOffset
            )
        }
        drawCircle(
            color = rangeColor,
            radius = circleRadius.toPx(),
            center = rightCircleOffset,
        )
    }
}