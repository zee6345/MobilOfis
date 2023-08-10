package com.app.transfer

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.network.models.responseModels.transferModels.TransferListResponse
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.transfers.TransferTopMenu
import com.app.transfer.transfers.navigation.transferToDetails
import com.app.uikit.bottomSheet.AccountBottomSheet
import com.app.uikit.bottomSheet.AmountBottomSheet
import com.app.uikit.bottomSheet.CurrencyBottomSheet
import com.app.uikit.bottomSheet.DateBottomSheet
import com.app.uikit.bottomSheet.StatusBottomSheet
import com.app.uikit.bottomSheet.TypeBottomSheet
import com.app.uikit.views.FiltersTopRow
import com.app.uikit.views.ItemClickedCallback
import ir.kaaveh.sdpcompose.sdp

private lateinit var showDateBottomSheet: MutableState<Boolean>
private lateinit var showFromAccountBottomSheet: MutableState<Boolean>
private lateinit var showStatusBottomSheet: MutableState<Boolean>
private lateinit var showTypeBottomSheet: MutableState<Boolean>
private lateinit var showAmountBottomSheet: MutableState<Boolean>
private lateinit var showCurrencyBottomSheet: MutableState<Boolean>

private lateinit var startDateSelected:MutableState<String>
private lateinit var endDateSelected:MutableState<String>
private lateinit var transferListResponse:MutableList<TransferListResponse>

@Composable
fun TransferScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }
    val accountFilterList = remember { mutableListOf<GetAccountsItem>() }
    val transferHeaderList = remember { mutableListOf<TransferCountSummaryResponseItem>() }

    startDateSelected = rememberSaveable { mutableStateOf("01-01-2019") }
    endDateSelected = rememberSaveable{ mutableStateOf("01-01-2023") }
    transferListResponse = rememberSaveable { mutableStateListOf() }


    val context: Context = LocalContext.current
    val businessDates by viewModel.businessDate.collectAsState()
    val accountsList by viewModel.accountsData.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState()
    val transferList by viewModel.transferList.collectAsState()


    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

//    val currentDate = System.currentTimeMillis()
//    val formattedDate = SimpleDateFormat("dd.mm.yyyy").format(currentDate)
    val dateStart = "01.01.2019"
    val endDate = "01.01.2023"


    LaunchedEffect(Unit) {
        viewModel.getBusinessDate()
        viewModel.getAccounts(userDetails.customerNo)
        viewModel.getTransferCountSummary(dateStart, endDate)
        viewModel.getTransferList(startDateSelected.value, endDateSelected.value, 0)
    }

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
                    text = stringResource(R.string.transfers),
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

            TransferTopMenu(transferHeaderList)

            FilterListMenu()

            repeat(5) {
                TransactionHistory(navController)
            }


        }

    }

    DateBottomSheet(showDateBottomSheet, onDateStartSelected = {
         startDateSelected.value = it
    }, onDateEndSelected = {
        endDateSelected.value =it
    })
    AccountBottomSheet(showFromAccountBottomSheet, accountFilterList)
    StatusBottomSheet(showStatusBottomSheet)
    TypeBottomSheet(showTypeBottomSheet)
    AmountBottomSheet(showAmountBottomSheet)
    CurrencyBottomSheet(showCurrencyBottomSheet)



    businessDates?.let {
        when (it) {
            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Loading -> {

            }

            is DataState.Success -> {

                val dateEnd: String = it.data as String
                val dateStart = "01.01.2019"
                val page = 0

                endDateSelected.value =dateEnd
//
//                viewModel.getTransferCountSummary(dateStart, dateEnd)
//                viewModel.getAccounts(userDetails.customerNo)
            }
        }
    }


    accountsList?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val userAccounts = it.data as GetAccounts


                accountFilterList.apply {
                    clear()
                    accountFilterList.addAll(userAccounts)
                }

            }
        }
    }


    transferCountSummery?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val data = it.data as TransferCountSummaryResponse

                transferHeaderList.apply {
                    clear()
                    addAll(data)
                }

            }
        }
    }

    transferList?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val transferData = it.data as TransferListResponse
                transferListResponse.apply {
                    transferListResponse.clear()
                    transferListResponse.addAll(listOf(transferData))
                }


            }
        }
    }





}

//@Composable
//fun TopMenuItem() {
//
//}

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
                    text = stringResource(R.string.garabag_revival_fund),
                    style = TextStyle(
                        fontSize = 14.sp
                    )

                )
                Text(
                    text = stringResource(R.string._999_000_000_00),
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
                                colorResource(R.color.border_grey),
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .padding(vertical = 1.sdp, horizontal = 6.sdp)
                    ) {
                        Text(
                            text = stringResource(R.string.anipay_non_budget), style = TextStyle(
                                color = colorResource(R.color.grey_text),
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
                                colorResource(R.color.border_grey),
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .padding(vertical = 1.sdp, horizontal = 6.sdp)
                    ) {
                        Text(
                            text = stringResource(R.string._18_24), style = TextStyle(
                                color = colorResource(R.color.grey_text),
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
                        text = stringResource(R.string.execution_done),
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