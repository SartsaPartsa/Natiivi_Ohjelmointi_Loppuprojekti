package com.example.triviaquiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.triviaquiz.domain.model.Question
import com.example.triviaquiz.ui.QuizUiState
import com.example.triviaquiz.ui.QuizViewModel

// Pelin päänäkymä, joka kokoaa yhteen pelin tilan ja toiminnot.
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = viewModel(),
    onNavigateToInfo: () -> Unit,
    onNavigateToMenu: () -> Unit
) {
    val state = viewModel.uiState

    QuizScreenContent(
        state = state,
        onAnswerSelected = viewModel::onAnswerSelected,
        onNextQuestion = viewModel::onNextQuestion,
        onRestart = viewModel::restartQuiz,
        onInfoClick = onNavigateToInfo,
        onMenuClick = onNavigateToMenu
    )
}

// Vastaa käyttöliittymän piirtämisestä pelin eri tilanteissa.
@Composable
fun QuizScreenContent(
    state: QuizUiState,
    onAnswerSelected: (String) -> Unit,
    onNextQuestion: () -> Unit,
    onRestart: () -> Unit,
    onInfoClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            // Näyttää latausindikaattorin, kun dataa haetaan.
            state.isLoading -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(16.dp))
                    Text("Ladataan kysymyksiä...")
                }
            }

            // Näyttää virheilmoituksen, jos lataus epäonnistui.
            state.errorMessage != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Virhe ladattaessa kysymyksiä", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(state.errorMessage)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = onRestart) { Text("Yritä uudestaan") }
                }
            }

            // Näyttää lopputuloksen, kun peli on päättynyt.
            state.isFinished -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Valmis!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(text = "Pisteesi: ${state.score} / ${state.totalQuestions}", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(24.dp))
                    Button(onClick = onRestart) { Text("Pelaa uudestaan") }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = onMenuClick) { Text("Päävalikkoon") }
                }
            }

            // Näyttää kysymysnäkymän pelin aikana.
            else -> {
                val question: Question? = state.currentQuestion
                if (question == null) {
                    Text("Ei kysymyksiä", modifier = Modifier.align(Alignment.Center))
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Kysymys ${state.currentIndex + 1} / ${state.totalQuestions}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Row {
                                OutlinedButton(onClick = onInfoClick) { Text("Info") }
                                Spacer(Modifier.width(8.dp))
                                OutlinedButton(onClick = onMenuClick) { Text("Valikko") }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(text = question.question, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

                        Spacer(Modifier.height(24.dp))

                        // Listaa vastausvaihtoehdot.
                        question.answers.forEach { answer ->
                            val isCorrect = answer == question.correctAnswer
                            val isSelected = answer == state.selectedAnswer
                            val label = when {
                                state.hasAnswered && isCorrect -> " (oikea)"
                                state.hasAnswered && isSelected && !isCorrect -> " (väärä)"
                                else -> ""
                            }
                            val enabled = !state.hasAnswered
                            Spacer(Modifier.height(8.dp))
                            Button(
                                onClick = { onAnswerSelected(answer) },
                                enabled = enabled,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(answer + label)
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        // Näyttää palautteen vastauksen jälkeen.
                        if (state.hasAnswered) {
                            val correct = state.selectedAnswer == question.correctAnswer
                            Text(
                                text = if (correct) "Oikein!" else "Väärin. Oikea vastaus: ${question.correctAnswer}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(16.dp))
                            Button(onClick = onNextQuestion, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                Text("Seuraava kysymys")
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        // Näyttää nykyiset pisteet.
                        Text(
                            text = "Pisteet: ${state.score}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }
    }
}
