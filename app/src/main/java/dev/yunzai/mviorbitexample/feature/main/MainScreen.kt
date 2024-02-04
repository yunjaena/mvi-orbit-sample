package dev.yunzai.mviorbitexample.feature.main

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
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    navigateToRandomNumber: (randomNumber: Int) -> Unit = {}
) {
    val state = viewModel.collectAsState().value

    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Random Number 0 to ${state.maxNumber}",
            style = Typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.increaseNumber() }
        ) {
            Text(text = "increase")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onRandomButtonClicked() }
        ) {
            Text(text = "random number")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.reset() }
        ) {
            Text(text = "reset")
        }

    }
    viewModel.collectSideEffect {
        when (it) {
            is MainSideEffect.NavigateToRandomScreen -> navigateToRandomNumber(it.number)
        }
    }
}

@Preview("light", uiMode = Configuration.UI_MODE_NIGHT_NO, locale = "ko")
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko")
@Preview("light", uiMode = Configuration.UI_MODE_NIGHT_NO, locale = "ko", device = Devices.FOLDABLE)
@Composable
fun MainScreenPreview() {
    MVIOrbitExampleTheme {
        Surface {
            MainScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}