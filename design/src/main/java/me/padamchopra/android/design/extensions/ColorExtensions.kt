package me.padamchopra.android.design.extensions

import androidx.compose.ui.graphics.Color

fun Color.applyAlpha(alpha: Float): Color = copy(alpha = this.alpha.times(alpha))
