package com.app.transfer.transfers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.app.transfer.R

import ir.kaaveh.sdpcompose.sdp

data class FilterModel(
    val id: String,
    val title: String,
    val icon: ImageVector,
    var isSelected: Boolean
)

@Composable
fun FiltersTopRow(callback: ItemClickedCallback) {
    val menu = remember { DataClassTransfer.filtersModelList }
    LazyRow(
    ) {
        items(items = menu, itemContent = {
            FiltersMenuData(menu = it, callback)
            Spacer(modifier = Modifier.size(width=5.dp, height=1.dp))
        })
    }
}

@Composable
fun FiltersMenuData(menu: FilterModel, callback: ItemClickedCallback) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.clickable {
                callback.itemClicked(menu.id)
                menu.isSelected = !menu.isSelected
            }) {
            Text(
                text = menu.title,
                modifier = Modifier
                    .padding(5.sdp)
                    .padding(horizontal = 7.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp
            )
            Icon(
                imageVector = if (menu.isSelected) {
                    Icons.Filled.Close
                } else {
                    menu.icon
                },
                contentDescription = "Close",
                tint = if (menu.isSelected) {
                    colorResource(id = R.color.red)
                } else {
                    colorResource(R.color.black)
                },
                modifier = Modifier
                    .padding(5.sdp)
                    .align(Alignment.CenterVertically)
                    .size(10.sdp, 10.sdp)
            )

        }
    }
}

interface ItemClickedCallback {
    fun itemClicked(id: String)
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun FiltersMenu() {
    FiltersTopRow(object : ItemClickedCallback {
        override fun itemClicked(id: String) {

        }

    })
}