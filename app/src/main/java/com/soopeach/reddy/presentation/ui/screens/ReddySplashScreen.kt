package com.soopeach.reddy.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R

@Composable
fun ReddySplashScreen() {

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF02F6BF), Color(0xFF01E3F7)),
                ),
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(93.dp)
                    .height(58.dp),
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "Reddy Logo"
            )

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                modifier = Modifier
                    .width(119.dp)
                    .height(44.dp),
                painter = painterResource(id = R.drawable.logo_word_white),
                contentDescription = "Reddy Text"
            )
        }
    }
}