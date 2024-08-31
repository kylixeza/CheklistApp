package com.kylix.checklistapp.screens.checklist_item.add_checklist_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistItemApiService
import com.kylix.checklistapp.data.api.request.ChecklistItemRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddChecklistItemViewModel(
    private val checklistItemApiService: ChecklistItemApiService
): ViewModel() {

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()

    fun saveItem(checklistId: Int, name: String) {
        viewModelScope.launch {
            try {
                val request = ChecklistItemRequest(name)
                checklistItemApiService.saveItem(checklistId, request)
                _isSuccess.value = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}