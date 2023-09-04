package com.app.uikit.bottomSheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ContactBottomSheet() {
    val ContactState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(id = R.string.show_status_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                ContactState.value = !ContactState.value
            }

        )
    }

    ContactBottomSheet(ContactState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactBottomSheet(contactSheet: MutableState<Boolean>) {
    if (contactSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { contactSheet.value = false },
        shape = RoundedCornerShape(topStart = 12.sdp, topEnd = 12.sdp),
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)

        ) {
            androidx.compose.material3.Text(
                text = stringResource(R.string.contact),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(width = 5.sdp, height = 10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        border = BorderStroke(1.dp, colorResource(R.color.border_grey)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(color = Color(0xFFFFFFFF)),
                contentAlignment = Alignment.Center

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        ),
                    horizontalArrangement = Arrangement.Start,
                    Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_call_center),
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = stringResource(R.string._144),
                        style = TextStyle(
                            color = Color(0xFF223142),
                            fontSize = 20.sp,
                            FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Text(
                        text = stringResource(R.string.call_center),
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .border(
                        border = BorderStroke(1.dp, colorResource(R.color.border_grey)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(color = Color(0xFFFFFFFF)),
                contentAlignment = Alignment.Center

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(top = 10.dp, start = 5.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 20.dp)

                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .dashedBorder(3.dp, colorResource(R.color.border_grey))
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string._077_434_07_77),
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .dashedBorder(3.dp, colorResource(R.color.border_grey))
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string._077_434_07_77),
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .dashedBorder(3.dp, colorResource(R.color.border_grey))
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string._077_434_07_77),
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }

                    }
                }
            }
        }
    }

}