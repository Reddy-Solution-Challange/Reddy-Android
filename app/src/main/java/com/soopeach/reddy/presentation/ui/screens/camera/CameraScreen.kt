package com.soopeach.reddy.presentation.ui.screens.camera

import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.soopeach.reddy.domain.Base64Utils
import com.soopeach.reddy.presentation.ui.utils.getOutputDirectory
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Composable
fun CameraScreen(navController: NavController) {

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    CameraView(
        outputDirectory = getOutputDirectory(context),
        executor = Executors.newSingleThreadExecutor(),
        onImageCaptured = {
            coroutineScope.launch {
                navController.navigate("check/${Base64Utils.encode(it.toString())}") }
        },
        onError = { },
        onCloseClicked = { navController.popBackStack() }
    )

}


