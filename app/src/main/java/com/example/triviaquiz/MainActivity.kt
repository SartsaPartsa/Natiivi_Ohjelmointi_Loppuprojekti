package com.example.triviaquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.triviaquiz.navigation.AppNavHost
import com.example.triviaquiz.ui.theme.TriviaQuizTheme

// Sovelluksen pääaktiviteetti, joka toimii Jetpack Compose -näkymien isäntänä.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asetetaan näkymän sisältö käyttämällä Jetpack Composea.
        setContent {
            // Käytetään sovelluksen omaa teemaa.
            TriviaQuizTheme {
                // Surface-elementti toimii päänäkymän taustana.
                Surface {
                    // AppNavHost vastaa sovelluksen sisäisestä navigoinnista.
                    AppNavHost()
                }
            }
        }
    }
}
