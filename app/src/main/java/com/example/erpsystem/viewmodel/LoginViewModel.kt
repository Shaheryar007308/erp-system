package com.example.erpsystem.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.erpsystem.data.model.LoginResponse
import com.example.erpsystem.data.repository.AuthRepository

sealed interface LoginUiState {
    data class Success(val response: LoginResponse) : LoginUiState
    data class Error(val errorMessage: String) : LoginUiState
    data object Loading : LoginUiState
    data object Idle : LoginUiState
}


class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _uiState = mutableStateOf<LoginUiState>(LoginUiState.Idle)
    val uiState : State<LoginUiState> = _uiState

    fun login(
        email: String,
        password: String ,
        targetRole : String
    ){
        // Enforce basic front-end validation rule layers
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("Email and Password fields cannot be empty.")
            return
        }
        _uiState.value = LoginUiState.Loading
        repository.loginUser(email, password) { result ->
            result.onSuccess { loginResponse ->
                // Guard: Ensure the user logging in matches the precise dashboard authorization node selected
                if (loginResponse.user.role == targetRole) {
                    _uiState.value = LoginUiState.Success(loginResponse)
                } else {
                    _uiState.value = LoginUiState.Error(
                        "Access Denied. Your credentials do not possess matching $targetRole level clearance clearance."
                    )
                }
            }.onFailure { throwable ->
                _uiState.value = LoginUiState.Error(throwable.message ?: "An unexpected error occurred.")
            }
        }
    }
    fun resetState(){
        _uiState.value= LoginUiState.Idle
    }
    }
