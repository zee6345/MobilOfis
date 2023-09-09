package com.app.transfer.transfers.transferdetails

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
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
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.transfer.R
import com.app.uikit.models.TabItem
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

const val transferToDetails = "transferToDetails"

@Composable
fun TransferDetailsInformation(navController: NavController) {

    val context = LocalContext.current

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
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
//                            navController.popBackStack()
                            (context as ComponentActivity).finish()
                        },
                    contentDescription = ""
                )
                Text(
                    text = stringResource(R.string.transfer_details),
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

            MainContentSource(navController)


        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainContentSource(navController: NavController) {

    val tabs = listOf(
        TabItem(title = "Details", screen = { Details(navController) }),
        TabItem(title = "History and notes", screen = { HistoryNnotes(navController) })
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )
    {
        // provide pageCount
        tabs.size
    }
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
                    text = stringResource(R.string.details),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontWeight = FontWeight(600),
                        color = if (!selectedMain.value) Color(0xFF203657) else Color(0xFF859DB5),
                        textAlign = TextAlign.Center,
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
                    text = stringResource(R.string.history_and_notes),
                    modifier = Modifier.padding(vertical = 5.sdp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontWeight = FontWeight(600),
                        color = if (!selectedMain.value) Color(0xFF859DB5) else  Color(0xFF203657),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = false,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            key = null,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                Orientation.Horizontal
            ),
            pageContent = {
                tabs[pagerState.currentPage].screen()
            }
        )

    }
}