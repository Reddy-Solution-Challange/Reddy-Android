package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.Grey_300
import com.soopeach.reddy.presentation.ui.theme.Grey_600

@Composable
fun RecentlySearchedItem(
    text: String,
    onClicked: (String) -> Unit = {},
    onCloseClicked: (String) -> Unit = {},
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp)
            .clickable {
                onClicked(text)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Body1.copy(color = Grey_600),
            textAlign = TextAlign.Start
        )

        Icon(
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    onCloseClicked(text)
                },
            painter = painterResource(id = R.drawable.close), contentDescription = "close",
            tint = Grey_300
        )
    }
}