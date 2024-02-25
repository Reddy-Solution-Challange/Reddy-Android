package com.soopeach.reddy.presentation.ui.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun BackTopBarWithTitle(
    hasElevation: Boolean = true,
    title: String,
    onBackClicked: () -> Unit,
) {
    Surface(
        elevation = if (hasElevation) 4.dp else 0.dp,
    ) {
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Image(
                modifier = Modifier
                    .padding(vertical = 18.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clickable {
                        onBackClicked()
                    },
                painter = painterResource(id = R.drawable.back_arrow), contentDescription = "logo"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = Body1,
                textAlign = TextAlign.Center
            )

        }
    }
}