package com.soopeach.reddy.presentation.ui.screens.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.LocalAuth
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Title2

@Composable
fun MyPageScreen(navController: NavController, onSignOutClicked: () -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(modifier = Modifier.height(84.dp))
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.paper_man),
                contentDescription = "Character",
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(text = "PAPERMAN", style = Body2.copy(Grey_400))

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = LocalAuth.current.currentUser?.displayName ?: "anonymous",
                style = Title2
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Grey_200)
            )
        }

        item {
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSignOutClicked()
                    }
                    .padding(20.dp)) {
                    Text(text = "Sign Out")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Grey_200)
                )
            }
        }

        item {
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSignOutClicked()
                    }
                    .padding(20.dp)) {
                    Text(text = "Delete Account")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Grey_200)
                )
            }
        }

    }

}