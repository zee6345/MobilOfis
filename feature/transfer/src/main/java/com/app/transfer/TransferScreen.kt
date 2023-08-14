package com.app.transfer

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.network.models.responseModels.transferModels.TransferListResponse
import com.app.network.models.responseModels.transferModels.TransferListResponseItem
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
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.FiltersTopRow
import com.app.uikit.views.ItemClickedCallback
import ir.kaaveh.sdpcompose.sdp

private lateinit var showDateBottomSheet: MutableState<Boolean>
private lateinit var showFromAccountBottomSheet: MutableState<Boolean>
private lateinit var showStatusBottomSheet: MutableState<Boolean>
private lateinit var showTypeBottomSheet: MutableState<Boolean>
private lateinit var showAmountBottomSheet: MutableState<Boolean>
private lateinit var showCurrencyBottomSheet: MutableState<Boolean>
private lateinit var startDateSelected: MutableState<String>
private lateinit var endDateSelected: MutableState<String>
//private lateinit var transferListResponse: MutableList<TransferListResponse>

@Composable
fun TransferScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }

    val isLoading = remember { mutableStateOf(false) }

    val accountFilterList = remember { mutableListOf<GetAccountsItem>() }
    val transferHeaderList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
    val transferListResponse = remember { mutableListOf<TransferListResponseItem>() }

    startDateSelected = rememberSaveable { mutableStateOf("01-01-2019") }
    endDateSelected = rememberSaveable { mutableStateOf("01-01-2023") }


    val context: Context = LocalContext.current
    val businessDates by viewModel.businessDate.collectAsState()
    val accountsList by viewModel.accountsData.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState()
    val transferList by viewModel.transferList.collectAsState()

    //fetch data
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

//    val currentDate = System.currentTimeMillis()
//    val formattedDate = SimpleDateFormat("dd.mm.yyyy").format(currentDate)
//    val dateStart = "01.01.2019"
//    val endDate = "01.01.2023"


    LaunchedEffect(Unit) {
        viewModel.getBusinessDate()
        viewModel.getAccounts(userDetails.customerNo)
        viewModel.getTransferCountSummary(startDateSelected.value, endDateSelected.value)
        viewModel.getTransferList(startDateSelected.value, endDateSelected.value, 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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

        ) {

            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {

                TransferTopMenu(transferHeaderList)

                FilterListMenu()

                LazyColumn(

                ) {
                    items(items = transferListResponse, itemContent = {
                        TransactionHistory(navController, it)
                    })
                }

                Box(modifier = Modifier.size(height = 50.sdp, width = 1.sdp))
            }
        }

    }

    DateBottomSheet(showDateBottomSheet, onDateStartSelected = {
        startDateSelected.value = it
    }, onDateEndSelected = {
        endDateSelected.value = it
    })

    AccountBottomSheet(showFromAccountBottomSheet, accountFilterList){
        //on account click
        showFromAccountBottomSheet.value = false


    }

    StatusBottomSheet(showStatusBottomSheet)

    TypeBottomSheet(showTypeBottomSheet){
        //on type click
        showTypeBottomSheet.value = false


    }

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
                endDateSelected.value = dateEnd
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


                accountFilterList?.apply {
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
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                isLoading.value = false

                val transferData = it.data as TransferListResponse
                transferListResponse?.apply {
                    clear()
                    addAll(transferData)
                }


            }
        }
    }


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
fun TransactionHistory(
    navController: NavController,
    transfer: TransferListResponseItem
) {
    var status = ""
    var color: Color? = null

    when (transfer.status) {
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
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp)
            .clickable {

                SharedModel.init().ibankRef.value = transfer.ibankRef

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
                    text = "${transfer.benefName}",
                    style = TextStyle(
                        fontSize = 14.sp
                    )

                )
                Text(
                    text = "${transfer.amount}",
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
                            text = "${transfer.brTrnType}", style = TextStyle(
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
                            text = "${transfer.trnDateTime}", style = TextStyle(
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
                                color = color!!, radius = 5.dp.toPx()
                            )
                        }
                        .align(Alignment.CenterVertically)) {

                    }


                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                    Text(
                        text = status,
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
//    TransferScreen(rememberNavController())
}