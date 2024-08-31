package com.kylix.checklistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kylix.checklistapp.screens.add_checklist.AddChecklistScreen
import com.kylix.checklistapp.screens.auth.login.LoginScreen
import com.kylix.checklistapp.screens.auth.register.RegisterScreen
import com.kylix.checklistapp.screens.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.Login.route
    ) {
        composable(ScreenNavigation.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(ScreenNavigation.Register.route)
                },
                onNavigateToHome = {
                    navController.navigate(ScreenNavigation.Home.route) {
                        popUpTo(ScreenNavigation.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(ScreenNavigation.Register.route) {
            RegisterScreen(
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(ScreenNavigation.Home.route) {
            HomeScreen(
                onAddTask = {
                    navController.navigate(ScreenNavigation.AddChecklist.route)
                }
            )
        }
        composable(ScreenNavigation.AddChecklist.route) {
            AddChecklistScreen(
                onSaveChecklist = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}