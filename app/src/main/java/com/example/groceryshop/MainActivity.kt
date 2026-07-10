package com.example.groceryshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryshop.authentication.ui.login_screen.components.LoginScreen
import com.example.groceryshop.authentication.ui.signup_screen.components.SignUpScreen
import com.example.groceryshop.authentication.ui.splash_screen.components.SplashScreen
import com.example.groceryshop.home_screen.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onDecision =
                    { route ->
                        navController.navigate(route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    })
        }
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("home")
            }, onSignUp = { navController.navigate("signup") })
        }
        composable("signup") {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate("login") },
                alreadyHaveAnAccount = { navController.navigate("login") })
        }
        composable("home") {
            HomeScreen()
        }
    }
}
