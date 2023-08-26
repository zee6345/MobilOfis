package com.app.transfer

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.app.network.models.requestModels.FileDescriptor
import com.app.network.models.requestModels.SendToBankModel
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.MainCard
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.network.models.responseModels.transferModels.TransferListResponse
import com.app.network.models.responseModels.transferModels.TransferListResponseItem
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.transfers.headerFilters

import com.app.transfer.transfers.navigation.transferToDetails
import com.app.uikit.bottomSheet.AccountBottomSheet
import com.app.uikit.bottomSheet.AmountBottomSheet
import com.app.uikit.bottomSheet.CurrencyBottomSheet
import com.app.uikit.bottomSheet.DateBottomSheet
import com.app.uikit.bottomSheet.StatusBottomSheet
import com.app.uikit.bottomSheet.TypeBottomSheet
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.FilterType
import com.app.uikit.models.SignatureInfo
import com.app.uikit.utils.SharedModel
import com.app.uikit.utils.Utils
import com.app.uikit.views.FiltersTopRow
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


const val SESSION = "SESSION_EVENTS"

private lateinit var showDateBottomSheet: MutableState<Boolean>
private lateinit var showFromAccountBottomSheet: MutableState<Boolean>
private lateinit var showStatusBottomSheet: MutableState<Boolean>
private lateinit var showTypeBottomSheet: MutableState<Boolean>
private lateinit var showAmountBottomSheet: MutableState<Boolean>
private lateinit var showCurrencyBottomSheet: MutableState<Boolean>

var isListEmpty = mutableStateOf(false)

@Composable
fun TransferScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }



    var selectedTransfer by remember { mutableStateOf<TransferListResponseItem?>(null) }

    val coroutine = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(false) }
    val isSigned = remember { mutableStateOf(false) }
    val sendToBank = remember { mutableStateOf(false) }

    val filterByStatus = remember { mutableStateOf("") }
    val filterByType = remember { mutableStateOf("") }
    val filterByAccount = remember { mutableStateOf("") }
    val filterByAmount = remember { mutableStateOf("") }
    val filterByCurrency = remember { mutableStateOf("") }
    val accountFilterList = remember { mutableListOf<GetAccountsItem>() }

    var headerList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
