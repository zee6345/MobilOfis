@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.home.menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.adjustment.components.AboutBankSheet
import com.app.transfer.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SelectCompanyBottomSheet(){
    val selectCompanyState = rememberSaveable { mutableStateOf(false) }

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
                selectCompanyState.value = !selectCompanyState.value
            }

        )
    }

    SelectCompanyBottomSheet(selectCompanyState)
}

@Composable
fun SelectCompanyBottomSheet(selectCompanyState: MutableState<Boolean>){
    if (selectCompanyState.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { selectCompanyState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Select Company",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))

            Text(
                text = "International Real Estate and Valuation Agency MMC",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .dashedBorder(1.dp, Color(0x99C9CACC)),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
            Text(
                text = "Value Services MMC",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp).fillMaxWidth().dashedBorder(1.dp, Color(0x99C9CACC)),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
            Text(
                text = "Saba MMC",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp).fillMaxWidth().dashedBorder(1.dp, Color(0x99C9CACC)),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142),
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun display(){
    SelectCompanyBottomSheet()
}