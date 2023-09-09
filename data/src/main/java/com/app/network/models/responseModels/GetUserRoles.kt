package com.app.network.models.responseModels

data class GetUserRoles(
    val approve: Boolean,
    val save: Boolean,
    val send: Boolean,
    val sign: Boolean
)