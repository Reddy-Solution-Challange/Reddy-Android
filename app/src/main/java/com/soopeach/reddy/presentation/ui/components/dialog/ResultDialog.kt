package com.soopeach.reddy.presentation.ui.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.components.button.ReddyBasicButton
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun ResultDialog(
    content: String,
    onDismissRequest: () -> Unit = {},
    onCloseClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {

        Column(
            Modifier
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onCloseClicked()
                        },
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "close"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(White)
                    .padding(horizontal = 35.dp)
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    modifier = Modifier.size(160.dp),
                    painter = painterResource(id = R.drawable.character_depression),
                    contentDescription = "character"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = content,
                    style = Body1,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            ReddyBasicButton(
                text = "Detail",
                onButtonClicked = {
                    onButtonClicked()
                }
            )
        }

    }
}