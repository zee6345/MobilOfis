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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.app.uikit.data.DataProvider
import ir.kaaveh.sdpcompose.sdp


@Composable
fun StatusBottomSheet() {
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

    StatusBottomSheet(showAccountBottomSheet){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusBottomSheet(
    showStatusBottomSheet: MutableState<Boolean>,
    onStatusClick: (String) -> Unit
) {
    if (showStatusBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showStatusBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),

        ) {
        Column(
            modifier = Modifier

        ) {

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


            val menu = remember { DataProvider.StatusList }
            LazyColumn(
            ) {
                items(items = menu, itemContent = {
                    StatusMenuItem(menuItem = it) { filter ->
                        onStatusClick(filter)
                    }
                })
            }
        }
    }
}

data class StatusModel(
    val color: Color,
    val title: String
)


@Composable
fun StatusMenuItem(menuItem: StatusModel, onStatusClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .clickable { onStatusClick(menuItem.title) }
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_circle),
                contentDescription = "circle",
                tint = menuItem.color,
                modifier = Modifier
                    .padding(horizontal = 5.sdp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = menuItem.title,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(0xFF223142)
            )
        }
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
                        color = colorResource(R.color.border_grey),
                        shape = RoundedCornerShape(size = 10.sdp)
                    )
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(top = 5.sdp))
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun showStatus() {
    StatusBottomSheet()
}