package com.example.groceryshop.authentication.ui.login_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.groceryshop.authentication.ui.login_screen.viewmodel.LoginEvents
import com.example.groceryshop.authentication.ui.login_screen.viewmodel.LoginViewModel

@Composable
fun LoginCard(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    val state by viewModel.loginState.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.events, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginEvents.NavigationOnSuccess -> onLoginSuccess()
                    is LoginEvents.ApiError -> snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF4FAF7)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 36.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "FreshBasket",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF085041)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Fresh groceries, delivered to you",
                    fontSize = 12.sp,
                    color = Color(0xFF5F5E5A)
                )

                EmailTextField(
                    state.email,
                    state.emailError,
                    viewModel::onEmailChange
                )

                PasswordTextField(
                    state.password,
                    state.passwordError,
                    viewModel::onPasswordChange
                )

                TextButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        "Forget Password?",
                        color = Color(0xFF1D9E75),
                        fontSize = 11.sp
                    )
                }

                Button(
                    onClick = { viewModel.onLoginClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1D9E75)
                    )
                ) {

                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text("Login")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    color = Color(0xFFC8E6D8)
                )

                Text(
                    " or ",
                    fontSize = 11.sp,
                    color = Color(0xFFB4B2A9)
                )

                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    color = Color(0xFFC8E6D8)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF444441)
                ),
                border = BorderStroke(
                    1.dp,
                    Color(0xFFC8E6D8)
                )
            ) {
                Text(
                    "Continue with Google",
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "Don't have an account? ",
                    fontSize = 12.sp,
                    color = Color(0xFF888780)
                )

                TextButton(
                    onClick = onSignUp,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        "Sign up",
                        fontSize = 12.sp,
                        color = Color(0xFF1D9E75),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Logo (must be inside the Box)
        Box(
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.TopCenter)
                .clip(CircleShape)
                .background(Color(0xFF1D9E75))
                .border(
                    width = 4.dp,
                    color = Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(34.dp)
            )
        }
    }
}