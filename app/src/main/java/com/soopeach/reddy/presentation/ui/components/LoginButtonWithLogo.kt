package com.soopeach.reddy.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.theme.Grey_300

@Composable
fun LoginButtonWithLogo(onLoginButtonClicked: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .width(93.dp)
                .height(58.dp),
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
        )

        Spacer(modifier = Modifier.height(14.dp))

        Image(
            modifier = Modifier
                .width(119.dp)
                .height(44.dp),
            painter = painterResource(id = R.drawable.logo_word), contentDescription = "logo"
        )

        Spacer(modifier = Modifier.height(96.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 52.dp)
                .fillMaxWidth()
                .height(58.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .border(1.dp, color = Grey_300, shape = RoundedCornerShape(100.dp))
                .clickable {
                    onLoginButtonClicked()
                }
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Image(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                painter = painterResource(id = R.drawable.google_logo), contentDescription = "구글 로고"
            )

            Text(
                text = "구글로 로그인", modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 17.dp),
                textAlign = TextAlign.Center
            )
        }


    }
}