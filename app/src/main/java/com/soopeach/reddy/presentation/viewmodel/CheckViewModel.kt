package com.soopeach.reddy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.soopeach.reddy.data.model.FirebaseStorageManager
import com.soopeach.reddy.data.model.response.check.CheckedResult
import com.soopeach.reddy.domain.usecase.camera.CheckHowToRecycleUseCase
import com.soopeach.reddy.presentation.model.UiState
import com.soopeach.reddy.presentation.ui.utils.getBytes
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.InputStream

sealed interface CheckSideEffect {
    data object Loading: CheckSideEffect
    data object Done: CheckSideEffect
}

data class CheckState(
    val checkResult: UiState<CheckedResult> = UiState.Idle,
    val imageUrl: UiState<String> = UiState.Idle
)

class CheckViewModel(
    private val checkHowToRecycleUseCase: CheckHowToRecycleUseCase
) : ViewModel(), ContainerHost<CheckState, CheckSideEffect> {

    override val container: Container<CheckState, CheckSideEffect> = container(CheckState())

    fun checkHowToRecycle(inputStream: InputStream) = intent {

        postSideEffect(CheckSideEffect.Loading)

        val imageUrl = FirebaseStorageManager.uploadImage(getBytes(inputStream))
        val result = checkHowToRecycleUseCase.checkHowToRecycle(imageUrl)

        reduce {
            state.copy(
                checkResult = UiState.Success(result),
                imageUrl = UiState.Success(imageUrl)
            )
        }

        inputStream.close()
        postSideEffect(CheckSideEffect.Done)

    }

}