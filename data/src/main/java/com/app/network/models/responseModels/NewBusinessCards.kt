package com.app.network.models.responseModels

data class NewBusinessCards(
    val MainCards: List<MainCardX>,
    val MsgRemark: Any,
    val MsgStatus: String
)