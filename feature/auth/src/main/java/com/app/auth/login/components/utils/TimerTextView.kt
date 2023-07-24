package com.app.auth.login.components.utils

import android.os.CountDownTimer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.concurrent.TimeUnit

@Composable
fun TimerTextView() {
    val remainingTime = remember { mutableStateOf(5 * 60 * 1000L) } // 5 minutes in milliseconds

    LaunchedEffect(Unit) {
        object : CountDownTimer(remainingTime.value, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime.value = millisUntilFinished
            }

            override fun onFinish() {
                // Timer finished, handle any necessary actions
            }
        }.start()
    }

    val formattedTime = remember(remainingTime.value) {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(remainingTime.value),
            TimeUnit.MILLISECONDS.toSeconds(remainingTime.value) % 60
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