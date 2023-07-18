package com.app.auth.home.menu.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class TabItem(
    val title: String, val screen: @Composable () -> Unit
)


@Composable
fun TabScreen(
    content: String
) {
    AccountList()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutMenu() {
    val tabs = listOf(TabItem(title = "Account", screen = { TabScreen(content = "Account Page") }),
        TabItem(title = "Cards", screen = { TabScreen(content = "Cards") }),
        TabItem(title = "Loan", screen = { TabScreen(content = "Loan") }),
        TabItem(title = "Trust", screen = { TabScreen(content = "Trust") })


    )
    val pagerState = rememberPagerState()
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
            pageCount = tabs.size, state = pagerState
        ) {
            tabs[pagerState.currentPage].screen()
        }
    }


}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabLayoutMenuPreview() {
    TabLayoutMenu()
}
