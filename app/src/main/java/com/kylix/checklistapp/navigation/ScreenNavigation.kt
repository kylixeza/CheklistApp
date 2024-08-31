package com.kylix.checklistapp.navigation

const val ARG_CHECKLIST_ID = "checklistId"

sealed class ScreenNavigation(val route: String = "") {
    data object Login: ScreenNavigation("/login")
    data object Register: ScreenNavigation("/register")
    data object Checklist: ScreenNavigation("/checklist")
    data object AddChecklist: ScreenNavigation("/add-checklist")

    data object ChecklistItem: ScreenNavigation("/checklist/{$ARG_CHECKLIST_ID}") {
        fun passChecklistId(checklistId: Int): String {
            return this.route.replace("{$ARG_CHECKLIST_ID}", checklistId.toString())
        }
    }
    data class AddChecklistItem(val checklistId: Int): ScreenNavigation("/checklist/{$checklistId}/add-item")
}