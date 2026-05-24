//package com.example.groceryshop.ui.theme.design_system
//
//
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.*
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun LoginScreen(
//    onLoginClick: () -> Unit = {},
//    onForgotPasswordClick: () -> Unit = {},
//    onGoogleClick: () -> Unit = {},
//    onFacebookClick: () -> Unit = {},
//    onSignUpClick: () -> Unit = {}
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var rememberMe by remember { mutableStateOf(false) }
//
//    val gradientBackground = Brush.verticalGradient(
//        colors = listOf(Color(0xFFB8D4F5), Color(0xFFFFFFFF)),
//        startY = 0f,
//        endY = 600f
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(brush = gradientBackground)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(horizontal = 24.dp, vertical = 48.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            // App Icon
//            Box(
//                modifier = Modifier
//                    .size(64.dp)
//                    .background(Color(0xFF3B7EF6), shape = RoundedCornerShape(16.dp)),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "✦",
//                    color = Color.White,
//                    fontSize = 28.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Title
//            Text(
//                text = "Sign in to your Account",
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1A1A2E),
//                textAlign = TextAlign.Start,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Subtitle
//            Text(
//                text = "Enter your email and password to log in",
//                fontSize = 14.sp,
//                color = Color(0xFF888888),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Email Field
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                placeholder = { Text("Email", color = Color(0xFFAAAAAA)) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color(0xFFE0E0E0),
//                    focusedBorderColor = Color(0xFF3B7EF6),
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White
//                ),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Password Field
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                placeholder = { Text("Password", color = Color(0xFFAAAAAA)) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                visualTransformation = if (passwordVisible) VisualTransformation.None
//                else PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = if (passwordVisible) Icons.Default.Visibility
//                            else Icons.Default.VisibilityOff,
//                            contentDescription = "Toggle password",
//                            tint = Color(0xFFAAAAAA)
//                        )
//                    }
//                },
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color(0xFFE0E0E0),
//                    focusedBorderColor = Color(0xFF3B7EF6),
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White
//                ),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Remember Me + Forgot Password
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(
//                        checked = rememberMe,
//                        onCheckedChange = { rememberMe = it },
//                        colors = CheckboxDefaults.colors(
//                            checkedColor = Color(0xFF3B7EF6)
//                        )
//                    )
//                    Text(
//                        text = "Remember me",
//                        fontSize = 13.sp,
//                        color = Color(0xFF555555)
//                    )
//                }
//                TextButton(onClick = onForgotPasswordClick) {
//                    Text(
//                        text = "Forgot Password?",
//                        fontSize = 13.sp,
//                        color = Color(0xFF3B7EF6)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Log In Button
//            Button(
//                onClick = onLoginClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(52.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF3B7EF6)
//                )
//            ) {
//                Text(
//                    text = "Log In",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.White
//                )
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Divider with "Or"
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFDDDDDD))
//                Text(
//                    text = "  Or  ",
//                    fontSize = 13.sp,
//                    color = Color(0xFFAAAAAA)
//                )
//                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFDDDDDD))
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Continue with Google
//            OutlinedButton(
//                onClick = onGoogleClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(52.dp),
//                shape = RoundedCornerShape(12.dp),
//                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
//                colors = ButtonDefaults.outlinedButtonColors(
//                    containerColor = Color.White
//                )
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    // Replace with actual Google icon if available
//                    Text("G", color = Color(0xFFDB4437), fontWeight = FontWeight.Bold, fontSize = 18.sp)
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Continue with Google",
//                        fontSize = 15.sp,
//                        color = Color(0xFF333333)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Continue with Facebook
//            OutlinedButton(
//                onClick = onFacebookClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(52.dp),
//                shape = RoundedCornerShape(12.dp),
//                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
//                colors = ButtonDefaults.outlinedButtonColors(
//                    containerColor = Color.White
//                )
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text("f", color = Color(0xFF1877F2), fontWeight = FontWeight.Bold, fontSize = 20.sp)
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Continue with Facebook",
//                        fontSize = 15.sp,
//                        color = Color(0xFF333333)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Sign Up Link
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Don't have an account? ",
//                    fontSize = 14.sp,
//                    color = Color(0xFF888888)
//                )
//                TextButton(
//                    onClick = onSignUpClick,
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Text(
//                        text = "Sign Up",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color(0xFF3B7EF6)
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
