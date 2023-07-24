package com.app.auth.login.easysignature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R
import com.app.auth.login.components.bottomSheet.dashedBorder

@Composable
fun EasySignatureScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "Introduction with Easy Signature",
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    modifier = Modifier.padding(bottom = 22.dp)
                )


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
                                3.dp, Color(0xFFE7EEFC)
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
                                .height(80.dp).width(100.dp),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(id = R.drawable.question_icon),
                            modifier = Modifier.size(50.dp)
                                .align(Alignment.CenterVertically).padding(end = 12.dp),
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier
                            .dashedBorder(
                                3.dp, Color(0xFFE7EEFC)
                            )
                            .padding(horizontal = 12.dp , vertical = 22.dp),
                        text = "Please accept the query sent to your phone. Compare the checking code of the survey to the same code as the following code.",
                        style = TextStyle(fontSize = 16.sp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, bottom = 5.dp)
                            .fillMaxWidth(),
                        text = "Check code",
                        style = TextStyle(
                            color = Color(0xFF859DB5),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "7960",
                        style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center)
                    )

                }
            }


        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun EasySignatureScreenPreview() {
    EasySignatureScreen()
}