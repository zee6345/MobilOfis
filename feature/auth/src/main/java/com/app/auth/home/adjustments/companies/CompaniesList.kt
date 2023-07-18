package com.app.auth.home.adjustments.companies

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R
import com.app.auth.home.menu.DataProvider

data class CompanyListName(
    val name: String,
)

@Composable
fun CompanyList() {
//    val companyList = remember { DataProvider.companyList }
//    LazyColumn(
//        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
//    ) {
//        items(items = companyList, itemContent = {
//            CompanyListItem(list = it)
//        })
//    }
}

@Composable
fun CompanyListItem(list: CompanyListName) {

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(

        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .weight(0.6f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color(0xFF223142)),
                    modifier = Modifier
                        .size(28.dp)
                        .padding(5.dp)
                        .align(Alignment.CenterVertically),
                )
                Text(
                    text = list.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    color = Color(0xff223142),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.4f))


        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun CompanyPreview() {
    CompanyList()
}