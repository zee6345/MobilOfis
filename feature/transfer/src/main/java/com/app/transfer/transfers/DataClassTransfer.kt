package com.app.transfer.transfers


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import com.app.transfer.components.AccountMenuModel
import com.app.transfer.components.CurrencyModel
import com.app.transfer.components.DateMenuModel
import com.app.transfer.components.StatusModel
import com.app.transfer.components.TypeModel

object DataClassTransfer {
    val currencyModelList = listOf(
        CurrencyModel("ALL"),
        CurrencyModel("AZN"),
        CurrencyModel("USD"),
        CurrencyModel("EUR"),

    )
    val TypeModelList =listOf(
        TypeModel("IM - To my own account within the bank"),
        TypeModel("IT - To another account within the bank"),
        TypeModel("FX - Currency exchange"),
        TypeModel("AZ - AZIPS"),
        TypeModel("XO - XOHKS"),
        TypeModel("XT - Budget - DXA"),
    )
    val StatusList = listOf(
        StatusModel(
            color = Color(0xFF26D978), title = "Executed"
        ),StatusModel(
            color = Color(0xFF939597), title = "Deleted"
        ),StatusModel(
            color = Color(0xFF916FDD), title = "Expired date"
        ),StatusModel(
            color = Color(0xFFFF4E57), title = "Refused"
        ),StatusModel(
            color = Color(0xFFD7C20A), title = "Sent to the bank"
        ),StatusModel(
            color = Color(0xFFC74375), title = "Not processed"
        ),StatusModel(
            color = Color(0xFF88B04B), title = "Processing"
        ),
    )
    val AccountItems = listOf(
        AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = false
        ), AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = false
        ), AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = true
        )
    )
    val DateMenu = listOf(
        DateMenuModel(
            title = "Today", textColor = Color(0xFFFFFFFF), color = Color(0xFF0FBF1B)
        ),
        DateMenuModel(
            title = "Yesterday", textColor = Color(0xFF223142), color = Color(0xFFFFFFFF)
        ),
        DateMenuModel(
            title = "This Week", textColor = Color(0xFF223142), color = Color(0xFFFFFFFF)
        ),
        DateMenuModel(
            title = "Last Week", textColor = Color(0xFF223142), color = Color(0xFFFFFFFF)
        ),
        DateMenuModel(
            title = "Bu ay", textColor = Color(0xFF223142), color = Color(0xFFFFFFFF)
        ),
        DateMenuModel(
            title = "Keçan ay", textColor = Color(0xFF223142), color = Color(0xFFFFFFFF)
        ),

        )
    val topMenuList = listOf(
        MainCardTransfer(
            title = "Signature\n" + "Waiting", color = Color(0xff2CCAD3), number = 3
        ),
        MainCardTransfer(
            title = "Execution\n" + "done", color = Color(0xff26D978), number = 3
        ),
        MainCardTransfer(
            title = "Signature and\n" + "confirmation", color = Color(0xff268ED9), number = 2
        ),
        MainCardTransfer(
            title = "Sent to \nBank", color = Color(0xFFF48A1D), number = 1
        ),
        MainCardTransfer(
            title = "Not\n Processed", color = Color(0xFFC74375), number = 1
        ),

        )
    val filtersModelList = listOf(
        FilterModel("date", "This Week", Icons.Filled.ArrowDropDown, false),
        FilterModel("account", "From the account", Icons.Filled.ArrowDropDown, false),
        FilterModel("type", "Type", Icons.Filled.ArrowDropDown, false),
        FilterModel("field", "Field", Icons.Filled.Search, false),
        FilterModel("appointment", "Appointment", Icons.Filled.Search, false),
        FilterModel("amount", "Amount", Icons.Filled.Search, false),
        FilterModel("currency", "Currency", Icons.Filled.ArrowDropDown, false),
        FilterModel("status", "Status", Icons.Filled.ArrowDropDown, false)

    )

    val taskDates = listOf(
        TaskDate(
            date = "18 Februrary 2023", tasks = listOf(
                Task(
                    "GARABAG REVIVAL FUND",
                    id = 0,
                    amount = 999000000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Guliyev Ayten Samir",
                    id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Məmmədov Məhəmməd...",
                    id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                )
            )
        ), TaskDate(
            date = "18 Februrary 2023", tasks = listOf(
                Task(
                    "GARABAG REVIVAL FUND",
                    id = 0,
                    amount = 999000000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Guliyev Ayten Samir",
                    id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Məmmədov Məhəmməd...",
                    id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                )
            )
        )

    )

}