package com.soopeach.reddy.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.soopeach.reddy.BuildConfig
import com.soopeach.reddy.data.model.request.RequestToken
import com.soopeach.reddy.data.service.UserService
import com.soopeach.reddy.domain.usecase.SignUpUseCase
import com.soopeach.reddy.presentation.model.LoginState
import com.soopeach.reddy.presentation.ui.screens.LoginScreen
import com.soopeach.reddy.presentation.ui.screens.ReddySplashScreen
import com.soopeach.reddy.presentation.ui.theme.ReddyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.get

val LocalAuth = compositionLocalOf { Firebase.auth }

class MainActivity : ComponentActivity() {

    private val auth: FirebaseAuth = Firebase.auth

    private val loginSuccessScope = CoroutineScope(Dispatchers.IO)

    private val loginFlow: MutableSharedFlow<LoginState> =
        MutableStateFlow(if (auth.currentUser != null) LoginState.LoggedIn else LoginState.LoggedOut)

    private val requestPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted.not()) {
            finish()
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Do nothing
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> {
                // TODO: dialog
            }

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReddyTheme {

                val signUpUseCase = get<SignUpUseCase>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    CompositionLocalProvider(LocalAuth provides auth) {

                        SetStatusBarColor(Color.White)

                        var isSplashScreenVisible by remember { mutableStateOf(true) }
                        val coroutineScope = rememberCoroutineScope()

                        val scope = rememberCoroutineScope()

                        if (isSplashScreenVisible) {
                            ReddySplashScreen()

                            coroutineScope.launch {
                                delay(1500)
                                isSplashScreenVisible = false
                            }
                        } else {

                            val loginState by loginFlow.collectAsStateWithLifecycle(
                                if (auth.currentUser != null) LoginState.LoggedIn else LoginState.LoggedOut
                            )

                            when (loginState) {
                                is LoginState.LoggedIn -> {
                                    AppScreen{
                                        auth.signOut()
                                        loginFlow.tryEmit(LoginState.LoggedOut)
                                    }
                                }

                                is LoginState.LoggedOut -> {

                                    val requestSignIn =
                                        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->

                                            if (result.resultCode == RESULT_OK) {
                                                val intent = result.data
                                                if (intent != null) {

                                                    val credential = Identity.getSignInClient(this)
                                                        .getSignInCredentialFromIntent(intent)

                                                    val idToken = credential.googleIdToken

                                                    val firebaseCredential =
                                                        GoogleAuthProvider.getCredential(
                                                            idToken,
                                                            null
                                                        )

                                                    auth.signInWithCredential(firebaseCredential)
                                                        .addOnCompleteListener(this) { task ->
                                                            if (task.isSuccessful) {

                                                                loginSuccessScope.launch {
                                                                    loginFlow.tryEmit(LoginState.LoggingIn)
                                                                    signUpUseCase.invoke(idToken!!)
                                                                    loginFlow.tryEmit(LoginState.LoggedIn)
                                                                }

                                                            } else {
                                                                Log.w(
                                                                    "LoginTaskResult",
                                                                    "signInWithCredential:failure",
                                                                    task.exception
                                                                )
                                                            }
                                                        }

                                                } else {
                                                    Log.d("Login", "No Intent Data")
                                                }
                                            } else {
                                                Log.d("Login", "No Result")
                                            }
                                        }

                                    LoginScreen {
                                        scope.launch {
                                            val request = GetSignInIntentRequest.builder()
                                                .setServerClientId(BuildConfig.OAuthClientID)
                                                .build()

                                            val intent = Identity.getSignInClient(this@MainActivity)
                                                .getSignInIntent(request).await()
                                            requestSignIn.launch(
                                                IntentSenderRequest.Builder(intent)
                                                    .build()
                                            )
                                        }
                                    }
                                }

                                is LoginState.LoggingIn -> {

                                }

                            }

                        }

                    }
                }
            }
        }

        requestCameraPermission()
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}