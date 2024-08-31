package com.kylix.checklistapp.screens.checklist_item.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistItemApiService
import com.kylix.checklistapp.data.api.request.ChecklistItemRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditChecklistItemViewModel(
    private val checklistItemApiService: ChecklistItemApiService
): ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()

    fun getItemById(checklistId: Int, itemId: Int) {
        viewModelScope.launch {
            try {
                val response = checklistItemApiService.getItemById(checklistId, itemId)
                _name.value = response.data?.name ?: ""
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun renameItem(checklistId: Int, itemId: Int) {
        viewModelScope.launch {
            try {
                val newName = _name.value
                val request = ChecklistItemRequest(newName)
                checklistItemApiService.renameItem(checklistId, itemId, request)
                _isSuccess.value = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setName(newName: String) {
        _name.value = newName
    }
}