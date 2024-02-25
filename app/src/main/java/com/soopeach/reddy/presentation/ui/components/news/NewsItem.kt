package com.soopeach.reddy.presentation.ui.components.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Caption
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Grey_900

@Composable
fun NewsItem(
    imageUrl: String,
    content: String,
    commentCount: Int,
    onClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .shadow(1.dp, shape = RoundedCornerShape(14.dp))
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClicked() },
        elevation = 2.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(14.dp)),
                model = imageUrl, contentDescription = "news",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp, end = 20.dp, top = 18.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = content,
                    maxLines = 2,
                    style = Body2.copy(Grey_900)
                )

//                Row(
//                    modifier = Modifier.clickable {
//
//                    },
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        modifier = Modifier.size(21.dp),
//                        painter = painterResource(id = R.drawable.comment),
//                        contentDescription = "comment icon",
//                        tint = Grey_400
//                    )
//                    Text(text = commentCount.toString(), style = Caption.copy(Grey_400))
//                }
            }
        }
    }
}