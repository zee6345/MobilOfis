package com.app.uikit.bottomSheet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

@Composable
fun BottomSheetAboutBank() {

    val showAboutBAnkBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showAboutBAnkBottomSheet.value = !showAboutBAnkBottomSheet.value
        }) {
            Text(text = "Show ModalBottomSheet")
        }
    }

    AboutBankBottomSheet(showAboutBAnkBottomSheet)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBankBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "About the Bank",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp, start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(R.color.border_grey)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.location),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = ""
                )
                Text(
                    text = "Branches and ATMs",
                    modifier = Modifier.padding(vertical = 12.dp),
                    style = TextStyle(fontSize = 16.sp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(R.color.border_grey)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.tariffs_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = ""
                )
                Text(
                    text = "Tariffs",
                    modifier = Modifier.padding(vertical = 12.dp),
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            Row(
                modifier = Modifier.padding(start = 18.dp, bottom = 12.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.exchange),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    contentDescription = ""
                )
                Text(
                    text = "Exchange rates",
                    modifier = Modifier.padding(vertical = 12.dp),
                    style = TextStyle(fontSize = 16.sp)
                )

            }
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun AboutBankPreview() {
    BottomSheetAboutBank()
}
