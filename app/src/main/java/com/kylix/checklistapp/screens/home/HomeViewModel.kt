package com.kylix.checklistapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistApiService
import com.kylix.checklistapp.data.api.response.ChecklistResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val checklistApiService: ChecklistApiService
): ViewModel() {

    private val _checklists = MutableStateFlow(emptyList<ChecklistResponse>())
    val checklists = _checklists.asStateFlow()

    fun getChecklists() {
        viewModelScope.launch {
            try {
                val response = checklistApiService.getChecklist()
                if (response.data != null) {
                    _checklists.value = response.data
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteChecklist(id: Int) {
        viewModelScope.launch {
            try {
                checklistApiService.deleteChecklist(id)
                getChecklists()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}