package com.dev.exam.core.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.exam.feature_exams.ui.HomeScreen
import com.dev.exam.feature_auth.ui.login.LoginScreen
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.ui.CreateExamScreen
import com.dev.exam.feature_exams.ui.UpdateExamScreen
import com.dev.exam.feature_questions.ui.AddQuestionSuccessScreen
import com.dev.exam.ui.temp.register.RegisterScreen
import com.dev.exam.ui.temp.search.SearchScreen
import com.dev.exam.ui.temp.stats.ExamScreen


@Composable
fun ExamNavigation(isTokenFound: Boolean) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isTokenFound) ExamScreens.HomeScreen.name else ExamScreens.LoginScreen.name
    ) {
        composable(ExamScreens.LoginScreen.name) {
            LoginScreen(onSignUpClicked = {
                navController.navigate(ExamScreens.RegisterScreen.name)
            }, onLoggedIn = {
                navController.navigate(ExamScreens.HomeScreen.name) {
                    popUpTo(ExamScreens.LoginScreen.name) {
                        inclusive = true
                    }
                }
            })
        }
        composable(ExamScreens.RegisterScreen.name) {
            RegisterScreen(onBackButtonClicked = {
                navController.popBackStack()
            }) {

                navController.navigate(ExamScreens.HomeScreen.name) {
                    popUpTo(ExamScreens.LoginScreen.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(ExamScreens.HomeScreen.name) {
            HomeScreen(onExamCreatedButtonClicked = {
                navController.navigate(ExamScreens.CreateExamScreen.name) {

                }
            },
                onUpdateExamButtonClicked = {examId->
                    navController.navigate(
                        ExamScreens.UpdateExamScreen.name
                                + "/?examId=${examId}"
                    )
                },
                onAddQuestionPerExamButtonClicked = {examId->
                    navController.navigate(
                        ExamScreens.AddQuestionScreen.name
                                + "/?examId=${examId}"
                    )
                })
        }

        composable(ExamScreens.CreateExamScreen.name) {
            CreateExamScreen(onBackButtonClicked = { navController.popBackStack() }) {
                navController.popBackStack()
            }

        }

        composable(ExamScreens.UpdateExamScreen.name
                + "/?examId={examId}",
            arguments = listOf(navArgument("examId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id =  backStackEntry.arguments?.getInt("examId") ?: -1
            UpdateExamScreen(
                onBackButtonClicked = { navController.popBackStack() },
                examId = id
            ) {
                navController.popBackStack()
            }

        }


        composable(ExamScreens.ExamScreen.name) {
            ExamScreen()
        }
        composable(ExamScreens.SearchScreen.name) {
            SearchScreen()
        }

        composable(ExamScreens.AddQuestionScreen.name  + "/?examId={examId}",
            arguments = listOf(navArgument("examId") { type = NavType.IntType })){
                backStackEntry ->
            val id =  backStackEntry.arguments?.getInt("examId") ?: -1
            AddQuestionSuccessScreen()
        }

    }
}