package com.app.transfer.components

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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

    StatusBottomSheet(showAccountBottomSheet)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeBottomSheet(showStatusBottomSheet: MutableState<Boolean>){
    if (showStatusBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showStatusBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),

    ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .padding(horizontal = 10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.type),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    color = Color(0xFF223142)
                )
            }
            Spacer(modifier = Modifier.padding(top = 5.sdp))
            TypeList()
        }
    }
}

data class TypeModel(
    val title: String
)

@Composable
fun TypeList() {
    val menu = remember { DataClassTransfer.TypeModelList }
    LazyColumn(
    ) {
        items(items = menu, itemContent = {
            TypeMenuItem(menuItem = it)
        })
    }
}

@Composable
fun TypeMenuItem(menuItem:TypeModel){
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(
                horizontal = 10.dp,
                vertical = 2.dp
            )
    ) {
            Text(
                text = menuItem.title,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.sdp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
        Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.sdp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.sdp)
                    .background(
                        color = Color(R.color.border_grey), shape = RoundedCornerShape(size = 10.sdp)
                    )
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.sdp))
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun showTypeBottom() {
    TypeBottomSheet()
}