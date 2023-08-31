package com.app.uikit.bottomSheet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ForgetPasswordBottomSheetScreen() {
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

    ForgetPasswordModalBottomSheet(showForgetPassBottomSheetSheet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordModalBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp)
        ) {
            Text(
                text = "If you have forgotten your username and/or \npassword, call 144 or visit a bank branch\n (with an identity document)",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 22.dp)
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Normal
            )
            Button(
                onClick = {
                    showModalBottomSheet.value = !showModalBottomSheet.value
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),// Optional: To override other button colors
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(Color(0xFF203657), RoundedCornerShape(8.dp))

            ) {
                Text(
                    "Close it", modifier = Modifier.padding(vertical = 12.dp), color = Color.White
                )
            }


        }
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun previewpassword() {
    ForgetPasswordBottomSheetScreen()
}