package com.kylix.checklistapp.navigation

const val ARG_CHECKLIST_ID = "checklistId"
const val ARG_CHECKLIST_ITEM_ID = "itemId"

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

    data object AddChecklistItem: ScreenNavigation("/add-checklist-item/{$ARG_CHECKLIST_ID}") {
        fun passChecklistId(checklistId: Int): String {
            return this.route.replace("{$ARG_CHECKLIST_ID}", checklistId.toString())
        }
    }

    data object DetailChecklistItem: ScreenNavigation("/detail-checklist-item/{$ARG_CHECKLIST_ID}/item/{$ARG_CHECKLIST_ITEM_ID}") {
        fun passChecklistId(checklistId: Int, itemId: Int): String {
            return this.route.replace("{$ARG_CHECKLIST_ID}", checklistId.toString())
                .replace("{$ARG_CHECKLIST_ITEM_ID}", itemId.toString())
        }
    }
}