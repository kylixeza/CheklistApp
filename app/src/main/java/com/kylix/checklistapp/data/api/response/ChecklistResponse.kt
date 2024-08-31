package com.kylix.checklistapp.data.api.response

data class ChecklistResponse(
    val id: Int,
    val name: String,
    val checklistCompletionStatus: Boolean,
    val items: List<ChecklistItemResponse>
)
