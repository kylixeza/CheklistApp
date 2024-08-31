package com.kylix.checklistapp.navigation

sealed class ScreenNavigation(val route: String) {
    data object Login: ScreenNavigation("/login")
    data object Register: ScreenNavigation("/register")
    data object Home: ScreenNavigation("/home")
    data object AddChecklist: ScreenNavigation("/add-checklist")
}