//    val headerList = ArrayList<TransferCountSummaryResponseItem>()
    val transferListResponse = remember { mutableListOf<TransferListResponseItem>() }

    val context: Context = LocalContext.current
    val businessDates by viewModel.businessDate.collectAsState()
    val accountsList by viewModel.accountsData.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState(initial = emptyList<TransferCountSummaryResponseItem>())
    val transferList by viewModel.transferList.collectAsState()
    val getSendToBank by viewModel.sendToBank.collectAsState()


    //fetch data
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    //set initial dates
    val currentDate = System.currentTimeMillis()
    val formattedDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)

    var startDate by remember { mutableStateOf(formattedDate) }
    var endDate by remember { mutableStateOf(formattedDate) }


    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getBusinessDate()
            viewModel.getAccounts(userDetails.customerNo)
            viewModel.getTransferCountSummary(startDate, endDate)
            viewModel.getTransferList(startDate, endDate, 0)
        }
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


        Box(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 10.dp)
        ) {

            Column {

                Spacer(modifier = Modifier.size(height = 5.sdp, width = 1.sdp))

                headerFilters(headerList) { filter ->
                    filterByStatus.value = filter

                    //if filter clicked enable check
                    if (filter.contains("PENDING_SIGNER", true)) {
                        isSigned.value = true

                        SharedModel.init().isForSigning.value = true

                    } else if (filter.contains("PENDING_APPROVER", true)) {
                        isSigned.value = true

                        SharedModel.init().isForSigning.value = false

                    } else if (filter.contains("SEND_TO_BANK", true)) {
                        isSigned.value = true
                        sendToBank.value = true
                    } else {
                        isSigned.value = false
                    }

                }

                Spacer(modifier = Modifier.size(height = 5.sdp, width = 1.sdp))
                FilterListMenu()

                if (!transferListResponse.isNullOrEmpty()) {
                    LazyColumn(
                        modifier = Modifier.padding(bottom = 50.sdp)
                    ) {

                        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                        val groupedItems = transferListResponse.groupBy { transferItem ->
                            val dateTime = formatter.parse(transferItem.trnDateTime)
                            dateTime.query { temporal ->
                                LocalDate.from(temporal)
                            }
                        }

                        groupedItems.forEach { (date, items) ->
                            item {

                                val filter = items.asSequence().filter {
                                    it.status.contains(filterByStatus.value, true)
                                }.filter {
                                    it.brTrnType.contains(filterByType.value, true)
                                }.filter {
                                    it.customerAccount.contains(filterByAccount.value, true)
                                }.filter {
                                    it.currency.contains(filterByCurrency.value, true)
                                }.filter {
                                    it.amount.toString().contains(filterByAmount.value, true)
                                }.toList()

//                                Log.e("mmmmTAG", "$filter")

                                // Display the date header if it's different from the last displayed date
                                if (filter.isNotEmpty()) {
                                    Spacer(
                                        modifier = Modifier.size(
                                            height = 10.sdp,
                                            width = 1.sdp
                                        )
                                    )

                                    DateHeader(date, isSigned.value)
                                }


                                filter.forEach { item ->

                                    TransferListItem(
                                        item,
                                        viewModel,
                                        selectedTransfer,
                                        navController,
                                        isSigned,
                                    ) {
                                        selectedTransfer = it
                                    }


                                }
                            }
                        }

                    }
                } else {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No transfer found!")
                    }
                }
            }

            if (isSigned.value) {

                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        enabled = selectedTransfer != null,
                        onClick = {
                            if (sendToBank.value) {

                                coroutine.launch {

                                    viewModel.sendToBankAPI(
                                        SendToBankModel(
                                            listOf(FileDescriptor("${selectedTransfer!!.ibankRef}")),
                                            ""
                                        )
                                    )
                                }

                            } else {
                                SharedModel.init().signatureData.value =
                                    SignatureInfo(true, selectedTransfer!!)
                                navController.navigate(transferToDetails)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.sdp)
                            .background(
                                if (selectedTransfer != null) Color(0xFF203657) else Color(
                                    0xFF203657
                                ).copy(
                                    0.8f
                                ),
                                RoundedCornerShape(8.dp)
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            if (sendToBank.value) "Send to bank" else "Sign",
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color.White
                        )
                    }
                }

            }

        }

    }

    DateBottomSheet(showDateBottomSheet) {
        startDate = it.startDate
        endDate = it.endDate

        coroutine.launch {
            viewModel.getTransferCountSummary(startDate, endDate)
            viewModel.getTransferList(startDate, endDate, 0)
        }

    }


    AccountBottomSheet(showFromAccountBottomSheet, accountFilterList) {
        //on account click
        showFromAccountBottomSheet.value = false

        filterByAccount.value = it.ACCOUNT_NO
    }

    StatusBottomSheet(showStatusBottomSheet) {
        showStatusBottomSheet.value = false

        filterByStatus.value = it
    }

    TypeBottomSheet(showTypeBottomSheet) {
        //on type click
        showTypeBottomSheet.value = false

        filterByType.value = it.prefix
    }

    AmountBottomSheet(showAmountBottomSheet) {
        filterByAmount.value = ((it.startAmount.toFloat() + it.endAmount.toFloat()) / 2).toString()
    }

    CurrencyBottomSheet(showCurrencyBottomSheet) {
        filterByCurrency.value = it

        showCurrencyBottomSheet.value = false
    }


    businessDates?.let {
        when (it) {
            is DataState.Error -> {}
            is DataState.Loading -> {}
            is DataState.Success -> {
                val dateEnd: String = it.data as String
                endDate = dateEnd
            }
        }
    }

    accountsList?.let {
        when (it) {
            is DataState.Loading -> {}
            is DataState.Error -> {}
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
            is DataState.Loading -> {}
            is DataState.Error -> {}
            is DataState.Success<*> -> {
                val data = it.data as TransferCountSummaryResponse
                if (data.isNotEmpty()) {
                    headerList?.apply {
                        clear()
                        addAll(data)
                    }

                    isListEmpty.value = false
                } else {

                    isListEmpty.value = true
                }

            }

            else -> {}
        }
    }

    transferList?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
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

    getSendToBank?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

            }
        }
    }


    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

}

