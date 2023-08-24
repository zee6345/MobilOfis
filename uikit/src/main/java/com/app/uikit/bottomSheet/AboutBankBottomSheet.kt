@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.app.uikit.bottomSheet

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

import ir.kaaveh.sdpcompose.sdp

@Composable
fun AboutBankSheet(navController: NavController) {
    val aboutBankState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(R.string.show_status_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                aboutBankState.value = !aboutBankState.value
            }

        )
    }

    AboutBankSheet(aboutBankState, navController){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBankSheet(aboutBankState: MutableState<Boolean>, navController: NavController, onItemClick:()->Unit) {
    if (aboutBankState.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { aboutBankState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {

        Column(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.about_the_bank),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .dashedBorder(
                        3.dp, colorResource(R.color.border_grey)
                    ),
                horizontalArrangement = Arrangement.Start

            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                            .padding(2.dp)
                    )
                    androidx.compose.material.Text(
                        text = stringResource(R.string.branches_and_atms),
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .dashedBorder(
                        2.dp, colorResource(R.color.border_grey)
                    ),
                horizontalArrangement = Arrangement.Start
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.ic_tariff),
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                            .padding(2.dp)
                    )
                    androidx.compose.material.Text(
                        text = stringResource(R.string.tariffs),
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        aboutBankState.value = false
                        onItemClick()
                    },
                horizontalArrangement = Arrangement.Start
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 3.dp, horizontal = 7.dp)
                    )
                    androidx.compose.material.Text(
                        text = stringResource(R.string.exchange_rates),
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun view(){
    AboutBankSheet(rememberNavController())
}