package com.example.triviaquiz.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaquiz.core.Result
import com.example.triviaquiz.data.TriviaRepository
import com.example.triviaquiz.domain.model.Question
import kotlinx.coroutines.launch

// Vastaa pelin logiikasta ja tilanhallinnasta.
class QuizViewModel(
    private val repository: TriviaRepository = TriviaRepository()
) : ViewModel() {

    // Kokoaa käyttöliittymän tilan (UI state).
    var uiState by mutableStateOf(QuizUiState(isLoading = true))
        private set

    init {
        // Ladataan kysymykset heti alussa.
        loadQuestions()
    }

    // Lataa kysymykset ja rakentaa pelitilan.
    fun loadQuestions() {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = repository.fetchQuestions(10)) {
                is Result.Success -> {
                    // Nollataan tila ja asetetaan uudet kysymykset.
                    uiState = QuizUiState(
                        isLoading = false,
                        questions = result.data,
                        currentIndex = 0,
                        score = 0,
                        hasAnswered = false,
                        selectedAnswer = null,
                        isFinished = false,
                        errorMessage = null
                    )
                }
                is Result.Error -> {
                    // Virheen sattuessa päivitetään virheilmoitus tilaan.
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.exception.localizedMessage ?: "Tuntematon virhe"
                    )
                }
            }
        }
    }

    // Kutsutaan, kun käyttäjä valitsee vastauksen.
    fun onAnswerSelected(answer: String) {
        if (uiState.hasAnswered || uiState.isFinished) return

        val isCorrect = uiState.currentQuestion?.correctAnswer == answer

        // Päivitetään tila valitulla vastauksella ja kasvatetaan pisteitä tarvittaessa.
        uiState = uiState.copy(
            hasAnswered = true,
            selectedAnswer = answer,
            score = if (isCorrect) uiState.score + 1 else uiState.score
        )
    }

    // Siirtyy seuraavaan kysymykseen tai päättää pelin.
    fun onNextQuestion() {
        if (uiState.currentIndex + 1 >= uiState.totalQuestions) {
            // Peli päättyy, kun kaikki kysymykset on käyty läpi.
            uiState = uiState.copy(isFinished = true, hasAnswered = false, selectedAnswer = null)
        } else {
            // Siirrytään seuraavaan kysymykseen ja nollataan vastaustila.
            uiState = uiState.copy(
                currentIndex = uiState.currentIndex + 1,
                hasAnswered = false,
                selectedAnswer = null
            )
        }
    }

    // Aloittaa pelin alusta.
    fun restartQuiz() {
        loadQuestions()
    }
}
