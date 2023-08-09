package com.app.adjustment.googleauth

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R


const val userProfileToGoogleAuth = "userProfileToGoogleAuth"

@Composable
fun ActivateGoogleAuthenticatorScreen(navController: NavHostController) {

    val context = LocalContext.current

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
                        .size(height = 25.dp, width = 32.dp)
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
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                backgroundColor = Color.White
            ) {

                Column() {

                    Image(
                        painter = painterResource(R.drawable.ic_google_auth),
                        contentDescription = "",
                        modifier = Modifier
                            .size(124.dp)
                            .padding(top = 22.dp)
                            .align(CenterHorizontally),
                    )

                    Text(
                        text = "Please download the Google Authenticator app.",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color(0xff223142),
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp)

                    )


                    Button(
                        onClick = {

                            val url = "https://play.google.com/store/apps/details?id=com.google.android.apps.authenticator2"
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(url)
                            startActivity(context, i, null)

                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF203657), // Change the background color here
                            contentColor = Color.White // Change the text color here if needed
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 22.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            "Download the application",
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp, shadow = null
                            )
                        )
                    }
                    Button(
                        onClick = {
                                  navController.navigate(googleAuthToOtpVerify)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White, // Change the background color here
                            contentColor = Color(0xFF203657) // Change the text color here if needed
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 22.dp)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                color = Color(0xFF203657),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Text(
                            "Already loaded",
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
fun ActivateGoogleAuthenticatorScreenPreview() {
    ActivateGoogleAuthenticatorScreen(rememberNavController())
}