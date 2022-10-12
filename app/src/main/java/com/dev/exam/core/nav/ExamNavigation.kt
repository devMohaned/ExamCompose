package com.dev.exam.core.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.exam.ui.temp.home.HomeScreen
import com.dev.exam.feature_auth.ui.login.LoginScreen
import com.dev.exam.ui.temp.register.RegisterScreen
import com.dev.exam.ui.temp.search.SearchScreen
import com.dev.exam.ui.temp.stats.ExamScreen


@Composable
fun ExamNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ExamScreens.LoginScreen.name) {
        composable(ExamScreens.LoginScreen.name) {
            LoginScreen(onSignUpClicked = {
                navController.navigate(ExamScreens.RegisterScreen.name)
            }, onLoggedIn = {
                navController.navigate(ExamScreens.HomeScreen.name)
            }, onTokenFound = {
                navController.navigate(ExamScreens.HomeScreen.name){
                    popUpTo(ExamScreens.HomeScreen.name) {
                        inclusive = true
                    }
                }
            })
        }
        composable(ExamScreens.RegisterScreen.name) {
            RegisterScreen(onBackButtonClicked = {
                navController.popBackStack()
            }) {

                navController.navigate(ExamScreens.HomeScreen.name)
            }
        }
        composable(ExamScreens.HomeScreen.name) {
            HomeScreen()
        }

        composable(ExamScreens.ExamScreen.name) {
            ExamScreen()
        }
        composable(ExamScreens.SearchScreen.name) {
            SearchScreen()
        }


    }
}