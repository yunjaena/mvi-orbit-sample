package dev.yunzai.mviorbitexample.feature.random

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import kotlin.random.Random

class RandomViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<RandomState, RandomSideEffect> {
    override val container: Container<RandomState, RandomSideEffect> =
        container(RandomState(), savedStateHandle = savedStateHandle) {
            val maxNumber = savedStateHandle.get<Int>("maxNumber")
            checkNotNull(maxNumber)
            getRandomNumber(maxNumber)
        }

    private fun getRandomNumber(maxNumber: Int) = intent {
        reduce {
            state.copy(
                maxNumber = maxNumber,
                randomNumber = Random.nextInt(maxNumber + 1)
            )
        }
    }

    fun goToMainScreen() = intent {
        postSideEffect(RandomSideEffect.GoToMainScreen)
    }

}