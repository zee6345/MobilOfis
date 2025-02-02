package com.app.transfer

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import com.app.uikit.models.FilterType
import com.app.uikit.models.SignatureInfo
import com.app.uikit.utils.SharedModel
import com.app.uikit.utils.Utils
import com.app.uikit.views.FiltersTopRow
import ir.kaaveh.sdpcompose.sdp
import java.text.SimpleDateFormat


const val SESSION = "SESSION_EVENTS"

private lateinit var showDateBottomSheet: MutableState<Boolean>
private lateinit var showFromAccountBottomSheet: MutableState<Boolean>
private lateinit var showStatusBottomSheet: MutableState<Boolean>
private lateinit var showTypeBottomSheet: MutableState<Boolean>
private lateinit var showAmountBottomSheet: MutableState<Boolean>
private lateinit var showCurrencyBottomSheet: MutableState<Boolean>

@Composable
fun TransferScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }

    val selectedItem = remember { mutableStateOf<TransferListResponseItem?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val isSigned = remember { mutableStateOf(false) }
    val sendToBank = remember { mutableStateOf(false) }

    val filterByStatus = remember { mutableStateOf("") }
    val filterByType = remember { mutableStateOf("") }
    val filterByAccount = remember { mutableStateOf("") }
    val filterByAmount = remember { mutableStateOf("") }
    val filterByCurrency = remember { mutableStateOf("") }
    val accountFilterList = remember { mutableListOf<GetAccountsItem>() }

    val transferHeaderList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
    val transferListResponse = remember { mutableListOf<TransferListResponseItem>() }


    val isSelected = remember { mutableStateOf(false) }


    val context: Context = LocalContext.current
    val businessDates by viewModel.businessDate.collectAsState()
    val accountsList by viewModel.accountsData.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState()
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
        viewModel.getBusinessDate()
        viewModel.getAccounts(userDetails.customerNo)
        viewModel.getTransferCountSummary(startDate, endDate)
        viewModel.getTransferList(startDate, endDate, 0)
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

            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {

                LazyColumn {

                    transferListResponse?.let { it ->

                        item {
                            Spacer(modifier = Modifier.size(height = 5.sdp, width = 1.sdp))
                            headerFilters(transferHeaderList) { filter ->
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
                        }

                        item {
                            Spacer(modifier = Modifier.size(height = 5.sdp, width = 1.sdp))
                            FilterListMenu()
                        }


                        val groupedItems = it.groupBy { group ->
                            group.trnDateTime
                        }
                        groupedItems.forEach { (date, items) ->
                            item {

                                // Display the items under the date header
                                val filter = items.filter {
                                    it.status.contains(filterByStatus.value, true)
                                }.filter {
                                    it.brTrnType.contains(filterByType.value, true)
                                }.filter {
                                    it.customerAccount.contains(filterByAccount.value, true)
                                }.filter {
                                    it.currency.contains(filterByCurrency.value, true)
                                }.filter {
                                    it.amount.toString().contains(filterByAmount.value, true)
                                }

                                //date header
                                if (filter.isNullOrEmpty()) {
                                    Spacer(
                                        modifier = Modifier.size(
                                            height = 15.sdp,
                                            width = 1.sdp
                                        )
                                    )

                                    // Display the date as a header
                                    DateHeader(date, isSigned.value)
                                }


                                filter.forEach { item ->
                                    isSelected.value = selectedItem.value == item

                                    val formattedTime = Utils.formattedTime(item.trnDateTime)

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        if (isSigned.value) {
                                            Box(
                                                modifier = Modifier.clickable {
                                                    selectedItem.value =
                                                        if (isSelected.value) null else item
                                                }
                                            ) {

                                                val icon =
                                                    if (isSelected.value) painterResource(id = R.drawable.ic_checkbox_check) else painterResource(
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
                                                    SharedModel.init().signatureData.value =
                                                        SignatureInfo(false, item)

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
                                                        text = "${item.benefName}",
                                                        style = TextStyle(fontSize = 14.sp),
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                        modifier = Modifier.weight(1f)
                                                    )
                                                    Text(
                                                        text = "${item.amount}",
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
                                                                text = "${item.brTrnType}",
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
                                                                        item.status
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
                                                            text = Utils.headerStatus(item.status).status,
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
                            }
                        }

                    }

                    item {
                        Box(modifier = Modifier.size(height = 50.sdp, width = 1.sdp))
                    }

                }

                if (isSigned.value) {

                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Button(
                            enabled = isSelected.value,
                            onClick = {
                                if (sendToBank.value) {


                                    viewModel.sendToBankAPI(
                                        SendToBankModel(
                                            listOf(FileDescriptor("${selectedItem.value!!.ibankRef}")),
                                            ""
                                        )
                                    )

                                } else {
                                    SharedModel.init().signatureData.value =
                                        SignatureInfo(true, selectedItem.value!!)
                                    navController.navigate(transferToDetails)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 50.sdp)
                                .background(
                                    if (isSelected.value) Color(0xFF203657) else Color(0xFF203657).copy(
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

    }

    DateBottomSheet(showDateBottomSheet) {
        startDate = it.startDate
        endDate = it.endDate

        viewModel.getTransferCountSummary(startDate, endDate)
        viewModel.getTransferList(startDate, endDate, 0)
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


}

@Composable
fun DateHeader(inputDateTimeString: String, value: Boolean) {

    val formattedDate = Utils.formattedDate(inputDateTimeString)

    Row(

    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "$formattedDate",
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
fun FilterListMenu() {
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

@Preview(device = Devices.PIXEL_4)
@Composable
fun TransferScreenPreview() {
    TransferScreen(rememberNavController())
}