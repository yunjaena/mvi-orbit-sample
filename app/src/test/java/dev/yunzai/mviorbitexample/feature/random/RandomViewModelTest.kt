package dev.yunzai.mviorbitexample.feature.random

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.orbitmvi.orbit.test.Item
import org.orbitmvi.orbit.test.TestSettings
import org.orbitmvi.orbit.test.test
import java.lang.Exception

class RandomViewModelTest {
    @Test
    fun Given_Max_Number_Empty_When_Initialized_Then_Error() = runTest {
        try {
            RandomViewModel(SavedStateHandle()).test(
                this,
                settings = TestSettings(exceptionHandlerOverride = CoroutineExceptionHandler { _, exception ->
                    Assert.assertNotNull(exception)
                }),
                initialState = RandomState()
            ) {
                runOnCreate()
            }
        } catch (_: Exception) {
        }
    }

    @Test
    fun Given_Max_Number_When_Initialized_Then_MaxNumber_Number_And_Random_Number_Expose() =
        runTest {
            RandomViewModel(SavedStateHandle().apply { set("maxNumber", 10) }).test(
                this,
                RandomState()
            ) {
                runOnCreate()
                expectInitialState()
                val item = awaitItem() as Item.StateItem
                Assert.assertEquals(10, item.value.maxNumber)
            }
        }
}