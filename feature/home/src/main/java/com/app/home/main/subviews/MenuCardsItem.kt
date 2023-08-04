package com.app.home.main.subviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


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


//@Composable
//fun CardsListItems(list: CardsListData) {
//
//    Card(
//        modifier = Modifier
//            .padding(vertical = 5.dp)
//            .fillMaxWidth(),
//        elevation = 1.dp,
//        backgroundColor = Color.White,
//        shape = RoundedCornerShape(corner = CornerSize(12.dp))
//    ) {
//        Row(modifier = Modifier.padding(vertical = 12.dp)) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(start = 12.dp)
//            ) {
//                Image(
//                    painter = painterResource(list.image),
//                    modifier = Modifier
//                        .align(Alignment.CenterVertically)
//                        .width(36.dp)
//                        .height(24.dp),
//                    contentDescription = ""
//                )
//                Column(modifier = Modifier.padding(start = 12.dp)) {
//                    Text(
//                        text = list.title,
//                        style = TextStyle(
//                            color = Color(R.color.background_card_blue),
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight(400)
//                        ),
//                        modifier = Modifier
//                            .wrapContentWidth(align = Alignment.Start),
//                        maxLines = 2,
//                    )
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(list.card_icon),
//                            modifier = Modifier
//                                .width(30.dp)
//                                .height(14.dp),
//                            contentDescription = ""
//                        )
//                        Text(
//                            text = list.card_num,
//                            fontWeight = FontWeight(600),
//                            modifier = Modifier.padding(start = 8.dp)
//                        )
//                        Box(
//                            modifier = Modifier
//                                .padding(start = 12.dp)
//                                .clip(
//                                    shape = RoundedCornerShape(15.dp),
//                                )
//                                .background(
//                                    color = Color(0xFFE7F0F9),
//                                )
//                        ) {
//                            Text(
//                                modifier = Modifier.padding(2.dp),
//                                text = "+1",
//                                color = Color(R.color.grey_text), fontSize = 12.sp
//                            )
//
//                        }
//                    }
//
//                }
//            }
//            Row() {
//                Text(
//                    text = "10 000 000.",
//                    modifier = Modifier.align(Alignment.Top),
//                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
//                    color = Color(R.color.background_card_blue)
//                )
//                Text(
//                    text = "00",
//                    modifier = Modifier
//                        .align(Alignment.Bottom)
//                        .padding(vertical = 3.dp),
//                    style = TextStyle(fontSize = 12.sp),
//                    color = Color(R.color.background_card_blue)
//                )
//                Text(
//                    text = "₼",
//                    modifier = Modifier
//                        .padding(end = 22.dp)
//                        .padding(3.dp)
//                        .align(Alignment.Bottom),
//                    style = TextStyle(fontSize = 12.sp),
//                    color = Color(R.color.background_card_blue)
//                )
//            }
//
//
//        }
//    }
//}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabCardPreview() {
//    CardsListView()
}
