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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
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
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


@Composable
fun BottomSheetCard() {
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
fun BottomSheetCardStatus(
    showStatusBottomSheet: MutableState<Boolean>,
    statusList: MutableList<String>,
    onStatusClick: (String) -> Unit
) {
    if (showStatusBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showStatusBottomSheet.value = false },
        shape = RoundedCornerShape(10.dp),

        ) {
        Column {

            Text(
                text = stringResource(R.string.status),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )

            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))

            val unique = remember { statusList }.distinctBy { it }

            LazyColumn(
            ) {
                items(items = unique, itemContent = {
                    StatusMenuItem(it) { filter ->
                        onStatusClick(filter)
                    }
                })
            }
        }
    }
}


@Composable
private fun StatusMenuItem(status: String, onStatusClick: (String) -> Unit) {


    Row(
        modifier = Modifier
            .background(Color.White)
            .dashedBorder(2.dp, colorResource(R.color.border_grey))
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable {
                onStatusClick(status)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier.size(
                    width = 5.dp,
                    height = 1.dp
                )
            )

            androidx.compose.material.Text(
                text = status,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )


        }

    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
private fun showStatus() {
    StatusBottomSheet()
}