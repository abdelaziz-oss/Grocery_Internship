package com.example.groceryshop.authentication.ui.login_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryshop.authentication.ui.login_screen.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F6E56))
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = 100.dp, y = (-100).dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(Color(0xFF1D9E75))
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-60).dp, y = 80.dp)
                .align(Alignment.BottomStart)
                .clip(CircleShape)
                .background(Color(0xFF085041))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(90.dp))
            Text(
                "Your fridge called. It misses you.", fontSize = 13.sp,
                color = Color(0xFFE1F5EE),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Let's go fill it up!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginCard(
                snackBarHostState = snackBarHostState,
                viewModel = viewModel,
                onLoginSuccess = onLoginSuccess, onSignUp = onSignUp,

                )
        }
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}