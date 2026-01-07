package com.example.triviaquiz.domain.model

// Edustaa yhtä triviakysymystä pelissä.
data class Question(
    val question: String,                      // Kysymyksen teksti.
    val correctAnswer: String,                 // Oikea vastaus.
    val answers: List<String>                  // Lista kaikista vastausvaihtoehdoista (oikea ja väärät).
)
