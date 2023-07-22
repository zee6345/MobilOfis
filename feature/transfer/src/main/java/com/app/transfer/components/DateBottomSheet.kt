package com.app.transfer.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.transfer.R
import com.app.transfer.transfers.DataClassTransfer
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun DateBottomSheet() {
    val showDateBottomSheetSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = "Show Date BottomSheet"),
//                    color = Color(0xFF203657),
            onClick = {
                showDateBottomSheetSheet.value = !showDateBottomSheetSheet.value
            }

        )
    }

    DateBottomSheet(showDateBottomSheetSheet)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(showDateBottomSheet: MutableState<Boolean>) {
    if (showDateBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showDateBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 12.sdp, topEnd = 12.sdp),
        containerColor = Color.White

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Text(
                text = "Duration",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142),


                )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            DateTypeMenu()
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Text(
                text = "Duration",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.sdp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF859DB5),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemsCardRow("18.11.2021")
                Box(
                    modifier = Modifier
                        .width(10.sdp)
                        .height(1.sdp)
                        .background(
                            color = Color(0xFFE7EEFC), shape = RoundedCornerShape(size = 10.sdp)
                        )
                        .align(Alignment.CenterVertically)
                )
                ItemsCardRow("14.11.2021")
            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString("Cancel"),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        showDateBottomSheet.value = false
                    },
                )

                ClickableText(
                    text = AnnotatedString("Apply"),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        showDateBottomSheet.value = false
                    },

                    )
            }


            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
        }
    }
}


data class DateMenuModel(
    val title: String,
    val textColor: Color,
    val color: Color
)

@Composable
fun DateTypeMenu() {
    val menu = remember { DataClassTransfer.DateMenu }
    LazyRow(
    ) {
        items(items = menu, itemContent = {
            DateMenuItem(menuItem = it)
        })
    }
}

@Composable
fun DateMenuItem(menuItem: DateMenuModel) {
    Card(
        modifier = Modifier.padding(horizontal = 4.dp),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = menuItem.color
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            androidx.compose.material.Text(
                text = menuItem.title,
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 8.dp
                    ),
                fontSize = 12.ssp,
                color = menuItem.textColor
            )
        }
    }
}

@Composable
fun ItemsCardRow(text: String) {
    Card(
        modifier = Modifier
            .padding(2.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "calender",
                tint = colorResource(R.color.blue),
                modifier = Modifier
                    .padding(horizontal = 5.sdp)
                    .align(Alignment.CenterVertically)
                    .size(width = 18.dp, height = 18.dp)
            )
            androidx.compose.material.Text(
                text = text,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 12.sp,
                color = Color(0xFF223142)
            )
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = "calender",
                modifier = Modifier
                    .padding(horizontal = 5.sdp)
                    .align(Alignment.CenterVertically),
                tint = Color.Red,

                )

        }
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun previewpassword() {
    DateBottomSheet()
}