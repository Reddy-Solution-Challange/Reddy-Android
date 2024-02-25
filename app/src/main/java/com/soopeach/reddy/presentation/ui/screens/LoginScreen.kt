package com.soopeach.reddy.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soopeach.reddy.presentation.ui.components.LoginButtonWithLogo

@Composable
fun LoginScreen(whenLoginClicked: () -> Unit) {

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {

        LoginButtonWithLogo {
            whenLoginClicked()
        }

    }
}