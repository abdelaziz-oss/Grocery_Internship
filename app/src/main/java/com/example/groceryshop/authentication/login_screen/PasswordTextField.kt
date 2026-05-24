package com.example.groceryshop.authentication.login_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordTextField(password: MutableState<String>, onValueChanged: (String) -> Unit) {
    Column(modifier = Modifier) {
        Text("Password", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = password.value,
            onValueChange = onValueChanged,
            leadingIcon = {
                Icon(
                    Icons.Filled.Password,
                    contentDescription = null,
                    tint = Color(0xFF1D9E75),
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
}