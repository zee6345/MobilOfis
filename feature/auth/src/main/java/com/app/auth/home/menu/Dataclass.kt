package com.app.auth.home.menu

import androidx.compose.ui.graphics.Color
import com.app.auth.R
import com.app.auth.home.menu.component.AccountListData
import com.app.auth.home.menu.component.CardsListData

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


    val cardsList = listOf(
        CardsListData(
            icon = R.drawable.ic_master_card,
            title = "MC Business Plus",
            cardIcon = R.drawable.ic_master_card_icon,
            cardTitle = "•••• 8339",
            cardAmount = "1560.23 $"
        ),
        CardsListData(
            icon = R.drawable.ic_visa_business,
            title = "Visa Business",
            cardIcon = R.drawable.ic_visa_icon,
            cardTitle = "•••• 7443",
            cardAmount = "1560.23 $"
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