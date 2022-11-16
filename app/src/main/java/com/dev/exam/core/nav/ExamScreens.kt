package com.dev.exam.core.nav



enum class ExamScreens {
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    SearchScreen,
    ExamScreen,
    CreateExamScreen,
    UpdateExamScreen,
    AddQuestionScreen,
    UpdateQuestionScreen,;


    companion object {
        fun fromRoute(route: String): ExamScreens = when (route.substringBefore("/")) {
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            ExamScreen.name -> ExamScreen
            CreateExamScreen.name -> CreateExamScreen
            UpdateExamScreen.name -> UpdateExamScreen
            AddQuestionScreen.name -> AddQuestionScreen
            UpdateQuestionScreen.name -> UpdateQuestionScreen
            else -> throw IllegalArgumentException("No Screen Found")
        }
    }


}