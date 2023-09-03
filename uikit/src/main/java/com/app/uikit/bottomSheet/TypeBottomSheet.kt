package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import ir.kaaveh.sdpcompose.sdp


@Composable
fun TypeBottomSheet() {
    val showAccountBottomSheet = rememberSaveable { mutableStateOf(false) }

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
                showAccountBottomSheet.value = !showAccountBottomSheet.value
            }

        )
    }

//    StatusBottomSheet(showAccountBottomSheet) {
//
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeBottomSheet(
    showStatusBottomSheet: MutableState<Boolean>,
    typeList: MutableList<String>,
    onTypeClick: (menuItem: TypeModel) -> Unit
) {

    if (showStatusBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showStatusBottomSheet.value = false },
        shape = RoundedCornerShape(10.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.type),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF223142),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Spacer(modifier = Modifier.padding(top = 5.sdp))


            val uniqueTypes = remember(typeList) { typeList.distinctBy { it } }

            LazyColumn {
                items(items = uniqueTypes, itemContent = {
                    TypeMenuItem(menuItem = it) { type ->
                        onTypeClick(type)
                    }
                })
            }
        }
    }
}

data class TypeModel(
    val prefix: String,
    val title: String
)


@Composable
fun TypeMenuItem(menuItem: String, onTypeClick: (menuItem: TypeModel) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .dashedBorder(3.dp, colorResource(R.color.border_grey))
            .padding(vertical = 2.dp)
            .clickable {
                onTypeClick(TypeModel(menuItem, extractTypeDetails(menuItem)))
            }
    ) {
        Text(
            text = "${menuItem} - ${extractTypeDetails(menuItem)}",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.sdp, start = 10.sdp, end = 10.sdp),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            color = Color(0xFF223142)
        )
        Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.sdp))

    }
}

private fun extractTypeDetails(type: String): String {
    var detail = ""
    when (type) {
        "IM" -> {
            detail = "Internal to own account"
        }

        "IT" -> {
            detail = "Internal to other account"
        }

        "FX" -> {
            detail = "Currency exchange"
        }

        "AZ" -> {
            detail = "AZIPS"
        }

        "XO" -> {
            detail = "XÖHKS"
        }

        "XT" -> {
            detail = "Budget - STA"
        }

        "XV" -> {
            detail = "Budget - VAT"
        }

        "FP" -> {
            detail = "Foreign currency transfer"
        }

        "RP" -> {
            detail = "RUB"
        }

        "SP" -> {
            detail = "Salary - Transfer"
        }


    }
    return detail
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun showTypeBottom() {
    TypeBottomSheet()
}