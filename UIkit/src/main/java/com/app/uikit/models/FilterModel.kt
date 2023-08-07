package com.app.uikit.models

import androidx.compose.ui.graphics.vector.ImageVector

data class FilterModel(
    val id: String,
    val title: String,
    val icon: ImageVector,
    var isSelected: Boolean
)