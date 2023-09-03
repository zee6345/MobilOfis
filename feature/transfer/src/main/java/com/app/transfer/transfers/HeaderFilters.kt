package com.app.transfer.transfers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.transfer.R
import com.app.transfer.isListEmpty
import com.app.uikit.views.AutoResizedText

//private var isSelected by mutableStateOf<TransferCountSummaryResponseItem?>(null)

@Composable
fun headerFilters(
    transferHeaderList: MutableList<TransferCountSummaryResponseItem>,
    onFilterClick: (String) -> Unit
) {

    if (!isListEmpty.value) {

        LazyRow {

            item {

                transferHeaderList.forEachIndexed { index, menu ->

                    Row(
                        Modifier.fillMaxWidth()
                    ) {


                        TransferMenuItemView(menu = menu) { status ->

                            var filter = ""

                            when (status) {
                                "For my signing" -> {
                                    filter = "PENDING_SIGNER"
                                }

                                "Executed" -> {
                                    filter = "CLOSED"
                                }

                                "Sign and confirmation" -> {
                                    filter = "PENDING_ALL"
                                }

                                "Sent to the bank" -> {
                                    filter = "BANK_SUCCESS"
                                }

                                "Not processed" -> {
                                    filter = "BANK_ERROR"
                                }

                                "Deleted" -> {
                                    filter = "DELETED"
                                }

                                "Rejected" -> {
                                    filter = "BANK_REJECTED"
                                }

                                "For confirmation" -> {
                                    filter = "PENDING_APPROVER"
                                }

                                "Expired" -> {
                                    filter = "EXPIRED"
                                }

                                "In process" -> {
                                    filter = "SEND_TO_BANK"
                                }

                                else -> {
                                    filter = ""
                                }
                            }

                            onFilterClick(filter)
                        }

                        Spacer(modifier = Modifier.size(width = 8.dp, height = 1.dp))

                    }
                }
            }
        }
    }

}

@Composable
private fun TransferMenuItemView(
    menu: TransferCountSummaryResponseItem,
    onFilterClick: (String) -> Unit
) {

    var status = ""
    var color = Color(0xff268ED9)

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

        "EDITED" -> {
            status = "In process"
            color = Color(0xFF009688)
        }

        else -> {
            Log.e("mmmTAG", "${menu.status}")
        }
    }

    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .background(
//                if (isSelected == menu) Color(0xFFE7EEFC) else
                Color.White,
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        Row(
            Modifier.clickable {
                onFilterClick(status)
//                isSelected = menu
            },
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = status,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.4.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF223142),
                )
            )
            AutoResizedText(
                text = "${menu.count}", modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = color, radius = 12.dp.toPx()
                        )
                    },
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.4.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(400)
                ), color = Color.White
            )

        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun MenuPreview() {
//    TransferTopMenu(transferHeaderList)
}