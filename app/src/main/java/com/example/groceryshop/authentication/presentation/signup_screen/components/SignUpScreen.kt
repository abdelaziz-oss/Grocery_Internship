package com.example.groceryshop.authentication.presentation.signup_screen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.groceryshop.authentication.presentation.signup_screen.viewmodel.SignUpState
import com.example.groceryshop.authentication.presentation.signup_screen.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess: () -> Unit,
    alreadyHaveAnAccount: () -> Unit
) {
    val signUpState by viewModel.signUpState.collectAsState()
    val fullNameText = remember { mutableStateOf("") }
    val emailText = remember { mutableStateOf("") }
    // val phoneText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }
    val context = LocalContext.current

    //  val confirmPasswordText = remember { mutableStateOf("") }

    LaunchedEffect(signUpState) {
        when (signUpState) {
            is SignUpState.Success -> {
                Toast.makeText(context, "User Created", Toast.LENGTH_SHORT).show()
                onSignUpSuccess


            }

            is SignUpState.Error -> {
            }

            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0F6E56))
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
        Column(modifier = Modifier.padding(32.dp)) {
            Text("Welcome!", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Create your account",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignUpItem(text = "Full Name") {
                CustomTextField(input = fullNameText) { fullNameText.value = it }
            }
            SignUpItem(text = "Email") {
                CustomTextField(input = emailText) { emailText.value = it }
            }
//            SignUpItem(text = "Phone Number") {
//                CustomTextField(input = phoneText) { fullNameText.value = it }
//            }
            SignUpItem(text = "Password") {
                CustomTextField(input = passwordText) { passwordText.value = it }
            }
//            SignUpItem(text = "Confirm password") {
//                CustomTextField(input = confirmPasswordText) { fullNameText.value = it }
//            }
            Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                OutlinedButton(
                    onClick = {
                        viewModel.signUp(emailText.value, fullNameText.value, passwordText.value)

                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) { Text("Create Account") }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account?", fontSize = 14.sp, color = Color(0xFF888780))
                TextButton(onClick = alreadyHaveAnAccount, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        "SignIn",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}