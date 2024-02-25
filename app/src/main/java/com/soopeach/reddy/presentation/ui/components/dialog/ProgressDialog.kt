package com.soopeach.reddy.presentation.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.soopeach.reddy.presentation.ui.theme.Black
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.Reddy_500

@Composable
fun ProgressDialog(
    text: String,
    onDismissRequest: () -> Unit = {}
) {


    Dialog(onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1.3f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Reddy_500)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = text,
                style = Body1,
                color = Black,
                textAlign = TextAlign.Center
            )
        }
    }
}