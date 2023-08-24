package com.app.uikit.models

import androidx.compose.ui.graphics.vector.ImageVector

data class FilterModel(
    val type: FilterType,
    val title: String,
    val icon: ImageVector,
    var isSelected: Boolean
)