package com.feragusper.buenosairesantesydespues.feature.records.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.feragusper.buenosairesantesydespues.feature.records.detail.RecordDetailScreen
import com.feragusper.buenosairesantesydespues.feature.records.list.RecordsListScreen

/** Route for the records grid. */
object RecordsListRoute {
    const val ROUTE = "records"
}

/** Route for a single record's detail. */
object RecordDetailRoute {
    const val ARG_ID = "recordId"
    const val ROUTE = "record"
    const val ROUTE_PATTERN = "$ROUTE/{$ARG_ID}"
    fun build(id: String) = "$ROUTE/$id"
}

fun NavController.navigateToRecordDetail(id: String) =
    navigate(RecordDetailRoute.build(id))

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.recordsListScreen(
    sharedTransitionScope: SharedTransitionScope,
    onRecordClick: (String) -> Unit,
    onAboutClick: () -> Unit,
) {
    composable(RecordsListRoute.ROUTE) {
        RecordsListScreen(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = this,
            onRecordClick = onRecordClick,
            onAboutClick = onAboutClick,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.recordDetailScreen(
    sharedTransitionScope: SharedTransitionScope,
    onBackClick: () -> Unit,
) {
    composable(
        route = RecordDetailRoute.ROUTE_PATTERN,
        arguments = listOf(navArgument(RecordDetailRoute.ARG_ID) { type = NavType.StringType }),
    ) {
        RecordDetailScreen(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = this,
            onBackClick = onBackClick,
        )
    }
}
