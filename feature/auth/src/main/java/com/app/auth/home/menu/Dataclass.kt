package com.app.auth.home.menu

import androidx.compose.ui.graphics.Color
import com.app.auth.R
import com.app.auth.home.adjustments.companies.CompanyListName
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



    val cardList=listOf(
        CardsListData(
            title = "MC Business Plus",
            image = R.drawable.ic_master_card,
            card_icon = R.drawable.ic_master_card_icon,
            card_num = "•••• 8339"
        ),
        CardsListData(
            title = "Visa Business",
            image = R.drawable.ic_visa_business,
            card_icon = R.drawable.ic_visa_icon,
            card_num = "•••• 7443"
        )
    )


    val menuList = listOf(
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


    val companyList =
        listOf(CompanyListName(
            name = "International Real Estate and Valuation Agency LLC",
        ),
            CompanyListName(name = "Value Services MMC"),
            CompanyListName(name = "Səba MMC"),
        )
}
