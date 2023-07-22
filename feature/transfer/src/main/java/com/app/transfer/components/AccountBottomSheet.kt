package com.app.transfer.components

import android.util.Log
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.transfer.R
import com.app.transfer.transfers.DataClassTransfer
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
            text = AnnotatedString(text = "Show Account BottomSheet"),
//                    color = Color(0xFF203657),
            onClick = {
                showAccountBottomSheet.value = !showAccountBottomSheet.value
            }

        )
    }

    AccountBottomSheet(showAccountBottomSheet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(showAccountBottomSheet: MutableState<Boolean>) {
    if (showAccountBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showAccountBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "From the account",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Text(
                text = "All",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.sdp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

            AccountTypeList()
        }
    }
}

data class AccountMenuModel(
    val title: String,
    val subTitle: String,
    val showIcon: Boolean
)

@Composable
fun AccountTypeList() {
    val menu = remember { DataClassTransfer.AccountItems }
    LazyColumn(
    ) {
        items(items = menu, itemContent = {
            AccountMenuItem(menuItem = it)
        })
    }
}

@Composable
fun AccountMenuItem(menuItem: AccountMenuModel) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Text(
                text = menuItem.title,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.sdp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
            if (menuItem.showIcon) {
                Icon(
                    painterResource(id = R.drawable.ic_blocked),
                    contentDescription = "calender",
                    modifier = Modifier
                        .padding(horizontal = 2.sdp)
                        .align(Alignment.CenterVertically)
                )
            } else {
                Log.e("error", "icon hide")
            }
        }
        Text(
            text = menuItem.subTitle,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.sdp),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            color = Color(0xFF223142)
        )
        Spacer(modifier = Modifier.padding(top = 5.sdp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.sdp)
                    .background(
                        color = Color(0xFFE7EEFC), shape = RoundedCornerShape(size = 10.sdp)
                    )
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(top = 5.sdp))
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun previewAccounts() {
    AccountBottomSheet()
}