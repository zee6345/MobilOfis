package com.app.uikit.bottomSheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

@Composable
fun BottomSheetCallCenter() {
    val showCallCenterBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showCallCenterBottomSheet.value = !showCallCenterBottomSheet.value
        }) {
            Text(text = "Show ModalBottomSheet")
        }
    }

    BottomSheetCallCenter(showCallCenterBottomSheet)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCallCenter(showModalBottomSheet: MutableState<Boolean>) {
    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "Contact",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            androidx.compose.material.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), border = BorderStroke(
                    1.dp, Color(R.color.border_grey)
                ), shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .width(72.dp)
                            .height(62.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 7.dp, start = 12.dp),
                    )
                    androidx.compose.material.Text(
                        text = "Call center",
                        style = TextStyle(color = Color(R.color.background_card_blue), fontSize = 14.sp),
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 12.dp)
                            .align(CenterVertically)

                    )
                }

            }

            androidx.compose.material.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 12.dp),
                border = BorderStroke(
                    1.dp, Color(R.color.border_grey)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Start,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = "",
                        modifier = Modifier
                            .size(62.dp)
                            .align(Alignment.Top)
                            .padding(end = 7.dp, start = 12.dp),
                    )
                    Column(modifier = Modifier.padding(top = 8.dp, start = 22.dp)) {
                        androidx.compose.material.Text(
                            text = "(077) 434-07-77",
                            style = TextStyle(color = Color(R.color.background_card_blue), fontSize = 14.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .dashedBorder(
                                    3.dp, Color(R.color.border_grey)
                                )
                                .padding(vertical = 12.dp)
                        )
                        androidx.compose.material.Text(
                            text = "(077) 434-17-77",
                            style = TextStyle(color = Color(R.color.background_card_blue), fontSize = 14.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .dashedBorder(
                                    3.dp, Color(R.color.border_grey)
                                )
                                .padding(vertical = 12.dp)
                        )
                        androidx.compose.material.Text(
                            text = "(077) 406-07-77",
                            style = TextStyle(color = Color(R.color.background_card_blue), fontSize = 14.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)

                        )
                    }

                }


            }

        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun CallCenterPreview() {
    BottomSheetCallCenter()
}
