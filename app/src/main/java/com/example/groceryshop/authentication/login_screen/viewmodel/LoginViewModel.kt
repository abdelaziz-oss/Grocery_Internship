package com.example.groceryshop.authentication.login_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryshop.authentication.login_screen.models.LoginResponse
import com.example.groceryshop.authentication.login_screen.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = repository.login(email, password)
            _loginState.value = if (result.isSuccess) {
                LoginState.Success(result.getOrNull()!!)
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }


        }
    }

}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val data: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()

}