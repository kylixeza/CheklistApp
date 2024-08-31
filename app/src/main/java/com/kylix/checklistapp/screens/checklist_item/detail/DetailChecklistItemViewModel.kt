package com.kylix.checklistapp.screens.checklist_item.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistItemApiService
import com.kylix.checklistapp.data.api.response.ChecklistItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailChecklistItemViewModel(
    private val checklistItemApiService: ChecklistItemApiService
): ViewModel() {

    private val _item = MutableStateFlow<ChecklistItemResponse?>(null)
    val item = _item.asStateFlow()

    fun getItem(checklistId: Int, itemId: Int) {
        viewModelScope.launch {
            try {
                val response = checklistItemApiService.getItemById(checklistId, itemId)
                if (response.data != null) {
                    _item.value = response.data
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}