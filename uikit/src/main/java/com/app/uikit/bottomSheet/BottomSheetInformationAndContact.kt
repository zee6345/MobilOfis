package com.app.uikit.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

@Composable
fun InformationBottomSheetScreen() {
    val showInformationBottomSheet = rememberSaveable { mutableStateOf(false) }

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
////        Button(onClick = {
////            showInformationBottomSheet.value = !showInformationBottomSheet.value
////        }) {
////            Text(text = "Show ModalBottomSheet")
////        }
//    }

//    InformationAndContentModalBottomSheet(showInformationBottomSheet)
//    InformationAndContentModalBottomSheet()
}


@Composable
private fun BottomSheetItems(iconRes: Int, title: String) {
    Row(
        modifier = Modifier
            .padding(top = 32.dp, start = 18.dp)
            .dashedBorder(
                3.dp, Color(R.color.border_grey)
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
    ) {

        Icon(
            painter = painterResource(id = iconRes),
            modifier = Modifier
                .height(28.dp)
                .width(34.dp)
                .align(CenterVertically),
            contentDescription = ""
        )
        Text(text = title, modifier = Modifier.padding(vertical = 12.dp))
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun preview() {
//    InformationBottomSheet()
//    InformationBottomSheetScreen()
}
