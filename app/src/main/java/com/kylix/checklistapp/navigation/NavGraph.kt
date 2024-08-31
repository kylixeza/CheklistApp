package com.kylix.checklistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kylix.checklistapp.screens.checklist.add_checklist.AddChecklistScreen
import com.kylix.checklistapp.screens.checklist_item.add_checklist_item.AddChecklistItemScreen
import com.kylix.checklistapp.screens.auth.login.LoginScreen
import com.kylix.checklistapp.screens.auth.register.RegisterScreen
import com.kylix.checklistapp.screens.checklist.ChecklistScreen
import com.kylix.checklistapp.screens.checklist_item.ChecklistItemScreen
import com.kylix.checklistapp.screens.checklist_item.detail.DetailChecklistItemScreen
import com.kylix.checklistapp.screens.checklist_item.edit.EditChecklistItemScreen

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
                    navController.navigate(ScreenNavigation.Checklist.route) {
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
        composable(ScreenNavigation.Checklist.route) {
            ChecklistScreen(
                onAddChecklist = {
                    navController.navigate(ScreenNavigation.AddChecklist.route)
                },
                onSelectItem = {
                    navController.navigate(ScreenNavigation.ChecklistItem.passChecklistId(it))
                }
            )
        }
        composable(ScreenNavigation.AddChecklist.route) {
            AddChecklistScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            ScreenNavigation.ChecklistItem.route,
            arguments = listOf(navArgument(ARG_CHECKLIST_ID) { type = NavType.IntType })
        ) {
            val checklistId = it.arguments?.getInt(ARG_CHECKLIST_ID) ?: 0
            ChecklistItemScreen(
                checklistId = checklistId,
                onAddChecklistItem = {
                    navController.navigate(ScreenNavigation.AddChecklistItem.passChecklistId(checklistId))
                },
                onSelectChecklistItem = {
                    navController.navigate(ScreenNavigation.DetailChecklistItem.passChecklistId(checklistId, it))
                },
                onEditChecklistItem = {
                    navController.navigate(ScreenNavigation.EditChecklistItem.passChecklistId(checklistId, it))
                }
            )
        }

        composable(
            ScreenNavigation.AddChecklistItem.route,
            arguments = listOf(navArgument(ARG_CHECKLIST_ID) { type = NavType.IntType })
        ) {
            val checklistId = it.arguments?.getInt(ARG_CHECKLIST_ID) ?: 0
            AddChecklistItemScreen(
                checklistId = checklistId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            ScreenNavigation.DetailChecklistItem.route,
            arguments = listOf(
                navArgument(ARG_CHECKLIST_ID) { type = NavType.IntType },
                navArgument(ARG_CHECKLIST_ITEM_ID) { type = NavType.IntType }
            )
        ) {
            val checklistId = it.arguments?.getInt(ARG_CHECKLIST_ID) ?: 0
            val itemId = it.arguments?.getInt(ARG_CHECKLIST_ITEM_ID) ?: 0
            DetailChecklistItemScreen(
                checklistId = checklistId,
                itemId = itemId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            ScreenNavigation.EditChecklistItem.route,
            arguments = listOf(
                navArgument(ARG_CHECKLIST_ID) { type = NavType.IntType },
                navArgument(ARG_CHECKLIST_ITEM_ID) { type = NavType.IntType }
            )
        ) {
            val checklistId = it.arguments?.getInt(ARG_CHECKLIST_ID) ?: 0
            val itemId = it.arguments?.getInt(ARG_CHECKLIST_ITEM_ID) ?: 0
            EditChecklistItemScreen(
                checklistId = checklistId,
                itemId = itemId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}