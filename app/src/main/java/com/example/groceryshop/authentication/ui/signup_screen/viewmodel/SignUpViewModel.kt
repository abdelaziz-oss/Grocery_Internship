package com.example.groceryshop.authentication.ui.signup_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryshop.authentication.domain.AuthRepository
import com.example.groceryshop.authentication.domain.validation.EmailValidator
import com.example.groceryshop.authentication.domain.validation.PasswordValidator
import com.example.groceryshop.authentication.domain.validation.UsernameValidator
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
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userNameValidator: UsernameValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : ViewModel() {
    //val repository = AuthRepositoryImpl()
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState
    private val _events = MutableSharedFlow<SignupEvents>()

    val events: SharedFlow<SignupEvents> = _events.asSharedFlow()

    fun onUsernameChange(newValue: String) {
        _signUpState.update { it.copy(userName = newValue, userNameError = null) }
    }

    fun onEmailChange(newValue: String) {
        _signUpState.update { it.copy(email = newValue, emailError = null) }
    }

    fun onPasswordChange(newValue: String) {
        _signUpState.update { it.copy(password = newValue, passwordError = null) }
    }

    fun signUpClick() {
        val current = _signUpState.value
        val userNameResult = userNameValidator.validate(current.userName)
        val emailResult = emailValidator.validate(current.email)
        val passwordResult = passwordValidator.validate(current.password)
        if (!userNameResult.isValid || !emailResult.isValid || !passwordResult.isValid) {
            _signUpState.update {
                it.copy(
                    userNameError = userNameResult.errorMessage,
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }
        viewModelScope.launch {
            _signUpState.update { it.copy(isLoading = true, signupErrorMessage = null) }
            val result = repository.signUp(
                username = current.userName,
                email = current.email,
                password = current.password
            )
            if (result.isSuccess) {
                _events.emit(SignupEvents.OnSignUpSuccess)
                // _signUpState.update { it.copy(isSuccess = true, isLoading = false) }
            } else {
                val message = result.exceptionOrNull()?.message ?: "Unknown Error"
                _events.emit(SignupEvents.ApiError(message = message))
//                _signUpState.update {
//                    it.copy(
//                        isLoading = false,
//                        signupErrorMessage = result.exceptionOrNull()?.message ?: " Unknown Error"
//                    )
//                }
            }


        }


    }


//    fun signUp(email: String, username: String, password: String) {
//        viewModelScope.launch {
//            _signUpState.value = SignUpState.Loading
//            val result = repository.signUp(email, username, password)
//            _signUpState.value = if (result.isSuccess) {
//                SignUpState.Success
//            } else {
//                SignUpState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
//            }
//        }
//    }

}

sealed class SignupEvents {
    object OnSignUpSuccess : SignupEvents()

    //object OnSignInClicked : SignupEvents()
    data class ApiError(val message: String = "") : SignupEvents()
}

data class SignUpState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val userNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val signupErrorMessage: String? = null
)

//sealed class SignUpState {
//
//    object Idle : SignUpState()
//    object Loading : SignUpState()
//    object Success : SignUpState()
//    data class Error(val message: String) : SignUpState()
//}