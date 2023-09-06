package com.app.uikit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinInputView(
    length: Int,
    onPinEntered: (String) -> Unit,
    onKeyEntered: (String) -> Unit,
    resetPin:Boolean = false,
) {
    val pinValue = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(20.dp),
        color = Color.Transparent
    ) {
        PinTextField(
            otpText = pinValue.value,
            onOtpTextChange = { value, _ ->
                pinValue.value = value.take(length)
            }
        )
    }

    CustomKeyboard { key ->
        if (key == "del") {
            if (pinValue.value.isNotEmpty()) {
                pinValue.value = pinValue.value.dropLast(1)
            }
        } else if (key == "fngr") {
            onKeyEntered("fngr")
        } else {
            if (pinValue.value.length < length) {
                pinValue.value += key
            }
        }

        if (pinValue.value.length == length) {
            onPinEntered(pinValue.value)
        }
    }


    if (resetPin){
        pinValue.value = ""
    }
}