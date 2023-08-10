package com.app.adjustment.googleauth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.uikit.views.OtpView

const val otpToConfirmGoogleAuthOtp = "otpToConfirmGoogleAuthOtp"

@Composable
fun ConfirmSMSCodeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true)
            .background(color = colorResource(R.color.border_light_grey))
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
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        },
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
            Column() {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    backgroundColor = Color.White
                ) {
                    Column() {
                        Text(
                            text = "Copy the code shown here and enter it in the Google Authenticator app",
                            style = TextStyle(fontSize = 16.sp),
                            color = Color(0xff223142),
                            modifier = Modifier
                                .padding(horizontal = 22.dp)
                                .padding(top = 22.dp)

                        )
                        Box(modifier = Modifier
                            .padding(22.dp)
                            .fillMaxWidth()
                            .background(
                                Color.White
                            )
                            .border(
                                width = 1.dp,
                                color = Color(0xFFE7EEFC),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {}) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp, horizontal = 12.dp)
                                    .padding(end = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val scrollState = rememberScrollState()
                                val textValue =
                                    "sdvs76876 üf7 76fgbasf7 768b"
                                Text(
                                    text = textValue,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 12.dp)
                                        .horizontalScroll(scrollState),
                                    style = TextStyle(
                                        Color(0xFF223142), fontSize = 16.sp

                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_copy),
                                    modifier = Modifier.align(CenterVertically),
                                    contentDescription = ""
                                )
                            }


                        }

                    }
                }
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    backgroundColor = Color.White
                ) {

                    Column {
                        Text(
                            text = "After the new line containing the 6-digit code is added in the Google Authenticator app, please enter it and click \"Confirm\".\n\nEnter the Google code",
                            style = TextStyle(fontSize = 16.sp),
                            color = Color(0xff223142),
                            modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp)

                        )
//                        OtpViewSix()
                        OtpView(viewCount = 6, onValueChange = {

                        })
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
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun ConfirmSMSCodeScreenPreview() {
    ConfirmSMSCodeScreen(rememberNavController())
}