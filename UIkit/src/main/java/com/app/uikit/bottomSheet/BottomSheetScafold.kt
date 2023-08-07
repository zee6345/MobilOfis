package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun BottomSheetMain() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {

        },
        sheetPeekHeight = 40.dp
    ) {
        // Content of the main screen
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Information and Content",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )

                BottomSheetItem(R.drawable.location, "Branches and ATMs")
                BottomSheetItem(R.drawable.tariffs_icon, "Tariffs")
                BottomSheetItem(R.drawable.whatsapp_icon, "WhatsApp support")
                BottomSheetItem(R.drawable.call_icon, "Call Center")
                BottomSheetItem(R.drawable.language, "Application Language")
            }
        }
    }
}

@Composable
fun BottomSheetItem(iconRes: Int, title: String) {
    Row(
        modifier = Modifier
            .padding(start = 18.dp)
            .dashedBorder(3.dp, Color(R.color.border_grey))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "",
            modifier = Modifier
                .size(34.dp)
                .align(Alignment.CenterVertically)
        )
        androidx.compose.material3.Text(
            text = title,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}



@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BottomSheetMain()
}