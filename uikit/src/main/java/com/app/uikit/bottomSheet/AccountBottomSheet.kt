package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.network.models.responseModels.GetAccountsItem
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import com.app.uikit.models.AccountsData
import com.app.uikit.utils.Utils
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

    AccountBottomSheet(showAccountBottomSheet, null) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(
    showAccountBottomSheet: MutableState<Boolean>,
    accounts: MutableList<AccountsData>?,
    onAccountClick: (accountItem: AccountsData) -> Unit
) {


    if (showAccountBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showAccountBottomSheet.value = false },
        shape = RoundedCornerShape(10.dp),

        ) {
        Column {
            Text(
                text = stringResource(R.string.from_the_account),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
            ) {

                Text(
                    text = stringResource(R.string.all),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp, horizontal = 20.dp),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    color = Color(0xFF223142)
                )
            }

            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

            val unique = remember { accounts!! }.distinctBy { it.accountNum }

            LazyColumn {
                items(items = unique, itemContent = {
                    AccountMenuItem(accountItem = it) { account ->
                        onAccountClick(account)
                    }
                })
            }
        }
    }
}


@Composable
fun AccountMenuItem(
    accountItem: AccountsData,
    onAccountClick: (accountItem: AccountsData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.White)
            .dashedBorder(2.dp, colorResource(R.color.border_grey))
            .clickable {
                onAccountClick(accountItem)
            }
    ) {

        Text(
            text = accountItem.accountNum,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 2.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF223142),
            )


        )

        Text(
            text = Utils.formatAmountWithSpaces(accountItem.ammount),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 2.dp),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.4.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF203657),
            )
        )
    }

}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun previewAccounts() {
    AccountBottomSheet()
}