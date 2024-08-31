package com.kylix.checklistapp.screens.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.AuthApiService
import com.kylix.checklistapp.data.api.request.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authService: AuthApiService,
): ViewModel() {

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()

    fun onRegister(username: String, email: String, password: String) {
        val body = RegisterRequest(username, email, password)

        viewModelScope.launch {
            try {
                val response = authService.register(body)
                _isSuccess.value = true
                Log.d("RegisterPage", "Success: ${response.data}")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RegisterPage", "Error: ${e.message}")
            }
        }
    }
}