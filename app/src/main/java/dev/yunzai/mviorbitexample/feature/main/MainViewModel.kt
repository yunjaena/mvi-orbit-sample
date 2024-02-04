package dev.yunzai.mviorbitexample.feature.main

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container = container<MainState, MainSideEffect>(MainState())

    fun increaseNumber() = intent {
        reduce {
            state.copy(
                maxNumber = state.maxNumber + 1
            )
        }
    }

    fun reset() = intent {
        reduce {
            state.copy(
                maxNumber = 0
            )
        }
    }

    fun onRandomButtonClicked() = intent {
        postSideEffect(MainSideEffect.NavigateToRandomScreen(state.maxNumber))
    }
}