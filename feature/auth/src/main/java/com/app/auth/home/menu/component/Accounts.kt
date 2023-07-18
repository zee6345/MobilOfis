package com.app.auth.home.menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.data.DataProvider

data class AccountListData(
    val title: String,
    val description: String,
)

@Composable
fun AccountList() {
    val accountList = remember { DataProvider.accountList }
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
    ) {
        items(items = accountList, itemContent = {
            AccountListItem(list = it)
        })
    }
}

@Composable
fun AccountListItem(list: AccountListData) {

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = list.title,
                    style = TextStyle(fontSize = 14.sp),
                    color = Color(0xFF223142)
                )
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(15.dp),
                        )
                        .background(
                            color = Color(0xFFF3F7FA),
                        )
                ) {
                    Text(
                        text = list.description,
                        modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                        style = TextStyle(fontSize = 11.sp),
                        color = Color(0xFF859DB5)
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "2300.",
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = TextStyle(fontSize = 14.sp),
                    color = Color(0xFF223142)
                )
                Text(
                    text = "00",
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .align(Bottom),
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF223142)
                )
                Text(
                    text = "₼",
                    modifier = Modifier
                        .padding(end = 22.dp)
                        .padding(vertical = 3.dp)
                        .align(Bottom),
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF223142)
                )
            }

        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabPreview() {
    AccountList()
}