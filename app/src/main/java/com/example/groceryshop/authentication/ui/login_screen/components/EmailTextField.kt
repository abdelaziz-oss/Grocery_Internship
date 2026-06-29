package com.example.groceryshop.authentication.ui.login_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailTextField(
    email: String,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit
) {

    Column(modifier = Modifier) {
        Text("Email", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = email,
            onValueChange = onValueChange,
            isError = errorMessage != null,
            supportingText = { if (errorMessage != null) Text(errorMessage, color = Color.Red) },
            leadingIcon = {
                Icon(
                    Icons.Filled.AlternateEmail,
                    contentDescription = null,
                    tint = Color(0xFF1D9E75),
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(  // ← change to TextFieldDefaults
                focusedTextColor = Color(0xFF2C2C2A),
                unfocusedTextColor = Color(0xFF2C2C2A),
                focusedIndicatorColor = Color(0xFF1D9E75),
                unfocusedIndicatorColor = Color(0xFFC8E6D8),
                focusedLabelColor = Color(0xFF1D9E75),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(.9f)
        )

    }
//    OutlinedTextField(
//        value = email.value,
//        onValueChange = { email.value = it },
//        label = { Text("Email") },
//        leadingIcon = {
//            Icon(Icons.Filled.Email, contentDescription = null, tint = Color(0xFF1D9E75))
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//        singleLine = true,
//        shape = RoundedCornerShape(12.dp),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor   = Color(0xFF1D9E75),
//            unfocusedBorderColor = Color(0xFFC8E6D8),
//            focusedLabelColor    = Color(0xFF1D9E75),
//            unfocusedContainerColor = Color.White,
//            focusedContainerColor   = Color.White
//        ),
//        modifier = Modifier.fillMaxWidth()
//    )
}