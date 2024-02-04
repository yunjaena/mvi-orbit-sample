package dev.yunzai.mviorbitexample.feature.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.yunzai.mviorbitexample.feature.main.MainScreen
import dev.yunzai.mviorbitexample.feature.random.RandomScreen

@Composable
fun RandomApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RandomNumberScreen.Main.name
    ) {
        composable(RandomNumberScreen.Main.name) {
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                navigateToRandomNumber = { navigateToRandomScreen(navController, it) }
            )
        }
        composable(
            route = "${RandomNumberScreen.Random.name}/{maxNumber}",
            arguments = listOf(
                navArgument("maxNumber") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            RandomScreen(
                modifier = Modifier.fillMaxSize(),
                goToMainScreen = { navController.popBackStack(RandomNumberScreen.Main.name, false) }
            )
        }
    }

}

private fun navigateToRandomScreen(
    navController: NavController,
    maxNumber: Int
) {
    navController.navigate("${RandomNumberScreen.Random.name}/${maxNumber}")
}