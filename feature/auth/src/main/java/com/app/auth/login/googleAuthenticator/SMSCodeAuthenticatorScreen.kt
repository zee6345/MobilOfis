package com.app.auth.login.googleAuthenticator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R
import com.app.auth.login.googleAuthenticator.components.OtpViewFive
import com.app.uikit.views.TimerTextView


@Composable
fun SMSCodeAuthenticatorScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = ""
                )
                Text(
                    text = "Google Authenticator",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }
        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                backgroundColor = Color.White
            ) {

                Column() {


                    Text(
                        text = "Please confirm the start of the activation process of Google Authenticator through the SMS code sent to your mobile number",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color(R.color.background_card_blue),
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp)

                    )
                    OtpViewFive()
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

                            TimerTextView()

                        }

                        ClickableText(modifier = Modifier.padding(5.dp).align(CenterVertically),
                            text = AnnotatedString(text = "Re-send SMS code"),
                            style = TextStyle(color = Color(0xFF203657), fontSize = 14.sp),
                            onClick = { })
                    }


                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF203657), // Change the background color here
                            contentColor = Color.White // Change the text color here if needed
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 22.dp)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            "Confirm it",
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp, shadow = null
                            )
                        )
                    }

                }


            }


        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun SMSCodeAuthenticatorScreenPreview() {
    SMSCodeAuthenticatorScreen()
}