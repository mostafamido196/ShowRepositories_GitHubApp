package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun RingWithPoint() {
    val ringRadius = 60f // Radius of the ring
    val pointRadius = 20f // Radius of the point

    Canvas(modifier = Modifier.size(100.dp)) {
        // Draw the ring (circle with a stroke)
        drawCircle(
            color = Color.Black,
            radius = ringRadius,
            style = Stroke(width = 8f) // Width of the ring
        )

        // Draw the point inside the ring
        drawCircle(
            color = Color.Black,
            radius = pointRadius,
            center = Offset(center.x, center.y) // Center of the ring
        )
    }
}
