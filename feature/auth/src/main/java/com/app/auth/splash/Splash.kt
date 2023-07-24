package com.app.auth.splash


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navController: NavController) {

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = !isVisible

    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                // customize with tween AnimationSpec
                animationSpec = tween(
                    durationMillis = 3000,
                    delayMillis = 200,
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

//        AnimatedView()

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                // customize with tween AnimationSpec
                animationSpec = tween(
                    durationMillis = 3000,
                    delayMillis = 200,
                    easing = LinearOutSlowInEasing
                )
            ),
            // you can also add animationSpec in fadeOut if need be.
            exit = fadeOut() + shrinkVertically(),

            ) {
            Text(
                text = "Mobile Office",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }




    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_footer), contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.sdp)
        )
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSplash() {
    SplashScreen(navController = rememberNavController())
}



@Composable
fun AnimatedView() {
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = 200,
                easing = LinearOutSlowInEasing
            )
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutLinearInEasing
            )
        ),
        content = {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .wrapContentSize(Alignment.Center)
                    .clickable {
                        isVisible = !isVisible
                    }
            ) {
                Text(
                    text = "Mobile Office",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    )
}



