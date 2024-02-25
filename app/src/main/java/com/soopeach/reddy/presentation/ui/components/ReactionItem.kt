package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.domain.model.Reactions
import com.soopeach.reddy.presentation.ui.theme.Body3

@Composable
fun ReactionItem(
    reaction: Reactions,
    isSelected: Boolean = false,
    onClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(50.dp)
                .clickable {
                    onClicked()
                },
            painter = painterResource(id =
            if (isSelected) reaction.selectedImage else reaction.unSelectedImage), contentDescription = "reaction")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reaction.text, style = Body3)
    }
}