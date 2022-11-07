package com.dev.exam.core.nav



enum class ExamScreens {
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    SearchScreen,
    ExamScreen,
    CreateExamScreen,;


    companion object {
        fun fromRoute(route: String): ExamScreens = when (route.substringBefore("/")) {
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            ExamScreen.name -> ExamScreen
            CreateExamScreen.name -> CreateExamScreen
            else -> throw IllegalArgumentException("No Screen Found")
        }
    }


}