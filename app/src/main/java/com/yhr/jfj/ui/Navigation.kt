package com.yhr.jfj.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yhr.jfj.ui.ui.HomeScreen
import com.yhr.jfj.ui.ui.login.LoginScreen
import com.yhr.jfj.ui.ui.signup.PolicyScreen
import com.yhr.jfj.ui.ui.signup.PrivacyScreen
import com.yhr.jfj.ui.ui.signup.SignUpScreen

sealed class Route {
    data class LoginScreen(val name: String = "login_screen") : Route()
    data class SignUpScreen(val name: String = "sign_up_screen") : Route()
    data class PrivacyScreen(val name: String = "privacy_screen") : Route()
    data class PolicyScreen(val name: String = "policy_screen") : Route()
    data class HomeScreen(val name: String = "home_screen") : Route()
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.LoginScreen().name) {
        composable(route = Route.LoginScreen().name) {
            LoginScreen(navController = navController)
        }
        composable(route = Route.SignUpScreen().name) {
            SignUpScreen(navController = navController)
        }
        composable(route = Route.PrivacyScreen().name) {
            PrivacyScreen(navController = navController) {}
        }
        composable(route = Route.PolicyScreen().name) {
            PolicyScreen(navController = navController) {}
        }
        composable(route = Route.HomeScreen().name) {
            HomeScreen()
        }

    }
}