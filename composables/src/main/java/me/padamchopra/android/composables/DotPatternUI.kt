package me.padamchopra.android.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import me.padamchopra.android.design.extensions.applyAlpha
import me.padamchopra.android.design.theme.AppTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DotPatternUI(
    modifier: Modifier = Modifier,
    dotSize: Dp = 3.dp,
    spacing: Dp = 18.dp,
    containerColor: Color = MaterialTheme.colorScheme.background,
    color: Color = MaterialTheme.colorScheme.onBackground,
    shimmerSpeed: Double = 2.0,
    dxFactor: Double = 0.25,
    dyFactor: Double = 0.21,
    baseAlpha: Double = 0.2,
    alphaMultiplier: Double = 3.0
) {
    val density = LocalDensity.current
    val time = remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(Unit) {
        while (isActive) {
            time.doubleValue = System.currentTimeMillis() / 1000.0
            delay(16)
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(containerColor)
    ) {
        val dotPx = with(density) { dotSize.toPx() }
        val spacingPx = with(density) { spacing.toPx() }

        val cols = (size.width / spacingPx).toInt() + 2
        val rows = (size.height / spacingPx).toInt() + 2

        for (row in 0..rows) {
            for (col in 0..cols) {
                val x = col * spacingPx
                val y = row * spacingPx

                val hash = ((row * 73856093) xor (col * 19349663)).toUInt()
                val phase = ((hash and 0xFFu).toDouble() / 255.0) * PI * 2
                val freq = 0.7 + ((hash shr 8 and 0xFFu).toDouble() / 255.0)

                val dx = col * dxFactor
                val dy = row * dyFactor
                val baseWave = sin(time.doubleValue * 0.6 + dx) + cos(time.doubleValue * 0.4 + dy)

                val pulse = sin(time.doubleValue * shimmerSpeed * freq + phase)
                val blended = (pulse + baseWave) / 4.0
                val alpha = (baseAlpha + alphaMultiplier * abs(blended)).coerceIn(0.0, 1.0)

                drawCircle(
                    color = color.copy(alpha = color.alpha.times(alpha.toFloat())),
                    radius = dotPx / 2,
                    center = Offset(x, y)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun Preview_DotPatternUI() {
    var colorOpacity by remember { mutableFloatStateOf(0.4f) }
    var shimmerSpeed by remember { mutableDoubleStateOf(2.0) }
    var dxFactor by remember { mutableDoubleStateOf(0.25) }
    var dyFactor by remember { mutableDoubleStateOf(0.21) }
    var baseAlpha by remember { mutableDoubleStateOf(0.2) }
    var alphaMultiplier by remember { mutableDoubleStateOf(3.0) }

    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DotPatternUI(
                dotSize = 3.dp,
                spacing = 18.dp,
                color = MaterialTheme.colorScheme.onBackground.applyAlpha(colorOpacity),
                shimmerSpeed = shimmerSpeed,
                dxFactor = dxFactor,
                dyFactor = dyFactor,
                baseAlpha = baseAlpha,
                alphaMultiplier = alphaMultiplier
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(AppTheme.sizes.padding16)
                    .navigationBarsPadding()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
                    .padding(AppTheme.sizes.padding16)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(AppTheme.sizes.padding24)
            ) {
                SliderRow(colorOpacity, 0.1f, 1f) { colorOpacity = it }
                SliderRow(shimmerSpeed.toFloat(), 0.5f, 5f) { shimmerSpeed = it.toDouble() }
                SliderRow(dxFactor.toFloat(), 0.1f, 1f) { dxFactor = it.toDouble() }
                SliderRow(dyFactor.toFloat(), 0.1f, 1f) { dyFactor = it.toDouble() }
                SliderRow(baseAlpha.toFloat(), 0f, 1f) { baseAlpha = it.toDouble() }
                SliderRow(alphaMultiplier.toFloat(), 0f, 10f) { alphaMultiplier = it.toDouble() }
            }
        }
    }
}

@Composable
private fun SliderRow(
    value: Float,
    min: Float,
    max: Float,
    onValueChange: (Float) -> Unit
) {
    val thumbColor = MaterialTheme.colorScheme.primary
    val activeTrack = MaterialTheme.colorScheme.primary
    val inactiveTrack = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.sizes.padding8)
    ) {
        Text(
            text = "%.2f".format(value),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Slider(
            modifier = Modifier
                .weight(1f)
                .height(8.dp),
            value = value,
            onValueChange = onValueChange,
            valueRange = min..max,
            colors = SliderDefaults.colors(
                thumbColor = thumbColor,
                activeTrackColor = activeTrack,
                inactiveTrackColor = inactiveTrack
            )
        )

        Text(
            text = "%.2f".format(value),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
