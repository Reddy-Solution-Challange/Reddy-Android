package com.soopeach.reddy.presentation.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400

@Composable
fun SearchBar(
    inputText: String,
    onValueChange: (String) -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .background(Grey_200)
            .padding(10.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.search), contentDescription = "검색 아이콘",
            tint = Grey_400
        )

        Spacer(modifier = Modifier.width(6.dp))

        BasicTextField(
            value = inputText,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = Body2,
            singleLine = true
        ) { innerTextField ->
            innerTextField()

            if (inputText.isEmpty()) {
                Text(
                    text = "궁금한 재활용을 검색해보세요.",
                    style = Body2.copy(
                        color = Grey_400
                    ),
                )
            }
        }

    }
}