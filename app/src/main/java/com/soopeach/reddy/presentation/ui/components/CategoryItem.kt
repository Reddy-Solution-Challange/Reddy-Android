package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.domain.model.RecycleItem
import com.soopeach.reddy.presentation.ui.theme.Body3

@Composable
fun CategoryItem(
    recycleItem: RecycleItem,
    onClicked: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(72.dp),
            shape = RoundedCornerShape(14.dp),
            elevation = 4.dp,
        ) {
            Box {
                Column(
                    Modifier
                        .fillMaxSize()
                        .clickable {
                            onClicked()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = recycleItem.image),
                        contentDescription = "재활용 아이템 아이콘"
                    )

                }
            }
        }
        Spacer(modifier = Modifier.size(6.dp))

        Text(
            text = recycleItem.text,
            style = Body3
        )
    }
}