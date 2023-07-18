package com.app.auth.home.menu

import androidx.compose.ui.graphics.Color
import com.app.auth.R
import com.app.auth.data.CardFilters
import com.app.auth.data.CardsListData
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


    val cardsList = listOf(
        CardsListData(
            icon = R.drawable.ic_master_card,
            title = "Master Card Business Plus",
            cardIcon = R.drawable.ic_master_card_icon,
            cardTitle = "•••• 8339",
            cardInc = "+2",
            cardAmount = "1560.23 $"
        ),
        CardsListData(
            icon = R.drawable.ic_visa_business,
            title = "Visa Business",
            cardIcon = R.drawable.ic_visa_icon,
            cardTitle = "•••• 7443",
            cardInc = "+1",
            cardAmount = "1560.23 $"
        ),

        )

    val filtersList = listOf(
        CardFilters(
            "Account name and IBAN",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "User",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Status",
            R.drawable.ic_filter_drop_down
        ),


        CardFilters(
            "End date",
            R.drawable.ic_filter_sort
        ),

        CardFilters(
            "Balance",
            R.drawable.ic_filter_sort
        ),

        )


    val filtersLoanList = listOf(
        CardFilters(
            "N of the loan agreement",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Currency",
            R.drawable.ic_filter_drop_down
        ),


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
}