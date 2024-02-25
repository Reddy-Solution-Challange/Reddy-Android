package com.soopeach.reddy.presentation.ui.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun LogoTopBar(
    isElevation: Boolean = true,
    menu: @Composable (() -> Unit)? = null,
) {

    Surface(
        
        elevation = if (isElevation) 4.dp else 0.dp,
    ) {
        Row(
            modifier = Modifier
                .background(White)
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .width(38.dp)
                    .height(24.dp),
                painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
            )

            menu?.invoke()

        }
    }
}