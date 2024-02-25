package com.soopeach.reddy.presentation.ui.screens.camera

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soopeach.reddy.domain.Base64Utils
import com.soopeach.reddy.presentation.ui.components.dialog.ProgressDialog
import com.soopeach.reddy.presentation.ui.components.dialog.ResultDialog
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.viewmodel.CheckSideEffect
import com.soopeach.reddy.presentation.viewmodel.CheckViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

sealed interface CheckScreenDialogState {
    data object Idle : CheckScreenDialogState
    data object Loading : CheckScreenDialogState
    data object Done : CheckScreenDialogState
}

@Composable
fun CheckScreen(
    navController: NavController,
    imageUri: String
) {

    var dialogState by remember { mutableStateOf<CheckScreenDialogState>(CheckScreenDialogState.Idle) }

    val viewModel = koinViewModel<CheckViewModel>()
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        dialogState = when (sideEffect) {
            is CheckSideEffect.Loading -> {
                CheckScreenDialogState.Loading
            }

            is CheckSideEffect.Done -> {
                CheckScreenDialogState.Done
            }
        }

    }

    val localContentResolver = LocalContext.current.contentResolver

    val scope = rememberCoroutineScope()

    when (dialogState) {
        CheckScreenDialogState.Loading -> {
            ProgressDialog("Loading...")
        }

        CheckScreenDialogState.Done -> {
            ResultDialog(
                content = state.checkResult.getDataOrNull()!!.description,
                onDismissRequest = { dialogState = CheckScreenDialogState.Idle },
                onCloseClicked = { dialogState = CheckScreenDialogState.Idle },
                onButtonClicked = {
                    navController.navigate("result")
                    dialogState = CheckScreenDialogState.Idle
                }
            )
        }

        CheckScreenDialogState.Idle -> {

        }
    }

    val configuration = LocalConfiguration.current
    val targetHeight = (configuration.screenHeightDp - (configuration.screenWidthDp * 4) / 3) / 2

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Base64Utils.decode(imageUri))
                .build(),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(targetHeight.dp)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        text = "Cancel",
                        style = Body1.copy(Grey_400),
                    )
                    Text(
                        modifier = Modifier.clickable {

                            scope.launch {

                                val inputStream = localContentResolver.openInputStream(
                                    Uri.parse(
                                        Base64Utils.decode(imageUri)
                                    )
                                )
                                viewModel.checkHowToRecycle(inputStream!!)

                            }

                        },
                        text = "Check",
                        style = Body1.copy(Grey_400),
                    )
                }
            }


        }

    }
}