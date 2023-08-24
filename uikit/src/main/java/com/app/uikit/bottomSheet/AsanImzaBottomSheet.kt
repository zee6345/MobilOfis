package com.app.uikit.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
fun AsanImzaSheet(navController: NavController) {
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

    AsanImzaBottomSheet(aboutBankState, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsanImzaBottomSheet(asanImzaState: MutableState<Boolean>, navController: NavController) {
    if (asanImzaState.value) ModalBottomSheet(
        onDismissRequest = { asanImzaState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        containerColor = colorResource(id = R.color.blue_01),
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.sdp, horizontal = 20.sdp)
        ){
            Text(
                text= stringResource(id = R.string.asan_imza_help),
                style= TextStyle(
                    color = Color.White
                )
            )
        }
    }

}


@Preview
@Composable
private fun PreViewPinSheet() {
    PinChangeSheet(rememberNavController())
}