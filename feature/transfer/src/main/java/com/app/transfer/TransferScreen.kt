package com.app.transfer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.AccountsData
import com.app.uikit.models.FilterType
import com.app.uikit.models.SignatureInfo
import com.app.uikit.utils.SharedModel
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.FiltersTopRow
import com.app.uikit.views.tarnsfersAccount
import com.app.uikit.views.tarnsfersCurrency
import com.app.uikit.views.tarnsfersStatus
import com.app.uikit.views.tarnsfersType
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
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

private val filterByStatus = mutableStateOf("")
private val filterByType = mutableStateOf("")
private val filterByAccount = mutableStateOf("")
private val filterByAmount = mutableStateOf("")
private val filterByCurrency = mutableStateOf("")
private val filterBySearch = mutableStateOf("")

@Composable
fun TransferScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    showDateBottomSheet = rememberSaveable { mutableStateOf(false) }
    showFromAccountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showStatusBottomSheet = rememberSaveable { mutableStateOf(false) }
    showTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }
    showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }

//    val searchTransfer = remember { mutableStateOf("") }


    var selectedTransfer by remember { mutableStateOf<TransferListResponseItem?>(null) }

    val coroutine = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(false) }
    val isSigned = remember { mutableStateOf(false) }
    val sendToBank = remember { mutableStateOf(false) }
    val showDateError = remember { mutableStateOf(false) }
    val isSearchEnable = remember { mutableStateOf(false) }
    val closeSearch = remember { mutableStateOf(false) }

    val filterTypeList = remember { mutableListOf<String>() }
    val filterCurrencyList = remember { mutableListOf<String>() }
    val filterStatusList = remember { mutableListOf<String>() }
    val filterAccountList = remember { mutableListOf<AccountsData>() }

    val headerList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
    val transferListResponse = remember { mutableListOf<TransferListResponseItem>() }

    val context: Context = LocalContext.current
    val businessDates by viewModel.businessDate.collectAsState()

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
    var showError by remember { mutableStateOf(false) }


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
                .then(Modifier.wrapContentHeight()),
            color = Color(0xFF203657),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
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

                    if (!isSearchEnable.value) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tansfer_search),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    isSearchEnable.value = true
                                }
                        )
                    }

                }


                if (isSearchEnable.value) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    ) {
                        OutlinedTextField(
                            value = filterBySearch.value,
                            onValueChange = {
                                filterBySearch.value = it

                                closeSearch.value = it.isEmpty()
                            },
                            label = {
                                androidx.compose.material3.Text(
                                    text = "Search",
                                    fontSize = 14.sp
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = if (!closeSearch.value) painterResource(id = R.drawable.ic_transfer_close) else painterResource(
                                        id = R.drawable.ic_tansfer_search
                                    ),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .clickable {
                                            isSearchEnable.value = false

                                            //reset search filter
                                            filterBySearch.value = ""
                                        }
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                focusedBorderColor = colorResource(R.color.background_card_blue),
                                unfocusedBorderColor = colorResource(R.color.border_grey),
                                unfocusedLabelColor = colorResource(R.color.grey_text),
                                focusedLabelColor = colorResource(R.color.background_card_blue)
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF223142),
                            )
                        )
                    }

                }

            }

        }


        Box(
            modifier = Modifier
                .weight(0.8f)
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


                                val filter =
                                    if (
                                        filterByStatus.value.isNotEmpty() or
                                        filterByType.value.isNotEmpty() or
                                        filterByAccount.value.isNotEmpty() or
                                        filterByCurrency.value.isNotEmpty() or
                                        filterByAmount.value.isNotEmpty() or
                                        filterBySearch.value.isNotEmpty()
                                    ) {
                                        items.asSequence()
                                            .filter {
                                                it.status.contains(filterByStatus.value, true)
                                            }.filter {
                                                it.brTrnType.contains(filterByType.value, true)
                                            }.filter {
                                                it.customerAccount.contains(
                                                    filterByAccount.value,
                                                    true
                                                )
                                            }.filter {
                                                it.currency.contains(filterByCurrency.value, true)
                                            }.filter {
                                                it.amount.toString()
                                                    .contains(filterByAmount.value, true)
                                            }.filter {
                                                it.benefName.contains(filterBySearch.value, true)
                                            }
                                            .toList()
                                    } else {
                                        items
                                    }


                                // Display the date header if it's different from the last displayed date
                                if (filter.isNotEmpty()) {
                                    Spacer(
                                        modifier = Modifier.size(
                                            height = 10.sdp,
                                            width = 1.sdp
                                        )
                                    )

                                    DateHeader(date)
                                }


                                filter.forEach { item ->

                                    TransferListItem(
                                        item,
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

    DateBottomSheet(showDateBottomSheet,
        onSelectedDate = {
            startDate = it.startDate
            endDate = it.endDate

            if (startDate > endDate) {
                showError = true
            } else {
                showError = false

                coroutine.launch {
                    viewModel.getTransferCountSummary(startDate, endDate)
                    viewModel.getTransferList(startDate, endDate, 0)
                }
            }

        })

    AccountBottomSheet(showFromAccountBottomSheet, filterAccountList) {
        showFromAccountBottomSheet.value = false

        filterByAccount.value = it.accountNum

        //for filter title
        tarnsfersAccount.value = it.accountNum

    }

    StatusBottomSheet(showStatusBottomSheet, filterStatusList) {
        showStatusBottomSheet.value = false

        filterByStatus.value = it

        //for filter title
        tarnsfersStatus.value = Utils.headerStatus(it).status

    }

    TypeBottomSheet(showTypeBottomSheet, filterTypeList) {
        //on type click
        showTypeBottomSheet.value = false

        filterByType.value = it.prefix

        //show type to filters
        tarnsfersType.value = it.prefix


    }

    AmountBottomSheet(showAmountBottomSheet) {
        filterByAmount.value = ((it.startAmount.toFloat() + it.endAmount.toFloat()) / 2).toString()
    }

    CurrencyBottomSheet(showCurrencyBottomSheet, filterCurrencyList) {

        showCurrencyBottomSheet.value = false

        filterByCurrency.value = it

        //for filter title
        tarnsfersCurrency.value = it

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

    transferCountSummery?.let {
        when (it) {
            is DataState.Loading -> {}
            is DataState.Error -> {}
            is DataState.Success<*> -> {
                val data = it.data as TransferCountSummaryResponse

                if (data.isNotEmpty()) {

                    headerList.apply {
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

    transferList?.let { it ->
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

                //transfer list
                if (transferListResponse.isNotEmpty()) {
                    transferListResponse.clear()
                }
                transferData.forEach {
                    transferListResponse.add(it)
                }


                //fetch trn type list
                if (filterTypeList.isNotEmpty()) {
                    filterTypeList.clear()
                }

                transferData.forEach { type ->
                    filterTypeList.add(type.brTrnType)
                }


                //fetch currency  list
                if (filterCurrencyList.isNotEmpty()) {
                    filterCurrencyList.clear()
                }

                transferData.forEach { type ->
                    filterCurrencyList.add(type.currency)
                }


                //fetch status list
                if (filterStatusList.isNotEmpty()) {
                    filterStatusList.clear()
                }

                transferData.forEach { type ->
                    filterStatusList.add(type.status)
                }

                //fetch account list
                if (filterAccountList.isNotEmpty()) {
                    filterAccountList.clear()
                }

                transferData.forEach { account ->
                    filterAccountList.add(AccountsData(account.customerAccount, account.amount))
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


    if (showError) {
        RoundedCornerToast(
            "The beginning of the period cannot be later than the end",
            Toast.LENGTH_SHORT,
            context
        )

        LaunchedEffect(Unit) {
            delay(3000)
            showError = false
        }
    }
}

@Composable
private fun DateHeader(inputDateTimeString: LocalDate) {
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
                    AutoResizedText(
                        text = Utils.formatAmountWithSpaces(transfer.amount),
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Right
                        ),
                        modifier = Modifier.weight(0.4f)
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

    FiltersTopRow {
        if (it != null) {
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
        } else {
            filterByStatus.value = ""
            filterByType.value = ""
            filterByAccount.value = ""
            filterByCurrency.value = ""
            filterByAmount.value = ""
            filterBySearch.value = ""
        }

    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TransferScreenPreview() {
    TransferScreen(rememberNavController())
}