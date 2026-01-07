package com.example.triviaquiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Sovelluksen aloitusnäyttö, josta voi aloittaa pelin tai siirtyä info-näkymään.
@Composable
fun StartScreen(
    onStartGameClick: () -> Unit, // Navigointifunktio pelin aloittamiseksi.
    onInfoClick: () -> Unit      // Navigointifunktio info-näkymään.
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center // Keskitetään sisältö.
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sovelluksen otsikko.
            Text(
                text = "Trivia",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Valitse aloitus:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(24.dp))

            // Nappi pelin aloittamiseen.
            Button(
                onClick = onStartGameClick,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Aloita peli")
            }

            Spacer(Modifier.height(12.dp))

            // Nappi info-näkymään siirtymiseen.
            OutlinedButton(
                onClick = onInfoClick,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Näytä info")
            }
        }
    }
}
