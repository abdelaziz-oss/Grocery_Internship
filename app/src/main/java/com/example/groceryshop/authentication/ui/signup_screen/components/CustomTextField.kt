package com.example.groceryshop.authentication.ui.signup_screen.components

import android.provider.CalendarContract
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(input: String, errorMessage: String? = "", onValueChange: (String) -> Unit) {
    TextField(
        value = input,
        onValueChange = onValueChange,
        isError = errorMessage != null,
        supportingText = { if (errorMessage != null) Text(errorMessage, color = Color.Red) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth(),

        )
}