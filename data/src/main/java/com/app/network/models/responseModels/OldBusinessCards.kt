package com.app.network.models.responseModels

data class OldBusinessCards(
    val MainCards: List<MainCard>,
    val MsgRemark: Any,
    val MsgStatus: String
)