package dev.yunzai.mviorbitexample.feature.main

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.orbitmvi.orbit.test.test

class MainViewModelTest {

    @Test
    fun When_Increase_Number_Then_Max_Number_Increase() = runTest {
        MainViewModel().test(this, MainState()) {
            expectInitialState()
            containerHost.increaseNumber()
            expectState { copy(maxNumber = 1) }
        }
    }


    @Test
    fun Given_Max_Number_Bigger_Then_Zero_When_Rest_Number_Then_Max_Number_Rest_To_Zero() =
        runTest {
            MainViewModel().test(this, MainState()) {
                expectInitialState()
                containerHost.increaseNumber()
                expectState { copy(maxNumber = 1) }
                containerHost.increaseNumber()
                expectState { copy(maxNumber = 2) }
                containerHost.increaseNumber()
                expectState { copy(maxNumber = 3) }
                containerHost.reset()
                expectState { copy(maxNumber = 0) }
            }
        }

    @Test
    fun When_On_Click_Random_Number_Button_Then_Navigate_To_Random_Number_Screen() =
        runTest {
            MainViewModel().test(this, MainState()) {
                expectInitialState()
                containerHost.increaseNumber()
                expectState { copy(maxNumber = 1) }
                containerHost.onRandomButtonClicked()
                expectSideEffect(MainSideEffect.NavigateToRandomScreen(1))
            }
        }
}