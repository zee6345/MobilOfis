package com.app.transfer.transferDetails


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color

object DataClassTransfer {
    val topMenuList = listOf(
        MainCardTransfer(
            title = "Signature\n" +
                    "Waiting",
            color = Color(0xff2CCAD3),
            number = 3
        ),
        MainCardTransfer(
            title = "Execution\n" +
                    "done",
            color = Color(0xff26D978),
            number = 3
        ),
        MainCardTransfer(
            title = "Signature and\n" +
                    "confirmation",
            color = Color(0xff268ED9),
            number = 2
        ),
        MainCardTransfer(
            title = "Sent to \nBank",
            color = Color(0xFFF48A1D),
            number = 1
        ),
        MainCardTransfer(
            title = "Not\n Processed",
            color = Color(0xFFC74375),
            number = 1
        ),

   )
    val filtersModelList = listOf(
        FilterModel("date","This Week" , Icons.Filled.ArrowDropDown,false),
        FilterModel("account","From the account", Icons.Filled.ArrowDropDown,false),
        FilterModel("type","Type",Icons.Filled.ArrowDropDown,false),
        FilterModel("field","Field",Icons.Filled.Search,false),
        FilterModel("appointment","Appointment",Icons.Filled.Search,false),
        FilterModel("amount","Amount",Icons.Filled.Search,false),
        FilterModel("currency","Currency",Icons.Filled.ArrowDropDown,false),
        FilterModel("status","Status",Icons.Filled.ArrowDropDown,false),

    )

    val taskDates = listOf(
        TaskDate(
            date = "18 Februrary 2023",
            tasks = listOf(
                Task("GARABAG REVIVAL FUND"
            , id = 0,
                amount = 999000000.00,
                status = "Execution done",
                paymentBy = "AniPay - Non-budget",
                timeSmall = "18:24"),

                Task("Guliyev Ayten Samir"
                    , id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"),

                Task("Məmmədov Məhəmməd..."
                    , id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24")
            )
        ),
         TaskDate(
            date = "18 Februrary 2023",
            tasks = listOf(
                Task("GARABAG REVIVAL FUND"
            , id = 0,
                amount = 999000000.00,
                status = "Execution done",
                paymentBy = "AniPay - Non-budget",
                timeSmall = "18:24"),

                Task("Guliyev Ayten Samir"
                    , id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"),

                Task("Məmmədov Məhəmməd..."
                    , id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24")
            )
        )

    )

}