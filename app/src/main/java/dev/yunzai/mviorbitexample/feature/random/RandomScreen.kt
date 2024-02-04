package dev.yunzai.mviorbitexample.feature.random

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.yunzai.mviorbitexample.ui.theme.MVIOrbitExampleTheme
import dev.yunzai.mviorbitexample.ui.theme.Typography
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun RandomScreen(
    modifier: Modifier = Modifier,
    viewModel: RandomViewModel = viewModel(),
    goToMainScreen: () -> Unit = {}
) {
    val state = viewModel.collectAsState().value

    RandomScreenImpl(
        modifier = modifier,
        state = state,
        onGoToMainButtonClicked = { viewModel.goToMainScreen() }
    )
    viewModel.collectSideEffect {
        when (it) {
            RandomSideEffect.GoToMainScreen -> goToMainScreen()
        }
    }
}

@Composable
fun RandomScreenImpl(
    modifier: Modifier = Modifier,
    state: RandomState = RandomState(),
    onGoToMainButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Random Number from 0 to ${state.maxNumber} is",
            style = Typography.titleLarge
        )

        Text(
            text = "${state.randomNumber}",
            style = Typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onGoToMainButtonClicked() }
        ) {
            Text(text = "Go To Main")
        }
    }


}

@Preview("light", uiMode = Configuration.UI_MODE_NIGHT_NO, locale = "ko")
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko")
@Preview("light", uiMode = Configuration.UI_MODE_NIGHT_NO, locale = "ko", device = Devices.FOLDABLE)
@Composable
fun RandomScreenPreview() {
    MVIOrbitExampleTheme {
        Surface {
            RandomScreenImpl(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}