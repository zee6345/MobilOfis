package com.app.home.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.component.CardMenuContent
import com.app.home.main.component.SelectCompanyBottomSheet
import com.app.home.main.component.TabLayoutMenu

import com.app.network.data.DataState
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel


private const val TAG = "MenuScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {

    val selectCompanyState = rememberSaveable { mutableStateOf(false) }
    val showBalance = rememberSaveable { mutableStateOf(false) }
    val balancePopup = rememberSaveable { mutableStateOf(false) }
    var touchPoint: Offset by remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    val homeData by rememberUpdatedState(viewModel.data.collectAsState())
    val context = LocalContext.current


    val userDetails = fetchUserDetails()

    LaunchedEffect(key1 = true ){
        //fetch accounts list
        viewModel.getAccounts(
            MainApp.session[Keys.KEY_TOKEN]!!,
            userDetails.customerNo
        )



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
                .weight(0.25f),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {

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
                            .padding(start = 22.dp, end = 12.dp)

                    ) {

                        Row(
                            modifier = Modifier.weight(0.7f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_business_icon),
                                modifier = Modifier
                                    .size(28.dp)
                                    .align(Top)
                                    .clickable {
                                        selectCompanyState.value = !selectCompanyState.value
                                    },
                                contentDescription = "",
                                tint = Color.White
                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(start = 5.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.international_real_estate_and_appraisal_agency),
                                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                                    modifier = Modifier
                                        .padding(start = 3.dp)
                                        .widthIn(max = 140.dp)
                                        .wrapContentWidth(align = Alignment.Start),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = stringResource(R.string.semire), style = TextStyle(
                                        color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp
                                    )
                                )
                            }

                            Image(
                                painter = painterResource(id = R.drawable.ic_business_expand),
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Top)
                                    .clickable {
                                        selectCompanyState.value = !selectCompanyState.value
                                    },
                                contentDescription = ""
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Top
                        ) {
                            Image(
                                painter =
                                if (showBalance.value)
                                    painterResource(id = R.drawable.ic_password_visible_off)
                                else painterResource(id = R.drawable.ic_password_visible),
                                modifier = Modifier
                                    .size(22.dp)
                                    .align(Top)
                                    .clickable {
                                        showBalance.value = !showBalance.value
                                    },
                                contentDescription = ""
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
                        Modifier
                            .padding(horizontal = 22.dp)
                            .pointerInput(Unit) {
                                detectTapGestures {

                                    touchPoint = it
                                    balancePopup.value = !balancePopup.value
                                }

                            },
                        verticalArrangement = Arrangement.Center

                    ) {
                        val annotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(Color.White.copy(0.5f), fontSize = 14.sp)) {
                                append(stringResource(R.string.total_accounts))
                            }
                            withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)) {
                                append(stringResource(R.string.balance))
                            }
                        }

                        Row() {
                            Text(
                                text = annotatedString,
                                modifier = Modifier
                                    .align(Bottom)
                                    .padding(end = 8.dp, top = 3.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_business_expand),
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Bottom),
                                contentDescription = ""
                            )

                        }
                        Row() {
                            Text(
                                text = stringResource(R.string._10_000_000),
                                modifier = Modifier.align(Top),
                                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                            Text(
                                text = stringResource(R.string._00),
                                modifier = Modifier
                                    .align(Bottom)
                                    .padding(vertical = 3.dp),
                                style = TextStyle(fontSize = 24.sp),
                                color = Color.White
                            )
                            Text(
                                text = stringResource(R.string.text_currency_type),
                                modifier = Modifier
                                    .padding(end = 22.dp)
                                    .padding(3.dp)
                                    .align(Bottom),
                                style = TextStyle(fontSize = 24.sp),
                                color = Color.White
                            )
                        }

                    }
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(0.75f)
                .padding(horizontal = 5.dp)
        ) {
            CardMenuContent()

            TabLayoutMenu(navController, homeData)

        }


    }

    SelectCompanyBottomSheet(selectCompanyState)
    DropDownPopup(balancePopup, density, touchPoint, screenHeightDp)


}

@Composable
fun DropDownPopup(
    expanded: MutableState<Boolean>,
    density: Density,
    touchPoint: Offset,
    screenHeightDp: Dp
) {
    val (xDp, yDp) = with(density) {
        (touchPoint.x.toDp()) to (touchPoint.y.toDp())
    }
    DropdownMenu(
        expanded = expanded.value,
        offset = DpOffset(xDp, -screenHeightDp + yDp + 150.dp),
        onDismissRequest = { expanded.value = false }
    ) {
        DropdownMenuItem(
            content = { Text(stringResource(R.string.blocked)) },
            onClick = { /* Handle refresh! */ }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.free_balance)) },
            onClick = { /* Handle settings! */ }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.bank_execution)) },
            onClick = { /* Handle send feedback! */ }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.after_execution)) },
            onClick = { /* Handle send feedback! */ }
        )
    }
}

private fun fetchUserDetails(): LoginVerifyResponse {
    val str = MainApp.session[Keys.KEY_USER_DETAILS]
    return Converter.fromJson(str!!, LoginVerifyResponse::class.java)
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
//SimpleTabRow()
    MenuScreen(rememberNavController())
}