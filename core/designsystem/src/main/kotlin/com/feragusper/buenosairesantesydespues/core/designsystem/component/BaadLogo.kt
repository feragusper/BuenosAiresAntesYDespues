package com.feragusper.buenosairesantesydespues.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val Sepia = Color(0xFFD7BE9C)
private val Teal = Color(0xFF79A9B1)
private val Brand = Color(0xFF15242B)

/**
 * The app's before/after mark: a framed photo split by the comparison slider.
 * [sliderFraction] (0..1) positions the divider, so callers can animate it.
 */
@Composable
fun BaadLogo(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    sliderFraction: Float = 0.5f,
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val radius = CornerRadius(w * 0.14f, w * 0.14f)
        val fraction = sliderFraction.coerceIn(0f, 1f)

        // "Después" (teal) fills the whole photo.
        drawRoundRect(color = Teal, cornerRadius = radius)

        // "Antes" (sepia) covers the left portion up to the slider.
        clipRect(right = w * fraction) {
            drawRoundRect(color = Sepia, cornerRadius = radius)
        }

        // Divider.
        val dividerW = w * 0.035f
        drawRect(
            color = Color.White,
            topLeft = Offset(w * fraction - dividerW / 2f, 0f),
            size = Size(dividerW, h),
        )

        // Handle.
        val handleR = w * 0.11f
        drawCircle(color = Color.White, radius = handleR, center = Offset(w * fraction, h / 2f))
        drawCircle(color = Brand, radius = handleR * 0.42f, center = Offset(w * fraction, h / 2f))
    }
}
