package com.app.auth.login.components.bottomSheet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FingerPrintConfirBottomSheetScreen() {
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = "Forgot your password?"),
//                    color = Color(0xFF203657),
            onClick = {
                showForgetPassBottomSheetSheet.value = !showForgetPassBottomSheetSheet.value
            }

        )
    }

    FingerPrintModalBottomSheet(showForgetPassBottomSheetSheet, {

    }, {

    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FingerPrintModalBottomSheet(
    showModalBottomSheet: MutableState<Boolean>, onClickThen: () -> Unit,
    onClickYes: () -> Unit
) {
    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp)
        ) {
            Text(
                text = "Do you want to activate \"fingerprint\"?",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
                    .padding(horizontal = 12.dp),
                fontSize = 17.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 17.dp)
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                androidx.compose.material.Button(
                    onClick = {
                        onClickThen()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color(0xFFE7F0F9), RoundedCornerShape(12.dp))
                ) {
                    Text(
                        "Then", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            color = Color(0xFF203657), fontSize = 17.sp, shadow = null
                        )
                    )
                }
                androidx.compose.material.Button(
                    onClick = {
                        onClickYes()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color(0xFF203657), RoundedCornerShape(12.dp))
                ) {
                    Text(
                        "Yes", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            color = Color.White, fontSize = 17.sp, shadow = null
                        )
                    )
                }
            }

        }
    }
}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun previewfingerPrint() {
    FingerPrintConfirBottomSheetScreen()
}