package com.app.adjustment.data

import com.app.adjustment.R
import com.app.adjustment.companies.companylist.CompanyListName


object DataProvider {

    val companyList =
        listOf(
            CompanyListName(
                name = "International Real Estate and Valuation Agency LLC",
            ),
            CompanyListName(name = "Value Services MMC"),
            CompanyListName(name = "Səba MMC"),
        )


    val exchangeList = listOf(
        ExchangeRatesModel(
            country_name = "USD",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "EUR",
            purchase_icon = R.drawable.ic_arrow_down,
            purchase_rate = "1.947",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.98",
            bank_icon = R.drawable.ic_arrow_down,
            bank_rate = "1.947"
        ),
        ExchangeRatesModel(
            country_name = "RUB",
            purchase_icon = R.drawable.ic_arrow_up,
            purchase_rate = "0.0234",
            sale_icon = R.drawable.ic_arrow_down,
            sale_rate = "0.0244",
            bank_icon = R.drawable.ic_arrow_up,
            bank_rate = "0.0234"
        ),
        ExchangeRatesModel(
            country_name = "GBP",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_rectangle,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "JPY",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "UAH",
            purchase_icon = R.drawable.ic_arrow_down,
            purchase_rate = "1.947",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.98",
            bank_icon = R.drawable.ic_arrow_down,
            bank_rate = "1.947"
        ),
        ExchangeRatesModel(
            country_name = "ZEF",
            purchase_icon = R.drawable.ic_arrow_up,
            purchase_rate = "0.0234",
            sale_icon = R.drawable.ic_arrow_down,
            sale_rate = "0.0244",
            bank_icon = R.drawable.ic_arrow_up,
            bank_rate = "0.0234"
        ),
        ExchangeRatesModel(
            country_name = "CHF",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "2.288",
            sale_icon = R.drawable.ic_rectangle,
            sale_rate = "2.358",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "2.288"
        ),
    )
}