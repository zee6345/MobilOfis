package com.app.home.main.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.data.DataProvider
import com.app.uikit.models.CardMenu

@Composable
fun CardMenuContent() {
    val menu = remember { DataProvider.menuList }
    LazyRow(
    ) {
        items(items = menu, itemContent = {
            ItemMenu(menu = it)
        })
    }
}

@Composable
fun ItemMenu(menu: CardMenu) {

    Card(
        modifier = Modifier
            .padding(5.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = menu.title,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp
            )
            Text(
                text = "${menu.number}", modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = menu.color, radius = 12.dp.toPx()
                        )
                    }, fontSize = 14.sp, color = Color.White
            )

        }
    }
}




@Preview(device = Devices.PIXEL_4)
@Composable
fun MenuPreview() {
    CardMenuContent()
}