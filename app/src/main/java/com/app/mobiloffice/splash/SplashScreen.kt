package com.app.mobiloffice.splash


import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.mobiloffice.MainActivity
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    var isVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = !isVisible
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Column(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center, // Change this to Center
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    // customize with tween AnimationSpec
                    animationSpec = tween(
                        durationMillis = 3000,
                        delayMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                ),
                // you can also add animationSpec in fadeOut if need be.
                exit = fadeOut() + shrinkHorizontally(),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    // customize with tween AnimationSpec
                    initialOffsetY = { it }, // Start off screen and slide in from its current position
                    animationSpec = tween(
                        durationMillis = 3000,
                        delayMillis = 200,
                        easing = LinearOutSlowInEasing
                    )
                ),
                // This exit animation will also move the text downwards as it fades out
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = { it }, // Slide out downwards off the screen
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutLinearInEasing
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.mobile_office),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_footer), contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.sdp)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)

        val mainActivity = Intent(context, MainActivity::class.java)
        context.startActivity(mainActivity)

        (context as? ComponentActivity)?.finishAffinity()

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSplash() {
    SplashScreen(navController = rememberNavController())
}





