package com.app.uikit.bottomSheet

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
import androidx.compose.runtime.remember
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
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import com.app.uikit.data.DataProvider
import com.app.uikit.models.CurrencyModel
import ir.kaaveh.sdpcompose.sdp


@Composable
fun CurrencyBottomSheet() {
    val showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = "Show Status BottomSheet"),
//                    color = Color(0xFF203657),
            onClick = {
                showCurrencyBottomSheet.value = !showCurrencyBottomSheet.value
            }

        )
    }

    CurrencyBottomSheet(showCurrencyBottomSheet) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    showCurrencyBottomSheet: MutableState<Boolean>,
    onCurrencyClick: (String) -> Unit
) {
    if (showCurrencyBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showCurrencyBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.currency),
                    modifier = Modifier
                        .padding(top = 5.sdp, bottom = 5.sdp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    color = Color(0xFF223142)
                )
            }


            val menu = remember { DataProvider.currencyModelList }
            LazyColumn(
            ) {
                items(items = menu, itemContent = {
                    CurrencyMenuItem(menuItem = it) { currency ->
                        onCurrencyClick(currency)
                    }
                })
            }

        }
    }
}


@Composable
fun CurrencyMenuItem(menuItem: CurrencyModel, onCurrencyClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .dashedBorder(2.dp, colorResource(R.color.border_grey))
            .clickable {
                val data = if (menuItem.equals("ALL")) {
                    ""
                } else menuItem.title

                onCurrencyClick(data)
            }
    ) {
        Text(
            text = menuItem.title,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.sdp, bottom = 5.sdp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            color = Color(0xFF223142)
        )
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun showCurrencyBottom() {
    CurrencyBottomSheet()
}