package com.app.auth.login

import CustomKeyboard
import CustomKeyboardOtp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = "Enter SMS code", style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "Please enter the SMS code sent to the\n number ***68-06.",
                        style = TextStyle(
                            color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OtpView()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 17.dp)
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(15.dp),
                        )
                        .background(
                            color = Color(0xFFE7F0F9),
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                        text = "01:00",
                        color = Color(0xFF223142)
                    )

                }

                ClickableText(modifier = Modifier.padding(5.dp),
                    text = AnnotatedString(text = "Re-send SMS code"),
//                    color = Color(0xFF203657),
                    onClick = { })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 17.dp)
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color(0xFFE7F0F9), RoundedCornerShape(10.dp))
                ) {
                    Text(
                        "Close", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            color = Color(0xFF203657),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            shadow = null
                        )
                    )
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color(0xFF203657), RoundedCornerShape(10.dp))
                ) {
                    Text(
                        "Next", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            shadow = null
                        )
                    )
                }
            }
            CustomKeyboardOtp()
        }

    }

}


@Preview(device = Devices.PIXEL_4)
@Composable
fun OtpScreenPreview() {
    OtpScreen()
}
