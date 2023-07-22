package com.app.transfer


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.transfer.components.AccountBottomSheet
import com.app.transfer.components.AmountBottomSheet
import com.app.transfer.components.CurrencyBottomSheet
import com.app.transfer.components.DateBottomSheet
import com.app.transfer.components.StatusBottomSheet
import com.app.transfer.components.TypeBottomSheet
import com.app.transfer.transfers.FiltersTopRow
import com.app.transfer.transfers.ItemClickedCallback
import com.app.transfer.transfers.TransferTopMenu
import com.app.transfer.transfers.navigation.transferToDetails
import ir.kaaveh.sdpcompose.sdp

lateinit var showDateBottomSheet: MutableState<Boolean>
lateinit var showFromAccountBottomSheet: MutableState<Boolean>
lateinit var showStatusBottomSheet: MutableState<Boolean>
lateinit var showTypeBottomSheet: MutableState<Boolean>
lateinit var showAmountBottomSheet: MutableState<Boolean>
lateinit var showCurrencyBottomSheet: MutableState<Boolean>

@Composable
fun TransferScreen(navController: NavController) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState(), enabled = true
            )
            .background(color = Color(0xFFF3F7FA))
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
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(width = 32.dp, height = 25.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentDescription = ""
                )
                Text(
                    text = "Transfers",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }


        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 10.dp)
                .verticalScroll(
                    rememberScrollState(), enabled = true
                ),

            ) {

            TopMenuItem()

            FilterListMenu()

            repeat(5) {
                TransactionHistory(navController)
            }


        }

    }

    DateBottomSheet(showDateBottomSheet)
    AccountBottomSheet(showFromAccountBottomSheet)
    StatusBottomSheet(showStatusBottomSheet)
    TypeBottomSheet(showTypeBottomSheet)
    AmountBottomSheet(showAmountBottomSheet)
    CurrencyBottomSheet(showCurrencyBottomSheet)

}

@Composable
fun TopMenuItem() {
    TransferTopMenu()
}

@Composable
fun FilterListMenu() {
    FiltersTopRow(object : ItemClickedCallback {
        override fun itemClicked(id: String) {
            if (id.equals("date", true)) {
                showDateBottomSheet.value = !showDateBottomSheet.value
            } else if (id.equals("type", true)) {
                showTypeBottomSheet.value = !showTypeBottomSheet.value
            } else if (id.equals("account", true)) {
                showFromAccountBottomSheet.value = !showFromAccountBottomSheet.value
            } else if (id.equals("amount", true)) {
                showAmountBottomSheet.value = !showAmountBottomSheet.value
            } else if (id.equals("currency", true)) {
                showCurrencyBottomSheet.value = !showCurrencyBottomSheet.value
            } else if (id.equals("status", true)) {
                showStatusBottomSheet.value = !showStatusBottomSheet.value

            }
        }
    })
}

@Composable
fun TransactionHistory(navController: NavController) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp)
            .clickable {
                navController.navigate(transferToDetails)
            },
        backgroundColor = Color.White
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.sdp, vertical = 5.sdp)

        ) {


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GARABAG REVIVAL FUND",
                    style = TextStyle(
                        fontSize = 14.sp
                    )

                )
                Text(
                    text = "999 000 000.00 ₼",
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )

            }

            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {
                    Box(
                        Modifier
                            .background(
                                Color(0xFFE7EEFC), shape = RoundedCornerShape(size = 6.dp)
                            )
                            .padding(vertical = 1.sdp, horizontal = 6.sdp)
                    ) {
                        Text(
                            text = "AniPay - Non-budget", style = TextStyle(
                                color = Color(0xFF859DB5),
                                fontSize = 12.sp

                            )
                        )
                    }

                    Spacer(
                        Modifier.size(width = 5.dp, height = 1.dp)
                    )

                    Box(
                        Modifier
                            .background(
                                Color(0xFFE7EEFC), shape = RoundedCornerShape(size = 6.dp)
                            )
                            .padding(vertical = 1.sdp, horizontal = 6.sdp)
                    ) {
                        Text(
                            text = "18:24", style = TextStyle(
                                color = Color(0xFF859DB5),
                                fontSize = 12.sp

                            )
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(modifier = Modifier
                        .padding(2.dp)
                        .width(10.dp)
                        .height(10.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color(0xFF0FBF1B), radius = 5.dp.toPx()
                            )
                        }
                        .align(Alignment.CenterVertically)) {

                    }


                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                    Text(
                        text = "Execution done",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )


                }


            }


        }


    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TransferScreenPreview() {
    TransferScreen(rememberNavController())
}