package com.soopeach.reddy.presentation.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.Reddy_500

@Composable
fun ReddyBasicButton(text: String, onButtonClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(14.dp))
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Reddy_500)
            .clickable {
                onButtonClicked()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = Body1)
    }
}