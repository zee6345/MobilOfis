package com.app.home.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
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
import com.app.home.BottomNavItem
import com.app.home.R
import com.app.home.main.model.Search
import com.app.home.main.model.SearchBy
import com.app.home.main.recents.RecentOps
import com.app.home.main.recents.recentToDetails
import com.app.home.main.subviews.TabLayoutMenu
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetCustomerBalanceItem
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.GetUserRoles
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.transfers.endDateSelected
import com.app.transfer.transfers.filterByStatus
import com.app.transfer.transfers.fromMain
import com.app.transfer.transfers.startDateSelected
import com.app.uikit.bottomSheet.SelectCompanyBottomSheet
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.UserRoles
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.HeaderFilters
import com.app.uikit.views.userRole
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


private const val TAG = "MenuScreen"


val recentDetail = mutableStateOf<GetRecentOpsItem?>(null)
var isShowBalance = mutableStateOf(false)

val enableSearch = mutableStateOf(false)
val disableSearch = mutableStateOf(false)
val searchIban = mutableStateOf("")
val searchUser = mutableStateOf("")
val searchContract = mutableStateOf("")
val searchDepositName = mutableStateOf("")
val searchAccountNo = mutableStateOf("")
val search = mutableStateOf("")

val searchFrom = mutableStateOf<Search?>(null)
val searchBy = mutableStateOf<SearchBy?>(null)


