package com.kylix.checklistapp.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.checklistapp.data.api.AuthApiService
import com.kylix.checklistapp.data.api.request.LoginRequest
import com.kylix.checklistapp.data.local.ChecklistDatastore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthApiService,
    private val dataStore: ChecklistDatastore
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onUsernameChanged(username: String) {
        _state.update {
            it.copy(username = username)
        }
        checkButtonEnable()
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(password = password)
        }
        checkButtonEnable()
    }

    fun onLogin() {
        val username = state.value.username
        val password = state.value.password

        val body = LoginRequest(username, password)

        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            try {
                val response = authService.login(body)
                if (response.data != null) {
                    dataStore.saveToken(response.data.token)
                }
                _state.update {
                    it.copy(isSuccess = true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _state.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun checkButtonEnable() {
        val username = state.value.username
        val password = state.value.password

        val enableButton = username.isNotEmpty() && password.isNotEmpty()

        _state.update {
            it.copy(enableButton = enableButton)
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val username: String = "",
    val password: String = "",
    val enableButton: Boolean = false,
)