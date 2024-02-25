package com.soopeach.reddy.presentation.model

sealed interface LoginState {
    data object LoggedIn : LoginState
    data object LoggedOut : LoginState
    data object LoggingIn : LoginState
}