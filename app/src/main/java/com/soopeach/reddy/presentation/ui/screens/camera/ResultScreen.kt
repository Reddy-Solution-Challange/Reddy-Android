package com.soopeach.reddy.presentation.ui.screens.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.soopeach.reddy.R
import com.soopeach.reddy.domain.usecase.camera.RequestSaveResultUseCase
import com.soopeach.reddy.presentation.ui.components.dialog.ProgressDialog
import com.soopeach.reddy.presentation.ui.components.dialog.ResultDialog
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBar
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Grey_900
import com.soopeach.reddy.presentation.ui.theme.Title2
import com.soopeach.reddy.presentation.ui.theme.White
import com.soopeach.reddy.presentation.viewmodel.CheckSideEffect
import com.soopeach.reddy.presentation.viewmodel.CheckViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

sealed interface ResultScreenDialogState {
    data object Idle : ResultScreenDialogState
    data object Loading : ResultScreenDialogState
    data object Done : ResultScreenDialogState
}

@Composable
fun ResultScreen(navController: NavController) {

    var dialogState by remember { mutableStateOf<ResultScreenDialogState>(ResultScreenDialogState.Idle) }

    val viewModel = koinViewModel<CheckViewModel>()
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        dialogState = when (sideEffect) {
            is CheckSideEffect.Loading -> {
                ResultScreenDialogState.Loading
            }

            is CheckSideEffect.Done -> {
                ResultScreenDialogState.Done
            }
        }
    }

    when (dialogState) {
        ResultScreenDialogState.Loading -> {
            ProgressDialog("Loading...")
        }

        ResultScreenDialogState.Done -> {

        }

        ResultScreenDialogState.Idle -> {

        }

    }

    val requestSaveResultUseCase = get<RequestSaveResultUseCase>()

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BackTopBar(
                onBackClicked = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "like icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            scope.launch {
                                requestSaveResultUseCase(
                                    state.imageUrl.getDataOrNull()!!,
                                    state.checkResult.getDataOrNull()!!.id.toInt()
                                )

                                navController.navigate("save") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        },
                    tint = Grey_400
                )
            }
        }
    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                modifier = Modifier
                    .width(185.dp)
                    .height(244.dp)
                    .clip(RoundedCornerShape(14.dp)),
                model = state.imageUrl.getDataOrNull()!!, contentDescription = "news",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .shadow(3.dp, shape = RoundedCornerShape(14.dp))
                    .background(White)
                    .padding(16.dp),
            ) {

                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.checkResult.getDataOrNull()!!.title,
                        style = Title2.copy(Grey_900),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.checkResult.getDataOrNull()!!.description,
                        style = Body2.copy(Grey_900),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }

}