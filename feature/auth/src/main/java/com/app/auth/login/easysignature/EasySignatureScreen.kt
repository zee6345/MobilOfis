package com.app.auth.login.easysignature

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.borders.dashedBorder
import com.app.uikit.bottomSheet.AsanImzaBottomSheet
import com.app.uikit.utils.SharedModel
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EasySignatureScreen(navController: NavController) {

    val easyCode = SharedModel.init().easyVerificationCode.value
    val asanImzaHelp = remember { mutableStateOf(false) }
    val context = LocalContext.current


    //backpress
    BackHandler(enabled = true, onBack = {
        navController.navigate(loginNavigationRoute) {
            popUpTo(loginNavigationRoute) { inclusive = true }
        }
    })


    //rest
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F7FA))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {

            Column(Modifier.fillMaxSize()) {

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(0.1f)

                ) {


                    CurvedBottomBox(
                        color = Color(0xff334b66),
                        curveHeight = 30.dp
                    )

                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(0.2f)
                        .padding(horizontal = 20.sdp, vertical = 15.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    androidx.compose.material3.Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        text = "Login with Asan Imza",
                        style = TextStyle(color = Color.White, fontSize = 29.sp)
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 22.dp),
                backgroundColor = Color.White
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .dashedBorder(
                                3.dp, colorResource(R.color.border_grey)
                            )
                            .height(80.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.print_design),
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .height(80.dp)
                                .width(100.dp),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_question),
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    asanImzaHelp.value = true
                                }
                                .padding(end = 12.dp),
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier
                            .dashedBorder(
                                3.dp, colorResource(R.color.border_grey)
                            )
                            .padding(horizontal = 12.dp, vertical = 22.dp),
                        text = "Please accept the query sent to your phone. Compare the checking code of the survey to the same code as the following code.",
                        style = TextStyle(fontSize = 16.sp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, bottom = 5.dp)
                            .fillMaxWidth(),
                        text = "Check code",
                        style = TextStyle(
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$easyCode",
                        style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center)
                    )

                    Spacer(modifier = Modifier.size(height = 20.sdp, width = 1.sdp))
                    CircularTimer()

                }
            }


        }

    }


    AsanImzaBottomSheet(asanImzaState = asanImzaHelp, navController = navController)

}

@Composable
fun CircularTimer() {
    val totalTimeSeconds = 2 * 60 // 2 minutes in seconds
    var remainingTime by remember { mutableStateOf(totalTimeSeconds) }

    val progress = (totalTimeSeconds - remainingTime) / totalTimeSeconds.toFloat()

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val timerJob = coroutineScope.launch {
            while (remainingTime > 0) {
                delay(1000) // Delay for 1 second
                remainingTime -= 1
            }
        }

        onDispose {
            timerJob.cancel()
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = Color(0xffECE9FF),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .size(150.dp)
        )
        Text(
            text = "${remainingTime / 60}:${String.format("%02d", remainingTime % 60)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff7B61FF)
        )
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun EasySignatureScreenPreview() {
    EasySignatureScreen(rememberNavController())
}