package com.soopeach.reddy.presentation.ui.components.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.reddy.presentation.ui.theme.Body3
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun SquareNewsItem(imageUrl: String, content: String) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl, contentDescription = "뉴스 이미지",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = content, style = Body3.copy(color = White))
        }
    }

}