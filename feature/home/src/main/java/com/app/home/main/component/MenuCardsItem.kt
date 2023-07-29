package com.app.home.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class CardsListData(
    val title: String,
    val image: Int,
    val card_icon: Int,
    val card_num: String,
)

@Composable
fun CardsListView() {
//    val cardList = remember { DataProvider.cardList }
//    LazyColumn(
//        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
//    ) {
//        items(items = cardList, itemContent = {
//            CardsListItem(list = it)
//        })
//    }
}


@Composable
fun CardsListItems(list: CardsListData) {

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(modifier = Modifier.padding(vertical = 12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Image(
                    painter = painterResource(list.image),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(36.dp)
                        .height(24.dp),
                    contentDescription = ""
                )
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = list.title,
                        style = TextStyle(
                            color = Color(0xFF223142),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400)
                        ),
                        modifier = Modifier
                            .wrapContentWidth(align = Alignment.Start),
                        maxLines = 2,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(list.card_icon),
                            modifier = Modifier
                                .width(30.dp)
                                .height(14.dp),
                            contentDescription = ""
                        )
                        Text(
                            text = list.card_num,
                            fontWeight = FontWeight(600),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .clip(
                                    shape = RoundedCornerShape(15.dp),
                                )
                                .background(
                                    color = Color(0xFFE7F0F9),
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "+1",
                                color = Color(0xFF859DB5), fontSize = 12.sp
                            )

                        }
                    }

                }
            }
            Row() {
                Text(
                    text = "10 000 000.",
                    modifier = Modifier.align(Alignment.Top),
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFF223142)
                )
                Text(
                    text = "00",
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(vertical = 3.dp),
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF223142)
                )
                Text(
                    text = "₼",
                    modifier = Modifier
                        .padding(end = 22.dp)
                        .padding(3.dp)
                        .align(Alignment.Bottom),
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF223142)
                )
            }


        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabCardPreview() {
//    CardsListView()
}
