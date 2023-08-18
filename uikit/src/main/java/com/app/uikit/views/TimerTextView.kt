package com.app.uikit.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerTextView(value: String) {
    val remainingTime = remember { mutableStateOf(300) } // 30 seconds

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(value) {
        val timerJob = coroutineScope.launch {
            while (remainingTime.value > 0) {
                delay(1000) // Delay for 1 second
                if (value.isNotEmpty()) {
                    remainingTime.value -= 1
                }
            }
        }

        onDispose {
            timerJob.cancel()
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
            fontWeight = FontWeight(500)
        )
    )
}


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

@Composable
fun CountdownTimer(value: String) {
    var remainingTime by remember { mutableStateOf(5 * 60) } // 5 minutes in seconds
    var timerJob by remember { mutableStateOf<Job?>(null) }

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(value) {
        timerJob?.cancel()

        if (value.isNotEmpty()) {
            timerJob = coroutineScope.launch {
                while (remainingTime > 0) {
                    delay(1000) // Delay for 1 second
                    remainingTime -= 1
                }
            }
        } else {
            remainingTime = 5 * 60 // Reset remaining time to 5 minutes when value is empty
        }

        onDispose {
            timerJob?.cancel()
        }
    }

    val formattedTime = remember(remainingTime) {
        String.format(
            "%02d:%02d",
            remainingTime / 60,
            remainingTime % 60
        )
    }

    Text(
        text = formattedTime,
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
        color = Color(0xFF223142),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500)
        )
    )
}

