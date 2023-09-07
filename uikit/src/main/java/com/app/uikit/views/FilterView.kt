package com.app.uikit.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.models.CardFilters
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FilterView(filter: CardFilters, onFilterClick: (CardFilters) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable {
                onFilterClick(filter)
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

            if (filter.filterIcon != null) {
                Image(
                    painter = painterResource(id = filter.filterIcon!!),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.dp)
                        .width(14.dp)
                        .height(14.dp)
                )
            }
        }
    }
}