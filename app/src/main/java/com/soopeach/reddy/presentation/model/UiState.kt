package com.soopeach.reddy.presentation.model

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>

    fun getDataOrNull(): T? = when (this) {
        is Idle -> null
        is Loading -> null
        is Success -> data
        is Error -> null
    }

}