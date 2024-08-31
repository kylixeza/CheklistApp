package com.kylix.checklistapp.screens.add_checklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.ChecklistApiService
import com.kylix.checklistapp.data.api.request.ChecklistRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddChecklistViewModel(
    private val checklistApiService: ChecklistApiService
): ViewModel() {

    private val _isSaveSuccess = MutableStateFlow(false)
    val isSaveSuccess = _isSaveSuccess.asStateFlow()

    fun saveChecklist(name: String) {
        viewModelScope.launch {
            try {
                val request = ChecklistRequest(name)
                checklistApiService.saveChecklist(request)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}