package com.feragusper.buenosairesantesydespues.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.feragusper.buenosairesantesydespues.about.AboutScreen
import com.feragusper.buenosairesantesydespues.splash.SplashScreen
import com.feragusper.buenosairesantesydespues.feature.records.navigation.RecordsListRoute
import com.feragusper.buenosairesantesydespues.feature.records.navigation.navigateToRecordDetail
import com.feragusper.buenosairesantesydespues.feature.records.navigation.recordDetailScreen
import com.feragusper.buenosairesantesydespues.feature.records.navigation.recordsListScreen

private const val SPLASH_ROUTE = "splash"
private const val ABOUT_ROUTE = "about"

@Composable
fun BaadNavHost() {
    val navController = rememberNavController()

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
            onRecordClick = navController::navigateToRecordDetail,
            onAboutClick = { navController.navigate(ABOUT_ROUTE) },
        )

        recordDetailScreen(
            onBackClick = navController::popBackStack,
        )

        composable(ABOUT_ROUTE) {
            AboutScreen(onBackClick = navController::popBackStack)
        }
    }
}
