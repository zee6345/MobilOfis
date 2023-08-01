package com.app.auth.login.components.bottomSheet



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//import androidx.compose.material.BottomSheetState
//import androidx.compose.material.BottomSheetValue
//import androidx.compose.material.ModalBottomSheetValue
//import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.auth.R

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


//@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
//@Composable
//fun InformationAndContentModalBottomSheet() {
////    if (showModalBottomSheet.value)
////    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val sheetState = rememberModalBottomSheetState(false)
//
//    ModalBottomSheet(
//        sheetState = sheetState,
//        onDismissRequest = {  },
//    ) {
//        Column(Modifier.fillMaxWidth()) {
//            Text(
//                text = "information And Content",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth(),
//                fontWeight = FontWeight.Bold
//            )
//
//            BottomSheetItems(R.drawable.location, "Branches and ATMs")
//            BottomSheetItems(R.drawable.tariffs_icon, "Tariffs")
//            BottomSheetItems(R.drawable.whatsapp_icon, "WhatsApp support")
//            BottomSheetItems(R.drawable.call_icon, "Call Center")
//            BottomSheetItems(R.drawable.language, "Application Language")
//        }
//    }
//}


fun Modifier.dashedBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }

    this.then(Modifier.drawWithCache {
        onDrawBehind {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val strokeWidthWithDensity = strokeWidthPx / density.density
            val start = Offset(0f, size.height)
            val end = Offset(size.width, size.height)
            drawLine(
                color = color,
                start = start,
                end = end,
                strokeWidth = strokeWidthWithDensity,
                pathEffect = pathEffect
            )
        }
    })
})

@Composable
private fun BottomSheetItems(iconRes: Int, title: String) {
    Row(
        modifier = Modifier
            .padding(top = 32.dp, start = 18.dp)
            .dashedBorder(
                3.dp, Color(com.app.home.R.color.border_grey)
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
