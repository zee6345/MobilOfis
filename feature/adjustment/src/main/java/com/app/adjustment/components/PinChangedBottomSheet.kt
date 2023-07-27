package com.app.adjustment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PinChangeSheet(navController: NavController) {
    val aboutBankState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(id = R.string.show_status_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                aboutBankState.value = !aboutBankState.value
            }

        )
    }

    PinChangedBottomSheet(aboutBankState, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinChangedBottomSheet(aboutBankState: MutableState<Boolean>, navController: NavController) {
    if (aboutBankState.value) ModalBottomSheet(
        containerColor = Color(0xFF203657),
        onDismissRequest = { aboutBankState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),

    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {

                Image(painterResource(id = R.drawable.success_icon), contentDescription = null,
                    modifier = Modifier.size(32.dp))

                Column(
                    Modifier.fillMaxWidth()
                ) {

                    Text(
                        text= stringResource(R.string.successful_operation),
                        style= TextStyle(
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                    Text(
                        text= stringResource(R.string.pin_has_been_changed),
                        style=TextStyle(
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),
                        )
                    )



                }


            }

            Button(
                onClick = {

            },
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(),

            ) {
                Text(
                    stringResource(R.string.close),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFF203657),
                        textAlign = TextAlign.Center,
                    )
                )
            }


        }
    }

}


@Preview
@Composable
private fun PreViewPinSheet() {
    PinChangeSheet(rememberNavController())
}