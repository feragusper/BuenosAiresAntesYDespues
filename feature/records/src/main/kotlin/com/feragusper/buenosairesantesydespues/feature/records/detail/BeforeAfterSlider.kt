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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

/**
 * Before/after image comparison, mirroring the website's img-comparison-slider.
 *
 * Both images occupy the exact same box (same size, same [ContentScale.Crop] framing) and are
 * stacked. Only the *drawing* of the top ("before") image is clipped to the slider fraction, so
 * neither image ever moves or re-frames while dragging — the two stay pixel-aligned and only the
 * reveal boundary moves.
 */
@Composable
fun BeforeAfterSlider(
    beforeUrl: String?,
    afterUrl: String?,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.clipToBounds()) {
        val boxWidth = maxWidth
        var fraction by remember { mutableFloatStateOf(0.5f) }

        // "After" image — full, underneath.
        AsyncImage(
            model = afterUrl,
            contentDescription = "Después",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        // "Before" image — same box/framing, but only painted up to `fraction` of the width.
        AsyncImage(
            model = beforeUrl,
            contentDescription = "Antes",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    clipRect(right = size.width * fraction) {
                        this@drawWithContent.drawContent()
                    }
                },
        )

        // Divider handle.
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = boxWidth * fraction - 1.dp)
                .fillMaxHeight()
                .width(2.dp)
                .background(Color.White),
        )

        // Drag surface on top.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, _ ->
                        change.consume()
                        fraction = (change.position.x / size.width).coerceIn(0f, 1f)
                    }
                },
        )
    }
}
