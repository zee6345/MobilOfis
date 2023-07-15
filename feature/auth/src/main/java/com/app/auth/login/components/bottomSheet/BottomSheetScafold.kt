package com.app.auth.login.components.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.auth.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@ExperimentalMaterialApi
@Composable
fun BottomSheetMain() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed)
    )

    // Declaring Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // Creating a Bottom Sheet
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.sdp)
                    .background(Color(0XFF0F9D58))) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Information and Content",
                        fontSize = 20.ssp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 18.dp)
                            .dashedBorder(
                                3.dp, Color(0xFFE7EEFC)
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            modifier = Modifier
                                .height(28.dp)
                                .width(34.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = ""
                        )
                        androidx.compose.material3.Text(text = "Branches and ATMs", modifier = Modifier.padding(vertical = 12.dp))
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .dashedBorder(
                                3.dp, Color(0xFFE7EEFC)
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.tariffs_icon),
                            modifier = Modifier
                                .height(28.dp)
                                .width(34.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = ""
                        )
                        androidx.compose.material3.Text(text = "Tariffs", modifier = Modifier.padding(vertical = 12.dp))
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .dashedBorder(
                                3.dp, Color(0xFFE7EEFC)
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.whatsapp_icon),
                            modifier = Modifier
                                .height(28.dp)
                                .width(34.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = ""
                        )
                        androidx.compose.material3.Text(text = "WhatsApp support", modifier = Modifier.padding(vertical = 12.dp))
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .dashedBorder(
                                3.dp, Color(0xFFE7EEFC)
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.call_icon),
                            modifier = Modifier
                                .height(28.dp)
                                .width(34.dp)
                                .align(Alignment.CenterVertically)
                                .fillMaxWidth(),

                            contentDescription = ""
                        )
                        androidx.compose.material3.Text(text = "Call Center", modifier = Modifier.padding(vertical = 12.dp))
                    }
                    Row(
                        modifier = Modifier.padding(start = 18.dp),

                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.language),
                            modifier = Modifier
                                .height(28.dp)
                                .width(34.dp)
                                .align(Alignment.CenterVertically)
                                .fillMaxWidth(),
                            contentDescription = ""
                        )
                        androidx.compose.material3.Text(text = "Application Language", modifier = Modifier.padding(vertical = 12.dp))
                    }
                }
            }
        },
        sheetPeekHeight = 40.sdp
    ) {}


}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomSheetMain()
}