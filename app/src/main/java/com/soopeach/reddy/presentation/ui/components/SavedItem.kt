package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.reddy.presentation.ui.theme.Body4

@Composable
fun SavedItem(imageUrl: String, content: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                onClick()
            },
        elevation = 4.dp,
    ) {
        Column(
            Modifier
                .padding(14.dp)

        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp)),
                model = imageUrl, contentDescription = "뉴스 이미지",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = content, style = Body4
            )

        }
    }
}