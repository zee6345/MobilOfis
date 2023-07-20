package com.app.home.menu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.home.data.CardFilters

import com.app.home.data.DataProvider
import com.app.home.data.TrustsData
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TrustsList() {

    val cardsList = remember { DataProvider.trustsDataList }
    val cardFilters = remember { DataProvider.filtersTrustsList }

    Column(
        modifier = Modifier.padding(horizontal = 5.sdp, vertical = 5.sdp)
    ) {


        Filters()

        LazyRow(
            contentPadding = PaddingValues(vertical = 5.dp)
        ) {
            items(items = cardFilters, itemContent = {
                Row {
                    FilterView(filter = it)
                    Box(modifier = Modifier.padding(end = 5.sdp))
                }

            })
        }


        LazyColumn(
            contentPadding = PaddingValues(vertical = 5.dp)
        ) {
            items(items = cardsList, itemContent = {
                TrustsListItem(obj = it)
            })
        }

    }


}


@Composable
private fun TrustsListItem(obj: TrustsData) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column() {

                Text(text = obj.title, style = TextStyle(fontSize = 14.sp))

                Text(
                    obj.snNumber,
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF859DB5))
                )
            }

            Column(

            ) {

                Text(
                    text = obj.amount,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF223142),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = obj.amountPrcnt,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .fillMaxWidth()

                )


            }


        }
    }
}

@Composable
private fun FilterView(filter: CardFilters) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable {

            },
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))

    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.sdp)
        ) {

            Text(
                text = filter.filterName, style = TextStyle(fontSize = 12.sp)
            )

            Image(
                painter = painterResource(id = filter.filterIcon),
                contentDescription = "",
                modifier = Modifier
                    .padding(1.dp)
                    .width(14.dp)
                    .height(14.dp)
            )
        }
    }


}


@Composable
private fun Filters() {

    val selectedBoxIndex = remember { mutableStateOf(-1) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable { selectedBoxIndex.value = 0 }) {
            Text(
                "Current deposits", style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142)
                )
            )
        }


        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable { selectedBoxIndex.value = 1 }) {
            Text(
                "Closed deposits", style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142)
                )
            )
        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TrustsPreview() {
    TrustsList()
}