package com.kylix.checklistapp.data.api.response

data class ChecklistItemResponse(
    val id: Int,
    val name: String,
    val itemCompletionStatus: Boolean
)
