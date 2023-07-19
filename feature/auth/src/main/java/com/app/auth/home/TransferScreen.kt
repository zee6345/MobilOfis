package com.app.auth.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.home.transferDetails.FiltersTopRow
import com.app.auth.home.transferDetails.ItemClickedCallback
import com.app.auth.home.transferDetails.TransferTopMenu

@Composable
fun TransferScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),

                    contentDescription = "", tint = Color.White
                )
                Text(
                    text = "Transfers",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(0.9f)
                .padding(horizontal = 10.dp),
        ) {

            TopMenuItem()
            FilterListMenu()
        }

    }

}

@Composable
fun TopMenuItem() {
    TransferTopMenu()
}

@Composable
fun FilterListMenu(){
    FiltersTopRow(object : ItemClickedCallback{
        override fun itemClicked(id: String) {

        }

    })
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TransferScreenPreview() {
    TransferScreen()
}