package com.app.transfer.signatureauth.message

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.transfer.R
import com.app.transfer.signatureauth.signingType
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.models.AuthType
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun SignFailed(navController: NavController) {

    var selected by remember { mutableStateOf(0) }

//    val signInfo = SharedModel.init().signInfo
    val index0 = remember { mutableStateOf(true) }
    val index1 = remember { mutableStateOf(false) }
    val index2 = remember { mutableStateOf(false) }
    val usernameState = remember { mutableStateOf("") }
    val paswdState = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    var isPswdVisible by remember { mutableStateOf(false) }
    val otpCount = remember { mutableStateOf(6) }
    val coroutine = rememberCoroutineScope()
    val otpValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val passwordVisualTransformation =
        if (isPswdVisible) VisualTransformation.None else PasswordVisualTransformation()


    val authWith = when (signingType.value) {
        AuthType.SMS -> {
            "Access by SMS"
        }

        AuthType.GOOGLE_AUTH -> {
            "Access with Google Auth"
        }

        AuthType.ASAN_IMZA -> {
            "Access with Asan Imza"
        }
    }

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
                        .padding(horizontal = 20.sdp, vertical = 10.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {


                        Image(
                            painterResource(id = R.drawable.ic_sign_failed),
                            contentDescription = "",
                            Modifier
                                .size(24.sdp)
                                .padding(horizontal = 5.dp)
                        )

                        Box() {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = "Operation failed!",
                                style = TextStyle(color = Color.White, fontSize = 29.sp)
                            )
                        }

                    }


                }

            }

        }

        Column(
            modifier = Modifier.weight(0.7f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    Modifier.padding(horizontal = 20.sdp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "An error occurred!\n" + "Please try again.",
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        )
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    Modifier.padding(horizontal = 20.sdp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_success_img),
                        contentDescription = null
                    )
                }


                Box(
                    Modifier
                        .padding()
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            (context as ComponentActivity).finish()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.sdp)
                            .background(
                                Color(0xFF203657),
                                RoundedCornerShape(8.dp)
                            )
                            .fillMaxWidth()
                    ) {
                        androidx.compose.material.Text(
                            "Close",
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color.White
                        )
                    }
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignFailed() {
    SignFailed(navController = rememberNavController())
}