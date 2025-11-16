package com.miuh.clubs.ui.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.miuh.clubs.ui.theme.backgroundDark
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier
) {
    // Embedded Advice: Use MaterialTheme colors and shapes for consistency.
    val themePrimary = MaterialTheme.colorScheme.primary
    val themeSurface = MaterialTheme.colorScheme.surface
    val themeOnPrimary = MaterialTheme.colorScheme.onPrimary

    // Use a Backdrop Surface/Box that is clickable = false to consume touches when loading.
    Surface(
        modifier = modifier
            .fillMaxSize()
            // Using surface color for a better look; backgroundDark is likely a fixed color.
            .background(themeSurface),
        color = themeSurface // Explicitly set Surface color if needed
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "RotationTransition")

        // 1. IMPROVEMENT: Animate the rotation angle for the dynamic arc.
        val angle by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = LinearEasing), // Faster rotation is often better
                repeatMode = RepeatMode.Restart
            ),
            label = "AngleAnimation"
        )

        // 2. IMPROVEMENT: Animate a scaling factor for a 'pulsing' effect on the text/base.
        val scale by infiniteTransition.animateFloat(
            initialValue = 0.9f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "ScaleAnimation"
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // 3. REFACTOR: Use a reasonably sized modifier for the animation container.
            val animationSize = 120.dp

            Box(
                modifier = Modifier
                    .size(animationSize * 3) // Make the surrounding box large enough for touch area
                    .padding(24.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale) // Apply the pulsing scale animation
            ) {
                // Background Circle (Stationary Track)
                Canvas(
                    modifier = Modifier
                        .matchParentSize() // Use all available space
                        .align(Alignment.Center),
                    onDraw = {
                        drawCircle(
                            color = themePrimary.copy(alpha = 0.2f), // Lighter, themed background track
                            style = Stroke(width = 8f)
                        )
                    }
                )

                // Rotating Arc (The actual loader)
                Canvas(
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.Center)
                        .rotate(angle) // 4. CRITICAL FIX: Rotate the Canvas modifier
                    ,
                    onDraw = {
                        drawArc(
                            color = themePrimary, // Use theme primary color
                            style = Stroke(
                                width = 10f, // Thicker stroke
                                cap = StrokeCap.Round
                            ),
                            startAngle = 270f, // Start at the top (better visual)
                            sweepAngle = 90f, // Maintain a sweep angle (360/4f)
                            useCenter = false
                        )
                    }
                )

                // Loading Text (Centered)
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Loading...",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = themePrimary,
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingAnimationPreview(
) {
    Box {
        LoadingAnimation()
    }

}