@Composable
private fun DateHeader(inputDateTimeString: LocalDate, value: Boolean) {
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formattedDate = inputDateTimeString.format(outputFormatter)

    val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val output = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)

    val localDate = LocalDate.parse(formattedDate, inputFormatter)
    val finalDate = localDate.format(output)

    Row(

    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "$finalDate",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF223142),
                    textAlign = TextAlign.Center,
                )
            )
        }

    }


}

@Composable
private fun TransferListItem(
    transfer: TransferListResponseItem,
    viewModel: HomeViewModel,
    selectedTransfer: TransferListResponseItem?,
    navController: NavController,
    isSigned: MutableState<Boolean>,
    onTransferSelected: (TransferListResponseItem) -> Unit
) {

    val formattedTime = Utils.formattedTime(transfer.trnDateTime)


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSigned.value) {
            Box(
                modifier = Modifier.clickable {
                    onTransferSelected(transfer)
                }
            ) {

                val icon =
                    if (transfer == selectedTransfer) painterResource(id = R.drawable.ic_checkbox_check) else painterResource(
                        id = R.drawable.ic_checkbox_uncheck
                    )
                Image(icon, contentDescription = "")
            }
        }

        Spacer(
            modifier = Modifier.size(
                height = 1.sdp,
                width = 5.sdp
            )
        )



        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.sdp)
                .clickable {
                    SharedModel.init().signatureData.value = SignatureInfo(false, transfer)
                    navController.navigate(transferToDetails)
                },
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 5.sdp,
                        vertical = 5.sdp
                    )
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
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${transfer.amount}",
                        style = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.weight(0.2f)
                    )

                }

                Spacer(
                    modifier = Modifier.size(
                        height = 5.dp,
                        width = 1.dp
                    )
                )

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
                                    shape = RoundedCornerShape(
                                        size = 6.dp
                                    )
                                )
                                .padding(
                                    vertical = 1.sdp,
                                    horizontal = 6.sdp
                                )
                        ) {
                            Text(
                                text = "${transfer.brTrnType}",
                                style = TextStyle(
                                    color = colorResource(R.color.grey_text),
                                    fontSize = 12.sp

                                )
                            )
                        }

                        Spacer(
                            Modifier.size(
                                width = 5.dp,
                                height = 1.dp
                            )
                        )

                        Box(
                            Modifier
                                .background(
                                    colorResource(R.color.border_grey),
                                    shape = RoundedCornerShape(
                                        size = 6.dp
                                    )
                                )
                                .padding(
                                    vertical = 1.sdp,
                                    horizontal = 6.sdp
                                )
                        ) {
                            Text(
                                text = "$formattedTime",
                                style = TextStyle(
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
                                    color = Utils.headerStatus(
                                        transfer.status
                                    ).color,
                                    radius = 5.dp.toPx()
                                )
                            }
                            .align(Alignment.CenterVertically)) {

                        }


                        Spacer(
                            modifier = Modifier.size(
                                width = 5.dp,
                                height = 1.dp
                            )
                        )

                        Text(
                            text = Utils.headerStatus(transfer.status).status,
                            style = TextStyle(
                                fontSize = 14.sp
                            )
                        )


                    }


                }
            }
        }
    }
}

@Composable
private fun FilterListMenu() {
    FiltersTopRow() {
        when (it) {
            FilterType.DATE -> {
                showDateBottomSheet.value = !showDateBottomSheet.value
            }

            FilterType.TYPE -> {
                showTypeBottomSheet.value = !showTypeBottomSheet.value
            }

            FilterType.ACCOUNT -> {
                showFromAccountBottomSheet.value = !showFromAccountBottomSheet.value
            }

            FilterType.AMOUNT -> {
                showAmountBottomSheet.value = !showAmountBottomSheet.value
            }

            FilterType.CURRENCY -> {
                showCurrencyBottomSheet.value = !showCurrencyBottomSheet.value
            }

            FilterType.STATUS -> {
                showStatusBottomSheet.value = !showStatusBottomSheet.value
            }

            else -> {

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




    Card(
        modifier = Modifier.padding(vertical = 5.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier.clickable { onFilterClick(status) },
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = status,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 12.sp
            )
            Text(
                text = "${menu.count}", modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = color, radius = 12.dp.toPx()
                        )
                    }, fontSize = 14.sp, color = Color.White
            )

        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TransferScreenPreview() {
    TransferScreen(rememberNavController())
}