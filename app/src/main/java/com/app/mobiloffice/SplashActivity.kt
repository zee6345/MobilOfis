package com.app.mobiloffice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.app.mobiloffice.ui.theme.MobilOfficeTheme
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilOfficeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadImageAndOther()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            delay(3000)

//            val intent = Intent(this@SplashActivity, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finishAffinity()
        }
    }


}

@Composable
private fun LoadImageAndOther() {
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
            contentDescription = stringResource(id = R.string.logo),
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
}