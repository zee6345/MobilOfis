package com.app.auth.login.components.bottomSheet


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showInformationBottomSheet.value = !showInformationBottomSheet.value
        }) {
            Text(text = "Show ModalBottomSheet")
        }
    }

    InformationAndContentModalBottomSheet(showInformationBottomSheet)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationAndContentModalBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "information And Content",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.location),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "Branches and ATMs", modifier = Modifier.padding(vertical = 12.dp))
            }

            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.tariffs_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "Tariffs", modifier = Modifier.padding(vertical = 12.dp))
            }

            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.whatsapp_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "WhatsApp support", modifier = Modifier.padding(vertical = 12.dp))
            }
            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.call_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically)
                        .fillMaxWidth(),

                    contentDescription = ""
                )
                Text(text = "Call Center", modifier = Modifier.padding(vertical = 12.dp))
            }
            Row(
                modifier = Modifier.padding(start = 18.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.language),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically)
                        .fillMaxWidth(),
                    contentDescription = ""
                )
                Text(text = "Application Language", modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }
}


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



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun InformationBottomSheet(){

    BottomSheetScaffold(
    sheetShape = RoundedCornerShape(20.dp),//Rounded corners
    sheetPeekHeight = 80.dp,//Initial height of sheet[Collapsed]{maybe too much 4 u}
    sheetContent = {
        MyBottomSheet()//Create a sheet Composable
    }
    ){}
}
@Composable
fun MyBottomSheet() {
    Box (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Column(Modifier.fillMaxWidth().fillMaxHeight()) {
            Text(
                text = "information And Content",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.location),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "Branches and ATMs", modifier = Modifier.padding(vertical = 12.dp))
            }

            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.tariffs_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "Tariffs", modifier = Modifier.padding(vertical = 12.dp))
            }

            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.whatsapp_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(text = "WhatsApp support", modifier = Modifier.padding(vertical = 12.dp))
            }
            Row(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .dashedBorder(
                        3.dp, Color(0xFFE7EEFC)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.call_icon),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically)
                        .fillMaxWidth(),

                    contentDescription = ""
                )
                Text(text = "Call Center", modifier = Modifier.padding(vertical = 12.dp))
            }
            Row(
                modifier = Modifier.padding(start = 18.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.language),
                    modifier = Modifier
                        .height(28.dp)
                        .width(34.dp)
                        .align(CenterVertically)
                        .fillMaxWidth(),
                    contentDescription = ""
                )
                Text(text = "Application Language", modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }

}



@Preview(device = Devices.PIXEL_4)
@Composable
fun preview() {
    InformationBottomSheet()
//    InformationBottomSheetScreen()
}
