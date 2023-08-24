package com.app.network.models.responseModels

data class NewBusinessCardsX(
    val MainCards: List<MainCard>,
    val MsgRemark: String,
    val MsgStatus: String
)