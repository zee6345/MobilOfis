package com.app.network.models.responseModels

data class Permission(
    val permission: String,
    val trn_group: String,
    val trn_sname: String,
    val trn_type: Int
)