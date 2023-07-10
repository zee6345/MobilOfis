package com.app.auth.pin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.pin.components.CustomKeyboard
import com.app.auth.pin.components.PinView

@Composable
fun PinScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)

            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    text = "Do you want to set a PIN?",
                    style = TextStyle(color = Color.White, fontSize = 22.sp)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PinView()
            CustomKeyboard(navController, "PinScreen")
            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(14.dp)) {
                Text(
                    text = "No",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 18.dp),
                    color = Color(0xFF203657)
                )
            }


        }

    }

}


@Preview(device = Devices.PIXEL_4)
@Composable
fun pinScreenPreview() {
    val navController = rememberNavController()
    PinScreen(navController)
}