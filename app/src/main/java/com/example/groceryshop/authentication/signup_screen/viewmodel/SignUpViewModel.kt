package com.example.groceryshop.authentication.signup_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryshop.authentication.login_screen.repository.AuthRepository
import com.example.groceryshop.authentication.signup_screen.models.SignUpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val repository = AuthRepository()
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState
    fun signUp(email: String, username: String, password: String) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            val result = repository.SignUp(email, username, password)
            _signUpState.value = if (result.isSuccess) {
                SignUpState.Success
            } else {
                SignUpState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

}

sealed class SignUpState {

    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}