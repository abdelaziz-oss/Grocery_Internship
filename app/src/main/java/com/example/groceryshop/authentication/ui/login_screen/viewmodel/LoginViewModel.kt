package com.example.groceryshop.authentication.ui.login_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryshop.authentication.data.local.UserPreferences
import com.example.groceryshop.authentication.domain.AuthRepository
import com.example.groceryshop.authentication.domain.validation.EmailValidator
import com.example.groceryshop.authentication.domain.validation.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val userPreferences: UserPreferences
) : ViewModel() {
    // private val repository = AuthRepositoryImpl()
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState

    private val _events = MutableSharedFlow<LoginEvents>()
    val events: SharedFlow<LoginEvents> = _events.asSharedFlow()

    fun onEmailChange(newValue: String) {
        _loginState.update { it.copy(email = newValue, emailError = null) }
    }

    fun onPasswordChange(newValue: String) {
        _loginState.update { it.copy(password = newValue, passwordError = null) }
    }

    fun onLoginClick() {
        val current = _loginState.value
        val emailResult = emailValidator.validate(current.email)
        val passwordResult = passwordValidator.validate(current.password)

        if (!emailResult.isValid || !passwordResult.isValid) {
            _loginState.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }
        viewModelScope.launch {
            _loginState.update {
                it.copy(isLoading = true)
            }
            try {
                val result = repository.login(current.email, current.password)
                if (result.isSuccess) {
                    userPreferences.setLoggedIn(true)
                    _events.emit(LoginEvents.NavigationOnSuccess)
                    //    it.copy(isLoading = false, isLoginSuccess = true)
                } else {
                    val message = result.exceptionOrNull()?.message ?: " Unknown Error"
                    _events.emit(LoginEvents.ApiError(message))
                }

//                    it.copy(
//                        isLoading = false,
//                        loginErrorMessage = result.exceptionOrNull()?.message ?: "Unknown Error"
//                    )Error
            } finally {
                _loginState.update { it.copy(isLoading = false) }
            }

        }
    }


}

sealed interface LoginEvents {
    object NavigationOnSuccess : LoginEvents
    data class ApiError(val message: String) : LoginEvents
}

// private val _loginEvent = MutableSharedFlow
// mozkart shared_flow
sealed class LoginEvent {
    object NavigateOnSuccess : LoginEvent()
    data class ApiError(val errorMessage: String = "") : LoginEvent()
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    //val loginErrorMessage: String? = null,
    //val isLoginSuccess: Boolean = false
)

//sealed class LoginState {
//    object Idle : LoginState()
//    object Loading : LoginState()
//    data class Success(val data: LoginResponse) : LoginState()
//    data class Error(val message: String) : LoginState()
//
//}