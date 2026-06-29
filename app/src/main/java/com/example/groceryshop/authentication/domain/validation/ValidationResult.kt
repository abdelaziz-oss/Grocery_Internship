package com.example.groceryshop.authentication.domain.validation

import android.util.Patterns
import javax.inject.Inject

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)

class UsernameValidator @Inject constructor() {
    fun validate(userName: String): ValidationResult {
        return when {
            userName.isBlank() -> ValidationResult(false, " Username cannot be empty")
            userName.length < 2 -> ValidationResult(false, "Username must be at least 2 characters")
            userName.length > 20 -> ValidationResult(
                false,
                "Username must be less than 20 characters"
            )

            !userName.matches(Regex("^[a-zA-Z0-9_]+$")) -> ValidationResult(
                false,
                "Username can only contain letters, Numbers, Underscores"
            )

            else -> ValidationResult(true)

        }
    }
}

class EmailValidator @Inject constructor() {
    fun validate(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult(false, "Email can not be empty")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult(
                false,
                "Please enter a valid email"
            )

            else -> ValidationResult(true)
        }
    }
}

class PasswordValidator @Inject constructor() {
    fun validate(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult(false, "password cannot be empty")
            password.length < 8 -> ValidationResult(false, "Password must be at least 8 characters")
            !password.any { it.isDigit() } -> ValidationResult(
                false,
                "Password must contain a digit"
            )

            !password.any { it.isUpperCase() } -> ValidationResult(
                false,
                "Password must contain an uppercase letter"
            )

            else -> ValidationResult(true)
        }
    }
}