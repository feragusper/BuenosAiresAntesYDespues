package com.feragusper.buenosairesantesydespues.core.designsystem.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * A shimmering placeholder background for skeleton loading states. Apply to a sized box.
 */
@Composable
fun Modifier.shimmer(shape: Shape = RoundedCornerShape(12.dp)): Modifier {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer-progress",
    )

    val base = Color.White.copy(alpha = 0.06f)
    val highlight = Color.White.copy(alpha = 0.16f)

    return this
        .clip(shape)
        .background(base)
        .drawWithCache {
            val width = size.width
            val sweep = width * 2f
            val start = -sweep + progress * (sweep + width)
            val brush = Brush.linearGradient(
                colors = listOf(Color.Transparent, highlight, Color.Transparent),
                start = Offset(start, 0f),
                end = Offset(start + sweep, size.height),
            )
            onDrawBehind { drawRect(brush) }
        }
}
