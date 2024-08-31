package com.kylix.checklistapp.screens.checklist_item

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistItemApiService
import com.kylix.checklistapp.data.api.response.ChecklistItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChecklistItemViewModel(
    private val checklistItemApiService: ChecklistItemApiService
): ViewModel() {

    private val _checklistItems = MutableStateFlow(emptyList<ChecklistItemResponse>())
    val checklistItems = _checklistItems.asStateFlow()

    fun getChecklistItems(checklistId: Int) {
        viewModelScope.launch {
            try {
                val response = checklistItemApiService.getAllItems(checklistId)
                if (response.data != null) {
                    _checklistItems.value = response.data
                    Log.d("ChecklistItemViewModel", "getChecklistItems: ${response.data}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteItem(checklistId: Int, itemId: Int) {
        viewModelScope.launch {
            try {
                val response = checklistItemApiService.deleteItem(checklistId, itemId)
                if (response.data != null) {
                    getChecklistItems(checklistId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateItemStatus(checklistId: Int, itemId: Int) {
        viewModelScope.launch {
            try {
                val response = checklistItemApiService.updateItemStatus(checklistId, itemId)
                if (response.data != null) {
                    getChecklistItems(checklistId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}