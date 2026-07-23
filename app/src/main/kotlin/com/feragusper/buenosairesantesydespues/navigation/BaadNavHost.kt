package com.feragusper.buenosairesantesydespues.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.feragusper.buenosairesantesydespues.about.AboutScreen
import com.feragusper.buenosairesantesydespues.feature.records.navigation.RecordsListRoute
import com.feragusper.buenosairesantesydespues.feature.records.navigation.navigateToRecordDetail
import com.feragusper.buenosairesantesydespues.feature.records.navigation.recordDetailScreen
import com.feragusper.buenosairesantesydespues.feature.records.navigation.recordsListScreen
import com.feragusper.buenosairesantesydespues.splash.SplashScreen

private const val SPLASH_ROUTE = "splash"
private const val ABOUT_ROUTE = "about"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BaadNavHost() {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
            composable(SPLASH_ROUTE) {
                SplashScreen(
                    onTimeout = {
                        navController.navigate(RecordsListRoute.ROUTE) {
                            popUpTo(SPLASH_ROUTE) { inclusive = true }
                        }
                    },
                )
            }

            recordsListScreen(
                sharedTransitionScope = this@SharedTransitionLayout,
                onRecordClick = navController::navigateToRecordDetail,
                onAboutClick = { navController.navigate(ABOUT_ROUTE) },
            )

            recordDetailScreen(
                sharedTransitionScope = this@SharedTransitionLayout,
                onBackClick = navController::popBackStack,
            )

            composable(ABOUT_ROUTE) {
                AboutScreen(onBackClick = navController::popBackStack)
            }
        }
    }
}
