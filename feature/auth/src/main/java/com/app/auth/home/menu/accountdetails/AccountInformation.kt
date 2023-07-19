package com.app.auth.home.menu.accountdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


data class TabItem(
    val title: String, val screen: @Composable () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountInformation(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState(), enabled = true
            )
            .background(color = Color(0xFFF3F7FA))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(15.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentDescription = ""
                )
                Text(
                    text = "Detailed account information",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }


        //main content
        Column(
            modifier = Modifier
                .weight(0.9f)

        ) {

            MainContent(navController)


        }

    }
}

@OptIn(ExperimentalFoundationApi::class)

@Composable
private fun MainContent(navController: NavController) {

    val tabs = listOf(
        TabItem(title = "Main information", screen = { MainInformation(navController) }),
        TabItem(title = "Blockages", screen = { Blockages(navController) })
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val selectedMain = remember { mutableStateOf(false) }

    Column {

        Row(
            Modifier.padding(horizontal = 10.sdp, vertical = 8.sdp)
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .background(
                        if (selectedMain.value) {
                            Color.Transparent
                        } else {
                            Color.White
                        },
                        RoundedCornerShape(8.dp)
                    )
                    .clickable {

                        coroutineScope.launch {
                            selectedMain.value = false

                            pagerState.animateScrollToPage(0)
                        }
                    },
                Alignment.Center,

                ) {
                Text(
                    modifier = Modifier.padding(vertical = 5.sdp),
                    text = "Main information",
                    style = TextStyle(
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Center
                    )

                )
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .background(
                        if (!selectedMain.value) {
                            Color.Transparent
                        } else {
                            Color.White
                        },
                        RoundedCornerShape(8.dp)
                    )
                    .clickable {

                        coroutineScope.launch {
                            selectedMain.value = true
                            pagerState.animateScrollToPage(1)
                        }
                    },
                Alignment.Center
            ) {
                Text(
                    text = "Blockages",
                    modifier = Modifier.padding(vertical = 5.sdp),
                    style = TextStyle(
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        HorizontalPager(
            pageCount = tabs.size, state = pagerState
        ) {
            tabs[pagerState.currentPage].screen()
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAccountInformation() {
//    AccountInformation(navController = rememberNavController())
    MainContent(navController = rememberNavController())
}
