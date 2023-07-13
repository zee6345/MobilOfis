package com.app.auth.home.menu

import androidx.compose.ui.graphics.Color
import com.app.auth.home.menu.component.AccountListData

object DataProvider {

    val accountList = listOf(
        AccountListData(
            title = "Current account in head office",
            description = "AZ63BRES0038019440023915603",
        ),
        AccountListData(
            title = "Current account in head office",
            description = "AZ63BRES0038019440023915603",
        ),
        AccountListData(
            title = "Current account in head office",
            description = "AZ63BRES0038019440023915603",
        ),
        AccountListData(
            title = "Current account in head office",
            description = "AZ63BRES0038019440023915603",
        ),
        AccountListData(
            title = "Current account in head office",
            description = "AZ63BRES0038019440023915603",
        ),

    )



    val menuList=listOf(
        CardMenu(
            title = "Signature\n" +
                    "Waiting",
            color = Color(0xff2CCAD3),
            number = 3
        ),
        CardMenu(
            title = "Time\n" +
                    "past",
            color = Color(0xff916FDD),
            number = 1
        ),
        CardMenu(
            title = "Sent to\n" +
                    "Bank",
            color = Color(0xffF48A1D),
            number = 1
        ),
        CardMenu(
            title = "Emal\nolunmadri",
            color = Color(0xFFC74375),
            number = 1
        ),
        CardMenu(
            title = "Silinib",
            color = Color(0xFF939597),
            number = 1
        ),

        )
}