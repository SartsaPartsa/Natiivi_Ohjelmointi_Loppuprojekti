package com.example.triviaquiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Näyttää tietoja sovelluksen toiminnasta ja teknisestä toteutuksesta.
@Composable
fun InfoScreen(
    onBackClick: () -> Unit // Navigointifunktio takaisin edelliseen näkymään.
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Vieritettävä sarake, joka sisältää kaikki tiedot.
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 56.dp) // Lisätään tilaa napille
        ) {
            // Otsikko.
            Text(
                text = "Trivia",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            // Selitys sovelluksen ideasta.
            Text(
                text = "Sovelluksen idea",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Vastaat Open Trivia DB -API:sta haettuihin kysymyksiin."
            )

            Spacer(Modifier.height(16.dp))

            // Selitys teknisestä toteutuksesta.
            Text(
                text = "Tekninen toteutus",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = """
• Arkkitehtuuri: MVVM
  - View (Jetpack Compose -näkymät)
  - ViewModel (QuizViewModel), joka sisältää pelilogiikan
  - Repository (TriviaRepository), joka hakee ja muuntaa datan
  - Data layer: Retrofit ApiService Open Trivia DB -API:lle

• API:
  - Open Trivia DB
  - Endpoint: https://opentdb.com/api.php?amount=10&type=multiple

• Lataus & virheet:
  - isLoading: näyttää CircularProgressIndicatorin
  - errorMessage: näyttää virheviestin ja 'Yritä uudestaan' -napin

• Tilanhallinta:
  - QuizUiState sisältää koko käyttöliittymän tilan

• UI-flow:
  - QuizScreen: näyttää kysymykset, vastaukset ja palautteen
  - InfoScreen: tämä näkymä, jossa kerrotaan ideasta ja tekniikasta
                """.trimIndent()
            )
        }

        // Nappi, jolla pääsee takaisin edelliseen näkymään.
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text("Takaisin quizziin")
        }
    }
}
