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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.miuh.clubs.ui.theme.backgroundDark
import com.miuh.clubs.ui.theme.backgroundLight
import com.miuh.clubs.ui.theme.onPrimaryLight
import com.miuh.clubs.ui.theme.onSurfaceLight
import com.miuh.clubs.ui.theme.primaryLight
import com.miuh.clubs.ui.theme.surfaceLight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    /* --------------------------------------------------------------------- */
    /* 1.  Theming helpers (pick surface / primary from your palette)        */
    /* --------------------------------------------------------------------- */
    val surface = MaterialTheme.colorScheme.surface
    val onSurface = MaterialTheme.colorScheme.onSurface
    val primary = MaterialTheme.colorScheme.primary

    /* --------------------------------------------------------------------- */
    /* 2.  Animations                                                        */
    /* --------------------------------------------------------------------- */
    val infinite = rememberInfiniteTransition(label = "loader")

    // 2a. Rotation of the 3 segments
    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // 2b. Elastic scale on the whole card
    val scale by infinite.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    // 2c. Shimmer gradient offset for the text
    val shimmer by infinite.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    /* --------------------------------------------------------------------- */
    /* 3.  Glass card (blur + tint)                                          */
    /* --------------------------------------------------------------------- */
    val glassTint = if (MaterialTheme.colorScheme.background == Color(0xFF111318)) {
        // dark
        Color.White.copy(alpha = .06f)
    } else {
        // light
        Color.Black.copy(alpha = .05f)
    }

    /* --------------------------------------------------------------------- */
    /* 4.  Layout                                                            */
    /* --------------------------------------------------------------------- */
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .graphicsLayer(scaleX = scale, scaleY = scale)
                    .padding(24.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(glassTint)          // tint
                    .drawWithContent {                // blur
                        drawContent()
                        drawRect(
                            Brush.verticalGradient(
                                0f to glassTint,
                                1f to glassTint
                            ),
                            blendMode = BlendMode.SrcOver
                        )
                    }
                    .padding(horizontal = 32.dp, vertical = 24.dp)
            ) {
                /* ------------------------------------------------------------- */
                /* 4a. Spinner                                                   */
                /* ------------------------------------------------------------- */
                Box(Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                    Canvas(Modifier.matchParentSize()) {
                        val stroke = 7f
                        val radius = size.minDimension / 2 - stroke
                        val segment = 90f                 // length of each arc
                        val gap = 20f                     // gap between arcs
                        val alpha = { idx: Int ->
                            // fade-in / fade-out while travelling
                            val pos = (rotation + idx * (segment + gap)) % 360f
                            when {
                                pos < 180 -> pos / 180f
                                else -> 2 - pos / 180f
                            }.coerceIn(0f, 1f)
                        }

                        for (i in 0..2) {
                            drawArc(
                                color = primary.copy(alpha = alpha(i)),
                                startAngle = rotation + i * (segment + gap),
                                sweepAngle = segment,
                                useCenter = false,
                                topLeft = Offset(
                                    x = center.x - radius,
                                    y = center.y - radius
                                ),
                                size = Size(radius * 2, radius * 2),
                                style = Stroke(width = stroke, cap = StrokeCap.Round)
                            )
                        }
                    }
                }

//                Spacer(Modifier.height(16.dp))
//
//                /* ------------------------------------------------------------- */
//                /* 4b. Shimmer text                                              */
//                /* ------------------------------------------------------------- */
//                val shimmerColors = listOf(
//                    onSurface.copy(alpha = .25f),
//                    onSurface.copy(alpha = .90f),
//                    onSurface.copy(alpha = .25f)
//                )
//
//                // travelling gradient
//                val brush by remember(shimmer) {
//                    derivedStateOf {
//                        Brush.linearGradient(
//                            colors = shimmerColors,
//                            start = Offset(shimmer * 200f, 0f),   // 200f ≈ text width
//                            end = Offset(shimmer * 200f + 200f, 0f),
//                            tileMode = TileMode.Clamp
//                        )
//                    }
//                }
//
//                Text(
//                    text = "Loading …",
//                    style = MaterialTheme.typography.titleMedium,
//                    modifier = Modifier
//                        .drawWithContent {
//                            drawContent()
//                            drawRect(brush = brush, blendMode = BlendMode.SrcIn)
//                        },
//                    color = onSurface
//                )
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
