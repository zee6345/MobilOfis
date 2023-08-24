package com.app.network.models.responseModels.transferModels

class MtCountriesResponse : ArrayList<MtCountriesResponseItem>()

data class MtCountriesResponseItem(
    val CODE: String,
    val NAME: String
)