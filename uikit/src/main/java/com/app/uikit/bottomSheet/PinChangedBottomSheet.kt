package com.app.uikit.bottomSheet

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
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.uikit.R
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

    PinChangedBottomSheet(aboutBankState){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinChangedBottomSheet(aboutBankState: MutableState<Boolean>, onClick:()->Unit) {
    if (aboutBankState.value) ModalBottomSheet(
        onDismissRequest = { aboutBankState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        containerColor = colorResource(id = R.color.blue_01),
    ) {

        Column() {
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, start = 18.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            ) {

                Image(
                    painter = painterResource(id = R.drawable.success_icon),
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(top = 6.dp)
                        .size(30.dp),
                    contentDescription = ""
                )
                Column() {
                    Text(
                        text = stringResource(R.string.successful_registration),
                        style = TextStyle(color = Color.White, fontSize = 24.sp)
                    )
                    Text(
                        text ="PIN has been changed",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )

                }


            }
            Button(
                onClick = {
                          onClick()
//                    navController.navigate("adjustment"){
//                        popUpTo("adjustment"){
//                            inclusive = true
//                        }
//                    }


                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))

            ) {
                Text(
                    "Close",
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = colorResource(R.color.blue_01)
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