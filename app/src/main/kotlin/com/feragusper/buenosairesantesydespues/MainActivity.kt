package com.feragusper.buenosairesantesydespues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.feragusper.buenosairesantesydespues.core.designsystem.theme.BaadTheme
import com.feragusper.buenosairesantesydespues.navigation.BaadNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            BaadTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BaadNavHost()
                }
            }
        }
    }
}
