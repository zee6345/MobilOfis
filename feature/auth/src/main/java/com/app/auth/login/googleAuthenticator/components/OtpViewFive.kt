package com.app.auth.login.googleAuthenticator.components


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
import com.app.auth.R


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 5,
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
                    Spacer(modifier = Modifier.width(7.dp))
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
            .width(45.dp).height(60.dp)
            .border(
                width = 1.dp, color = Color(R.color.border_grey), shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
            .background(color = Color.White),
        text = char,
        style = MaterialTheme.typography.h4,
        color = if (isFocused) {
            Color(R.color.background_card_blue)
        } else {
            Color(R.color.background_card_blue)
        },
        textAlign = TextAlign.Center
    )
}

@Composable
fun OtpViewFive() {
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

        OtpTextField(otpText = otpValue, onOtpTextChange = { value, otpInputFilled ->
            otpValue = value
        })
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun OtpPreview() {
    OtpViewFive()
}
