package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400

@Composable
fun SearchBarButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .clickable {
                onClicked()
            }
            .background(Grey_200)
            .padding(10.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.search), contentDescription = "검색 아이콘",
            tint = Grey_400
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "궁금한 재활용을 검색해보세요.",
            style = Body2.copy(
                color = Grey_400
            ),
        )

    }
}