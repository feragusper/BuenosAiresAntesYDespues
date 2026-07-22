package com.feragusper.buenosairesantesydespues.feature.records.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

/**
 * Before/after image comparison. The "after" image fills the box; the "before" image is
 * revealed from the left up to a draggable vertical divider — the Compose equivalent of the
 * original SlideImageView.
 */
@Composable
fun BeforeAfterSlider(
    beforeUrl: String?,
    afterUrl: String?,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.clipToBounds()) {
        val boxWidth = maxWidth
        val maxWidthPx = with(LocalDensity.current) { boxWidth.toPx() }
        var fraction by remember { mutableFloatStateOf(0.5f) }

        // After image (full background).
        AsyncImage(
            model = afterUrl,
            contentDescription = "Después",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        // Before image, clipped to the current fraction but rendered at full width so it lines up.
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(boxWidth * fraction)
                .clipToBounds(),
        ) {
            AsyncImage(
                model = beforeUrl,
                contentDescription = "Antes",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(boxWidth)
                    .fillMaxHeight(),
            )
        }

        // Divider handle.
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .offset(x = boxWidth * fraction - 1.dp)
                .width(2.dp)
                .background(Color.White)
                .align(Alignment.CenterStart),
        )

        // Drag surface.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(maxWidthPx) {
                    detectHorizontalDragGestures { change, _ ->
                        change.consume()
                        fraction = (change.position.x / maxWidthPx).coerceIn(0f, 1f)
                    }
                },
        )
    }
}
