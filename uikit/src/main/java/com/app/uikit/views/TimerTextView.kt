package com.app.uikit.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TimerTextView() {
    val remainingTime = remember { mutableStateOf(30) } // 30 seconds

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Delay for 1 second

            if (remainingTime.value > 0) {
                remainingTime.value -= 1
            } else {
                remainingTime.value = 30 // Reset remaining time to 30 seconds
            }
        }
    }

    val formattedTime = remember(remainingTime.value) {
        String.format(
            "%02d:%02d",
            remainingTime.value / 60,
            remainingTime.value % 60
        )
    }

    Text(
        text = formattedTime,
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
        color = Color(0xFF223142),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),

            )
    )
}