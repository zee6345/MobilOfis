package com.app.transfer.transfers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem


@Composable
fun TransferTopMenu(transferHeaderList: MutableList<TransferCountSummaryResponseItem>) {
//    val menu = remember { DataProvider.topMenuList }
    LazyRow(
    ) {
        items(items = transferHeaderList, itemContent = {
            TransferMenuItemView(menu = it)
            Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))
        })
    }
}

@Composable
fun TransferMenuItemView(menu: TransferCountSummaryResponseItem) {
    var status = ""
    var color: Color? = null

    when (menu.status) {
        "PENDING_SIGNER" -> {
            status = "For signing"
            color = Color(0xff268ED9)
        }

        "CLOSED" -> {
            status = "Executed"
            color = Color(0xff26D978)
        }

        "PENDING_ALL" -> {
            status = "Sign and confirmation"
            color = Color(0xFFC74375)
        }

        "BANK_SUCCESS" -> {
            status = "Sent to the bank"
            color = Color(0xFFF48A1D)
        }

        "BANK_ERROR" -> {
            status = "Not processed"
            color = Color(0xff2CCAD3)
        }

        "DELETED" -> {
            status = "Deleted"
            color = Color(0xFFE91E63)
        }

        "BANK_REJECTED" -> {
            status = "Rejected"
            color = Color(0xFFE91E63)
        }

        "PENDING_APPROVER" -> {
            status = "For confirmation"
            color = Color(0xFFFF5722)
        }

        "EXPIRED" -> {
            status = "Expired"
            color = Color(0xFFF80658)
        }

        "SEND_TO_BANK" -> {
            status = "In process"
            color = Color(0xFFCDDC39)
        }
    }


    Card(
        modifier = Modifier
            .padding(vertical = 5.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = status,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp
            )
            Text(
                text = "${menu.count}", modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = color!!, radius = 12.dp.toPx()
                        )
                    }, fontSize = 14.sp, color = Color.White
            )

        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun MenuPreview() {
//    TransferTopMenu(transferHeaderList)
}