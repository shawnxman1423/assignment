package io.liba.assignment.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import io.liba.assignment.screens.home.HomeScreen
import io.liba.assignment.screens.home.HomeViewModel
import io.liba.assignment.screens.ui.theme.AssignmentTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AssignmentTheme {
                AssignmentNavHost()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AssignmentNavHost() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = "home") {
        composable(
            route = "home",
            content = {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(navController, viewModel)
            }
        )
    }
}
