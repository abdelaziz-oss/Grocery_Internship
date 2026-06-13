package com.example.groceryshop.authentication.presentation.signup_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpItem(modifier: Modifier = Modifier, text: String, content: @Composable () -> Unit) {
    Column(modifier = modifier.padding(vertical = 5.dp)) {
        Text(text, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        content()
    }
}