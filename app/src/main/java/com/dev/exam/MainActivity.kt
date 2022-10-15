package com.dev.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.exam.core.nav.ExamNavigation
import com.dev.exam.ui.MainViewModel
import com.dev.exam.ui.theme.ExamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApp()

        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    ExamTheme {
        MyAppContent()
    }
}

@Composable
fun MyAppContent(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        ExamNavigation(mainViewModel.isTokenFound())
    }
}