@SuppressLint("FlowOperatorInvokedInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val selectCompanyState = remember { mutableStateOf(false) }

    val balancePopup = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(false) }
    val customerBalanceType = remember { mutableStateOf("Balance") }
    val balance = remember { mutableStateOf("0.0") }
    val symbol = remember { mutableStateOf("") }
    val customerName = remember { mutableStateOf("") }

    val sheet = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheet)

    val customerBalance = remember { mutableStateListOf<GetCustomerBalanceItem>() }
    val transferHeaderList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
    val recentData = remember { mutableListOf<GetRecentOpsItem>() }

    val accountBalance by viewModel.accountBalance.collectAsState()
    val setCustomerName by viewModel.setCustomerName.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState()
    val recentOps by viewModel.recentOps.collectAsState()
    val userRoles by viewModel.getUserRoles.collectAsState()
    val businessDates by viewModel.businessDate.collectAsState()

    //set initial dates
    val currentDate = System.currentTimeMillis()
    val formattedDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)


    val oneWeekLaterDate = Date(currentDate - 15 * 24 * 60 * 60 * 1000)
    val dateWith15EarlyFromCurrent = SimpleDateFormat("dd.MM.yyyy").format(oneWeekLaterDate)

    //set date 15 days early for main
    startDateSelected = remember { mutableStateOf(dateWith15EarlyFromCurrent) }
    endDateSelected = remember { mutableStateOf(formattedDate) }

    val coroutine = rememberCoroutineScope()

    val selectedBoxIndex = remember { mutableStateOf(0) }


    val context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    //set default company name
    customerName.value = userDetails.customerName
    viewModel.session.put("customer", customerName.value)

    handleUserRoles(userDetails.role)

    //search params
    handleSearchParams()


    //initial API calls
    LaunchedEffect(Unit) {

        //add check
        fromMain.value = false

        //set status
        filterByStatus.value = ""

        coroutine.launch {

            viewModel.getBusinessDate()

            viewModel.getBalance(userDetails.customerNo)
            viewModel.getRecentOps(userDetails.customerNo, "")

            //user roles
            viewModel.getUserRoles()
        }

    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = if (isEmpty.value) 0.sdp else 45.sdp,
        modifier = Modifier.padding(bottom = 40.sdp),
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetContent = {

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        if (scaffoldState.bottomSheetState.isExpanded) painterResource(id = R.drawable.ic_option_down) else painterResource(
                            id = R.drawable.ic_options_up
                        ),
                        modifier = Modifier
                            .size(width = 24.sdp, height = 24.sdp)
                            .padding(3.dp)
                            .clickable {
                                coroutine.launch {
                                    if (scaffoldState.bottomSheetState.isExpanded) {
                                        scaffoldState.bottomSheetState.collapse()
                                    } else {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            },
                        tint = colorResource(R.color.background_card_blue),
                        contentDescription = ""
                    )
                }


                Text(
                    text = "Recent operations on accounts",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.sdp),
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.sdp, bottom = 10.sdp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val sumDR = recentData.filter { it.debit_credit_flag == "DR" }
                        .sumByDouble { it.amount?.toDoubleOrNull() ?: 0.0 }

                    val sumCR = recentData.filter { it.debit_credit_flag == "CR" }
                        .sumByDouble { it.amount?.toDoubleOrNull() ?: 0.0 }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painterResource(id = R.drawable.ic_options_arrow_in),
                            modifier = Modifier.size(width = 18.sdp, height = 8.sdp),
                            tint = colorResource(R.color.background_card_blue),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                        )

                        Text(
                            text = "${Utils.formatAmount(sumDR!!)}",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 18.4.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF203657),
                                textAlign = TextAlign.Right,
                            )
                        )

                    }

                    Spacer(
                        modifier = Modifier.size(width = 20.sdp, height = 1.sdp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painterResource(id = R.drawable.ic_options_arrow_out),
                            modifier = Modifier.size(width = 18.sdp, height = 8.sdp),
                            tint = Color(0xFFFF4E57),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                        )


                        Text(
                            text = if (sumCR == 0.0) "${Utils.formatAmount(sumCR)}" else "- ${
                                Utils.formatAmount(
                                    sumCR!!
                                )
                            }",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 18.4.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFFFF4E57),
                                textAlign = TextAlign.Right,
                            )
                        )

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp, vertical = 5.sdp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 6.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(
                                    if (selectedBoxIndex.value == 0) Color(0xFFE7EEFC) else Color.White,
                                )
                                .clickable {
                                    selectedBoxIndex.value = 0

                                    viewModel.getRecentOps(userDetails.customerNo, "")

                                }) {
                                Text(
                                    text = "All",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 0) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 6.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(if (selectedBoxIndex.value == 1) Color(0xFFE7EEFC) else Color.White)
                                .clickable {
                                    selectedBoxIndex.value = 1

                                    viewModel.getRecentOps(userDetails.customerNo, "CR")

                                }) {
                                Text(
                                    text = "Income",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 1) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }


                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 6.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(
                                    if (selectedBoxIndex.value == 2) Color(0xFFE7EEFC) else Color.White
                                )
                                .clickable {
                                    selectedBoxIndex.value = 2

                                    viewModel.getRecentOps(userDetails.customerNo, "DR")

                                }) {
                                Text(
                                    text = "Expenditure",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 2) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }

                        Box(
                            Modifier
                                .padding(start = 10.dp)
                                .clickable {
//                                    navController.navigate(recentTransactions)
                                    val recentOpsDetails = Intent(context, RecentOps::class.java)
                                    recentOpsDetails.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    context.startActivity(recentOpsDetails)

                                }

                        ) {
                            Text(
                                text = "More"
                            )
                        }

                    }



                    Box(
                        Modifier.clickable {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getRecentOps(userDetails.customerNo, "")
                            }
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_refresh),
                            tint = colorResource(R.color.background_card_blue),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(20.dp)
                        )
                    }
                }

                LazyColumn {

                    if (isEmpty.value) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                contentAlignment = Center
                            ) {
                                Text(text = "No recent transaction found!")
                            }
                        }

                    } else {
                        recentData.forEachIndexed { index, getRecentOpsItem ->
                            item {
                                CardsItem(data = getRecentOpsItem!!, navController = navController)
                            }
                        }
                    }


                    item {
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
            }


        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF3F7FA))
        ) {
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                    .fillMaxWidth()
                    .weight(0.23f),
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (enableSearch.value) {

                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF203657)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 15.dp,
                                        end = 15.dp,
                                        bottom = 15.dp,
                                        top = 20.dp
                                    )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = "Home",
                                        style = TextStyle(color = Color.White, fontSize = 18.sp),
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(horizontal = 8.dp)
                                    )

                                    Icon(
                                        painter = painterResource(id = com.app.transfer.R.drawable.ic_tansfer_search),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp)
                                            .clickable {
                                                enableSearch.value = false
                                            }
                                    )

                                }


                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                            ) {
                                OutlinedTextField(
                                    value = search.value,
                                    onValueChange = {
                                        search.value = it
                                        disableSearch.value = it.isEmpty()
                                    },
                                    label = {
                                        androidx.compose.material3.Text(
                                            text = "Search",
                                            fontSize = 14.sp
                                        )
                                    },
                                    trailingIcon = {
                                        androidx.compose.material3.Icon(
                                            painter = if (!disableSearch.value) painterResource(id = com.app.transfer.R.drawable.ic_transfer_close) else painterResource(
                                                id = com.app.transfer.R.drawable.ic_tansfer_search
                                            ),
                                            contentDescription = null,
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp)
                                                .clickable {
                                                    enableSearch.value = false

                                                    //reset search filter
                                                    search.value = ""
                                                }
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        backgroundColor = Color.White,
                                        focusedBorderColor = colorResource(com.app.transfer.R.color.background_card_blue),
                                        unfocusedBorderColor = colorResource(com.app.transfer.R.color.border_grey),
                                        unfocusedLabelColor = colorResource(com.app.transfer.R.color.grey_text),
                                        focusedLabelColor = colorResource(com.app.transfer.R.color.background_card_blue)
                                    ),
                                    singleLine = true,
                                    shape = RoundedCornerShape(8.dp),
                                    textStyle = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(com.app.transfer.R.font.roboto_regular)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }

                        }


                    } else {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(color = Color(0xFF203657).copy(alpha = 0.9f)),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                            ) {


                                Row(
                                    modifier = Modifier
                                        .weight(0.6f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_business_icon),
                                        modifier = Modifier
                                            .size(26.dp)
                                            .padding(1.dp)
                                            .align(Top)
                                            .clickable {
                                                coroutine.launch {
                                                    selectCompanyState.value = true
                                                }
                                            }
                                            .weight(0.2f),
                                        contentDescription = "",
                                        tint = Color.White
                                    )

                                    Column(
                                        Modifier
                                            .padding(start = 5.dp)
                                            .weight(1f)
                                    ) {

                                        Text(
                                            modifier = Modifier.clickable {
                                                coroutine.launch {
                                                    selectCompanyState.value = true
                                                }
                                            },
                                            text = customerName.value,
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 14.sp
                                            ),
                                            maxLines = 2,
                                        )

                                        Text(
                                            text = "${userDetails.userName}",
                                            style = TextStyle(
                                                color = Color.White.copy(alpha = 0.5f),
                                                fontSize = 14.sp
                                            ),
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            modifier = Modifier.padding(top = 5.dp)
                                        )
                                    }


                                    Image(
                                        painter = painterResource(id = R.drawable.ic_business_expand),
                                        modifier = Modifier
                                            .size(16.dp)
                                            .align(Top)
                                            .clickable {
                                                coroutine.launch {
                                                    selectCompanyState.value = true
                                                }
                                            },
                                        contentDescription = "",

                                        )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.4f)
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Top
                                ) {
                                    Icon(
//                                    painter = if (showBalance.value) painterResource(id = com.app.transfer.R.drawable.ic_pswd_visible) else painterResource(id = com.app.transfer.R.drawable.ic_pswd_visible),
                                        painter = painterResource(id = com.app.transfer.R.drawable.ic_pswd_visible),
                                        modifier = Modifier
                                            .size(22.dp)
                                            .align(Top)
                                            .clickable {
                                                isShowBalance.value = !isShowBalance.value
                                            },
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_option_icon),
                                        modifier = Modifier
                                            .size(22.dp)
                                            .align(Top),
                                        contentDescription = ""
                                    )
                                }

                            }

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(color = Color(0xFF203657)),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                Modifier.padding(horizontal = 22.dp),
                                verticalArrangement = Arrangement.Center

                            ) {
                                val annotatedString = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            Color.White.copy(0.5f),
                                            fontSize = 14.sp
                                        )
                                    ) {
                                        append(stringResource(R.string.total_accounts) + " ")
                                    }
                                    withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)) {
                                        append(customerBalanceType.value)
                                    }
                                }

                                Row() {
                                    Text(
                                        text = annotatedString,
                                        modifier = Modifier
                                            .align(Bottom)
                                            .padding(end = 8.dp, top = 3.dp)
                                            .clickable {
                                                balancePopup.value = !balancePopup.value
                                            }
                                    )
                                    Box(
                                        Modifier.align(Bottom)
                                    ) {

                                        Image(
                                            painter = painterResource(id = R.drawable.ic_business_expand),
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clickable {
                                                    balancePopup.value = !balancePopup.value
                                                },
                                            contentDescription = ""
                                        )

                                        if (customerBalance.isNotEmpty()) {
                                            DropDownPopup(expanded = balancePopup) {
                                                when (it) {
                                                    Balance.BLOCKED -> {
                                                        balance.value =
                                                            customerBalance[0].BLOCK_BALANCE
                                                        customerBalanceType.value = "Blocked"
                                                    }

                                                    Balance.FREE_BALANCE -> {
                                                        balance.value =
                                                            customerBalance[0].AVAIL_BALANCE
                                                        customerBalanceType.value = "Free Balance"
                                                    }

                                                    Balance.BANK_EXECUTION -> {
                                                        balance.value =
                                                            customerBalance[0].WAITING_BALANCE
                                                        customerBalanceType.value = "Bank Execution"
                                                    }

                                                    Balance.AFTER_EXECUTION -> {
                                                        balance.value =
                                                            customerBalance[0].AFTERAPPROVE_BALANCE
                                                        customerBalanceType.value =
                                                            "After Execution"
                                                    }

                                                    Balance.BALANCE -> {
                                                        balance.value = customerBalance[0].BALANCE
                                                        customerBalanceType.value = "Balance"
                                                    }

                                                }
                                            }
                                        }


                                    }


                                }


                                Row {
                                    Text(
                                        text = if (isShowBalance.value) "****" else "${
                                            Utils.formatAmountWithSpaces(
                                                balance.value.toDouble()
                                            )
                                        } ${symbol.value}",
                                        modifier = Modifier.align(Top),
                                        style = TextStyle(
                                            fontSize = 32.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = Color.White
                                    )

                                }

                            }
                        }

                    }

                }

            }

            Column(
                modifier = Modifier
                    .weight(0.8f)
            ) {

                Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

                if (transferHeaderList.isNotEmpty()) {
                    Box(Modifier.padding(horizontal = 10.dp)) {
                        HeaderFilters(transferHeaderList) {
                            //add check
                            fromMain.value = true

                            //set status
                            filterByStatus.value = it

                            //route to transfers
                            navController.navigate(BottomNavItem.Transfers.screen_route)

                        }
                    }
                }

                TabLayoutMenu(navController)

            }


        }

    }


    SelectCompanyBottomSheet(selectCompanyState, userDetails.customers) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.setCustomerName(ChangeCompanyName(it))

            delay(1000)

            viewModel.setCustomerName(ChangeCompanyName(it))
        }
    }

    if (customerBalance.isNotEmpty() && customerBalance != null) {
        setDefaultBalanceValue(customerBalanceType, balance, customerBalance)
    }


    //handle API Response
    accountBalance?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {

                isLoading.value = false

                val data = it.data as GetCustomerBalance

                if (data.isNotEmpty()) {
                    customerBalance?.apply {
                        clear()
                        addAll(data)
                    }

                    symbol.value = Utils.formatCurrency(customerBalance[0].CCY_NAME)
                }
            }

            else -> {}
        }

    }

    setCustomerName?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {

                isLoading.value = false


                val data = it.data as LoginVerifyResponse
                data?.apply {
                    val str = Converter.toJson(this)
                    viewModel.session.put(Keys.KEY_USER_DETAILS, str)

                    customerName.value = this.customerName
                    viewModel.session.put("customer", this.customerName)


                    LaunchedEffect(Unit) {

                        handleUserRoles(role)


                        //main
                        viewModel.getBalance(userDetails.customerNo)
                        viewModel.getRecentOps(userDetails.customerNo, "")

                        //accounts
                        viewModel.getAccounts(userDetails.customerNo)

                        //cards
                        viewModel.getOldBusinessCards(userDetails.customerNo)
                        viewModel.getNewBusinessCards(userDetails.customerNo)

                        //loans
                        viewModel.getLoans(userDetails.customerNo)

                        //deposits
                        viewModel.getTrusts(userDetails.customerNo)

                        //headers
                        viewModel.getBusinessDate()

                    }

                }


            }

            else -> {}
        }

    }

    transferCountSummery?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as TransferCountSummaryResponse

                transferHeaderList.apply {
                    clear()
                    addAll(data)
                }


            }

            else -> {}
        }
    }

    recentOps?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                //on error hide sheet
                isEmpty.value = true
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as GetRecentOps

                recentData?.apply {
                    clear()
                    addAll(data)
                }


                isEmpty.value = !data.isNotEmpty()


            }

            else -> {}
        }

    }

    userRoles?.let {
        when (it) {
            is DataState.Loading -> {}
            is DataState.Error -> {}
            is DataState.Success -> {
                val data = it.data as GetUserRoles

//                LaunchedEffect(Unit) {
//                    Log.e("mmTAG", "${data.toString()}")
//                }


            }
        }
    }

    businessDates?.let {
        when (it) {
            is DataState.Error -> {}
            is DataState.Loading -> {}
            is DataState.Success -> {
                val dateEnd: String = it.data as String

                val oneWeekLaterDate = Date(currentDate - 15 * 24 * 60 * 60 * 1000)
                val dateWith15EarlyFromCurrent =
                    SimpleDateFormat("dd.MM.yyyy").format(oneWeekLaterDate)

                startDateSelected.value = dateWith15EarlyFromCurrent
                endDateSelected.value = dateEnd

                //initial api call to refresh data
                LaunchedEffect(Unit) {
                    viewModel.getTransferCountSummary(
                        startDateSelected.value,
                        endDateSelected.value
                    )
                }

            }
        }
    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }


}

