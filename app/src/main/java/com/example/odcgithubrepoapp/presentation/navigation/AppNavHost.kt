package com.example.odcgithubrepoapp.presentation.navigation

import BoardingScreen
import SplashScreen
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.RepoDetailsScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.RepoIssuesScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.IssuePassedDetailsIssuesScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.RepoListScreen
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.DETAILS_ISSUES_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.NAME_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.OWNER_ARGUMENT_KEY

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                goToHome = {
                    navController.navigate(Screens.RepoListScreen.route) {
                        popUpTo(Screens.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
                goToBoarding = {
                    navController.navigate(Screens.BoardingScreen.route) {
                        popUpTo(Screens.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screens.BoardingScreen.route) {
            BoardingScreen(
                toRepoListScreen = {
                    navController.navigate(Screens.RepoListScreen.route) {
                        popUpTo(Screens.BoardingScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screens.RepoListScreen.route) {
            RepoListScreen(
                onRepoItemClicked = { ownerName, name ->
                    navController.navigate(
                        Screens.RepoDetailsScreen.passOwnerAndName(
                            ownerName,
                            name
                        )
                    )
                })
        }

        composable(
            route = Screens.RepoDetailsScreen.route,
            arguments = listOf(
                navArgument(OWNER_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val owner = navBackStackEntry.arguments?.getString(OWNER_ARGUMENT_KEY)
            val name = navBackStackEntry.arguments?.getString(NAME_ARGUMENT_KEY)
            if (owner != null && name != null) {
                RepoDetailsScreen(
                    owner = owner,
                    name = name,
                    onShowIssuesClicked = { ownerName, name ->
                        navController.navigate(
                            Screens.RepoIssuesScreen.passOwnerAndName(
                                owner = ownerName,
                                name = name
                            )
                        )
                    },
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = Screens.RepoIssuesScreen.route,
            arguments = listOf(
                navArgument(DETAILS_ISSUES_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val sourceJson = navBackStackEntry.arguments?.getString(DETAILS_ISSUES_KEY)
            val source = Gson().fromJson(sourceJson, IssuePassedDetailsIssuesScreen::class.java)
            if (source != null) {
                RepoIssuesScreen(
                    owner = source.owner,
                    name = source.name,
                    onShowIssueItemClicked = { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}