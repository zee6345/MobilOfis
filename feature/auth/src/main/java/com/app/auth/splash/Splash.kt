package com.app.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.auth.R
import com.app.auth.login.navigation.loginNavigationRoute
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .padding(20.sdp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val img = painterResource(id = R.drawable.ic_logo)
        Image(
            painter = img,
            contentDescription = "",
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )

        Text(
            text = "Mobile Office",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_footer), contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.sdp)
        )
    }


    LaunchedEffect(Unit) {
        // Delay for 2 seconds before navigating
//        delay(40000)
//        navController.navigate(loginNavigationRoute)
    }


}