private fun handleUserRoles(role:Int) {
    when (role) {
        1 -> {
            userRole.value = UserRoles.MAKER
        }

        2 -> {
            userRole.value = UserRoles.SIGNER
        }

        3 -> {
            userRole.value = UserRoles.APPROVER
        }
    }

}

private fun handleSearchParams() {
    when (searchFrom.value) {
        Search.FromCards -> {
            when (searchBy.value) {
                SearchBy.ByIBAN -> {
                    searchIban.value = search.value
                }

                SearchBy.ByUser -> {
                    searchUser.value = search.value
                }

                else -> {
                    search.value = search.value
                }
            }
        }

        Search.FromLoans -> {
            when (searchBy.value) {
                SearchBy.ByAgreement -> {
                    searchContract.value = search.value
                }

                else -> {
                    search.value = search.value
                }
            }
        }

        Search.FromDeposits -> {
            when (searchBy.value) {
                SearchBy.ByDepositName -> {
                    searchDepositName.value = search.value
                }

                SearchBy.ByAccount -> {
                    searchAccountNo.value = search.value
                }

                else -> {
                    search.value = search.value
                }
            }
        }

        else -> {
            search.value
        }
    }
}


@Composable
private fun CardsItem(data: GetRecentOpsItem, navController: NavController) {

    var isCredit by remember { mutableStateOf(false) }

    val symbol = Utils.formatCurrency(data.currency_name)

    isCredit = data.debit_credit_flag != "DR"

    val amount = if (isCredit) {
        "-${data.amount} ${symbol}"
    } else {
        "${data.amount} ${symbol}"
    }


    Card(
        modifier = Modifier
            .padding(vertical = 4.sdp, horizontal = 15.sdp)
            .fillMaxWidth()
            .clickable {
                recentDetail.value = data
                navController.navigate(recentToDetails)
            },
        elevation = 1.dp,
        backgroundColor = Color(0xFFF3F7FA),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "${data.receiver_name}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF203657),
                        textAlign = TextAlign.Left,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {

                AutoResizedText(
                    text = if (isShowBalance.value) "****" else amount,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = if (isCredit) Color(0xFFFF4E57) else Color(0xFF203657),
                        textAlign = TextAlign.Right,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.sdp)
                        .fillMaxWidth()
                )


                Text(
                    Utils.convertToHourMinute(data.trn_time),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.1.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(400),
                        color = colorResource(R.color.grey_text),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.sdp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

fun setDefaultBalanceValue(
    customerBalanceType: MutableState<String>,
    balance: MutableState<String>,
    customerBalance: SnapshotStateList<GetCustomerBalanceItem>
) {
    customerBalance?.let {

        when (customerBalanceType.value) {
            "Blocked" -> {
                balance.value = customerBalance[0].BLOCK_BALANCE
            }

            "Free Balance" -> {
                balance.value = customerBalance[0].AVAIL_BALANCE
            }

            "Bank Execution" -> {
                balance.value = customerBalance[0].WAITING_BALANCE
            }

            "After Execution" -> {
                balance.value = customerBalance[0].AFTERAPPROVE_BALANCE
            }

            "Balance" -> {
                balance.value = customerBalance[0].BALANCE
            }

        }
    }
}

@Composable
fun DropDownPopup(
    expanded: MutableState<Boolean>,
    selectedString: (type: Balance) -> Unit
) {

    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))) {

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {

            DropdownMenuItem(
                content = { Text(stringResource(R.string.balance)) },
                onClick = {
                    selectedString(Balance.BALANCE)
                    expanded.value = false
                }
            )
            Divider(
                color = Color(0xFFE7EEFC)
            )

            DropdownMenuItem(
                content = { Text(stringResource(R.string.blocked)) },
                onClick = {
                    selectedString(Balance.BLOCKED)
                    expanded.value = false
                }
            )
            Divider(color = Color(0xFFE7EEFC))
            DropdownMenuItem(
                content = { Text(stringResource(R.string.free_balance)) },
                onClick = {
                    selectedString(Balance.FREE_BALANCE)
                    expanded.value = false
                }
            )
            Divider(color = Color(0xFFE7EEFC))
            DropdownMenuItem(
                content = { Text(stringResource(R.string.bank_execution)) },
                onClick = {
                    selectedString(Balance.BANK_EXECUTION)
                    expanded.value = false
                }
            )
            Divider(color = Color(0xFFE7EEFC))
            DropdownMenuItem(
                content = { Text(stringResource(R.string.after_execution)) },
                onClick = {
                    selectedString(Balance.AFTER_EXECUTION)
                    expanded.value = false
                }
            )
        }

    }

}

enum class Balance {
    BALANCE,
    BLOCKED,
    FREE_BALANCE,
    BANK_EXECUTION,
    AFTER_EXECUTION
}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MenuScreen(rememberNavController())
}