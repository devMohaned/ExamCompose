package com.dev.exam.core.nav



enum class ExamScreens {
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    SearchScreen,
    ExamScreen,;


    companion object {
        fun fromRoute(route: String): ExamScreens = when (route.substringBefore("/")) {
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            else -> throw IllegalArgumentException("No Screen Found")
        }
    }


}