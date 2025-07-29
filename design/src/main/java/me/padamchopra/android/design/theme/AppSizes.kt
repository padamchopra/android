package me.padamchopra.android.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppSizes(
    // It is a bad practice to use explicit sizes in the name
    // but I have often found myself needing to reference these sizes
    // so just use size and if there's ever a need to change something in future for a type,
    // right-click and reformat :)
    val icon8: Dp = 8.dp,
    val icon16: Dp = 16.dp,
    val icon24: Dp = 24.dp,
    val icon40: Dp = 40.dp,

    val divider: Dp = 1.dp,

    val padding2: Dp = 2.dp,
    val padding4: Dp = 4.dp,
    val padding8: Dp = 8.dp,
    val padding12: Dp = 12.dp,
    val padding16: Dp = 16.dp,
    val padding24: Dp = 24.dp,
    val padding32: Dp = 32.dp,
    val padding40: Dp = 40.dp
)

internal val LocalAppSizes = staticCompositionLocalOf { AppSizes() }
