package com.example.erpsystem.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.DeepNavy
import com.example.erpsystem.ui.theme.SoftCream
import kotlinx.coroutines.delay

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun SplashScreen(onNavigateToWelcome: () -> Unit) {
    // Animation states for a smooth entrance scale
    val scale = remember { Animatable(0.6f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        // Run scale and alpha fade simultaneously over 1 second
        scale.animateTo(1f, animationSpec = tween(durationMillis = 1000))
        alpha.animateTo(1f, animationSpec = tween(durationMillis = 800))

        delay(1500) // Hold execution state briefly for a premium feel
        onNavigateToWelcome() // Exit node trigger
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftCream),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value).alpha(alpha.value)
        ) {
            Text(
                text = "CAMPUS ERP",
                fontSize = 38.sp,
                fontWeight = Bold,
                color = DeepNavy,
                letterSpacing = 2.sp,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Management & Control Portal",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}