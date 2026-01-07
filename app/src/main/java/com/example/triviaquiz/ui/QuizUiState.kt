package com.example.triviaquiz.ui

import com.example.triviaquiz.domain.model.Question

// Kokoaa kaikki Quiz-näkymän tilaan liittyvät tiedot yhteen.
data class QuizUiState(
    val isLoading: Boolean = false,              // Onko dataa lataamassa
    val errorMessage: String? = null,          // Virheviesti, jos lataus epäonnistui
    val questions: List<Question> = emptyList(), // Koko pelin kysymyslista
    val currentIndex: Int = 0,                 // Nykyisen kysymyksen indeksi
    val score: Int = 0,                        // Pelaajan pisteet
    val hasAnswered: Boolean = false,          // Onko nykyiseen kysymykseen vastattu
    val selectedAnswer: String? = null,        // Käyttäjän valitsema vastaus
    val isFinished: Boolean = false            // Onko peli päättynyt
) {
    // Palauttaa nykyisen kysymyksen.
    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)

    // Palauttaa kysymysten kokonaismäärän.
    val totalQuestions: Int
        get() = questions.size
}
