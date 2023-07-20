package com.app.home.menu.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

data class TabItem(
    val title: String, val screen: @Composable () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutMenu(navController: NavController) {

    val tabs = listOf(
        TabItem(title = "Account", screen = { AccountList(navController) }),
        TabItem(title = "Cards", screen = { CardsList(navController) }),
        TabItem(title = "Loan", screen = { LoansList() }),
        TabItem(title = "Trust", screen = { TrustsList() })
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        tabs.size
    }
    val coroutineScope = rememberCoroutineScope()

    Column(
    ) {
        TabRow(selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color(0xFFF3F7FA),
            modifier = Modifier.drawBehind {
                drawRect(
                    color = Color.White, topLeft = Offset.Zero, size = this.size
                )
            }) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = item.title, fontSize = 12.sp) },
                    selectedContentColor = Color(0xFF203657),
                    unselectedContentColor = Color(0xFF859DB5),
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,

            userScrollEnabled = true,
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


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun TabLayoutMenuPreview() {
    TabLayoutMenu(rememberNavController())
}
