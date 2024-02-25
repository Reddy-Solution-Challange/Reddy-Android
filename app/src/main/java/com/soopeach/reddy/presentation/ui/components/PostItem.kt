package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun PostItem(imageUrl: String, content: String, onPostClicked: () -> Unit = {}) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onPostClicked()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "post image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(White.copy(alpha = 0.3f))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            text = content, style = Body2.copy(color = White),
            textAlign = TextAlign.Right
        )
    }
}