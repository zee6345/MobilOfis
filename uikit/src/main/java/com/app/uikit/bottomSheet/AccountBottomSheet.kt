package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.network.models.responseModels.GetAccountsItem
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import ir.kaaveh.sdpcompose.sdp


@Composable
fun AccountBottomSheet() {
    val showAccountBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(R.string.show_account_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                showAccountBottomSheet.value = !showAccountBottomSheet.value
            }

        )
    }

    AccountBottomSheet(showAccountBottomSheet, null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(
    showAccountBottomSheet: MutableState<Boolean>,
    accounts: MutableList<GetAccountsItem>?
) {
    if (showAccountBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showAccountBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),

        ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.from_the_account),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Text(
                text = stringResource(R.string.all),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.sdp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

            AccountTypeList(accounts!!)
        }
    }
}


@Composable
fun AccountTypeList(accounts: MutableList<GetAccountsItem>) {
//    val menu = remember { DataProvider.AccountItems }
    LazyColumn(
    ) {
        items(items = accounts, itemContent = {
            AccountMenuItem(accountItem = it)
        })
    }
}

@Composable
fun AccountMenuItem(accountItem: GetAccountsItem) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .dashedBorder(1.dp, colorResource(id = R.color.border_light_grey))
                .padding(vertical = 2.dp)
        ) {
            Text(
                text = accountItem.IBAN,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.sdp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
//            if (menuItem.showIcon) {
//                Icon(
//                    painterResource(id = R.drawable.ic_blocked),
//                    contentDescription = stringResource(R.string.calender),
//                    modifier = Modifier
//                        .padding(horizontal = 2.sdp)
//                        .align(Alignment.CenterVertically)
//                )
//            } else {
////                Log.e("error", "icon hide")
//            }
        }
        Text(
            text = accountItem.BALANCE,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.sdp),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            color = Color(0xFF223142)
        )
        Spacer(modifier = Modifier.padding(top = 5.sdp))
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.sdp)
//                    .background(
//                        color = Color(R.color.border_grey),
//                        shape = RoundedCornerShape(size = 10.sdp)
//                    )
//                    .align(Alignment.CenterVertically)
//            )
//        }
        Spacer(modifier = Modifier.padding(top = 5.sdp))
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun previewAccounts() {
    AccountBottomSheet()
}