package com.app.uikit.views


import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        singleLine = true,
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index, text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        })
}

@Composable
private fun CharView(
    index: Int, text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .border(
                width = 1.dp, color = Color(0xFFE7EEFC), shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
            .background(color = Color.White),
        text = char,
        style = MaterialTheme.typography.h4,
        color = if (isFocused) {
            Color(0xFF223142)
        } else {
            Color(0xFF223142)
        },
        textAlign = TextAlign.Center
    )
}

@Composable
fun OtpView(viewCount:Int, onValueChange: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    focusRequester.requestFocus()
                }
            }
            .padding(20.dp),
        color = Color.Transparent
    ) {
        var otpValue by remember {
            mutableStateOf("")
        }

        OtpTextField(
            otpCount= viewCount,
            otpText = otpValue,
            onOtpTextChange = { value, _ ->
                otpValue = value
            })

        onValueChange(otpValue)
    }
}

@Composable
fun OtpView(viewCount:Int, presetValue:String, onValueChange: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    var otpValue by remember { mutableStateOf("") }

    if (presetValue.isNotEmpty()){
        otpValue = presetValue
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    focusRequester.requestFocus()
                }
            }
            .padding(20.dp),
        color = Color.Transparent
    ) {


        OtpTextField(
            otpCount= viewCount,
            otpText = otpValue,
            onOtpTextChange = { value, _ ->
                otpValue = value
            })

        onValueChange(otpValue)
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun OtpPreview() {
    OtpView(6) {

    }
//    Abc()
}
