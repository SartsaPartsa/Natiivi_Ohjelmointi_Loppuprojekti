package com.example.triviaquiz.data
import android.util.Log
import androidx.core.text.HtmlCompat
import com.example.triviaquiz.core.Result
import com.example.triviaquiz.data.api.RetrofitClient
import com.example.triviaquiz.data.model.TriviaQuestionDto
import com.example.triviaquiz.domain.model.Question

// Vastaa kysymysten noutamisesta API:sta ja muuntamisesta sovelluksen käyttöön.
class TriviaRepository {

    private val api = RetrofitClient.api

    // Noutaa ja palauttaa listan kysymyksiä.
    suspend fun fetchQuestions(amount: Int = 10): Result<List<Question>> {
        try {
            val response = api.getQuestions(amount = amount)

            if (response.response_code != 0) {
                // Palauttaa virheen, jos API-vastaus on epäonnistunut.
                return Result.Error(IllegalStateException("API response_code != 0: ${response.response_code}"))
            }

            // Muuntaa API-vastaukset sovelluksen Question-malleiksi.
            val questions = response.results.map { dto -> mapDtoToDomain(dto) }
            return Result.Success(questions)
        } catch (e: Exception) {
            // Palauttaa virheen, jos API-kutsu epäonnistuu.
            Log.e("TriviaRepository", "API call failed", e)
            return Result.Error(e)
        }
    }

    // Muuntaa API:n DTO-mallin sovelluksen sisäiseksi Question-malliksi.
    private fun mapDtoToDomain(dto: TriviaQuestionDto): Question {
        // Dekoodaa HTML-entiteetit tekstistä.
        val decodedQuestion = decodeHtml(dto.question)
        val decodedCorrect = decodeHtml(dto.correct_answer)
        val decodedIncorrect = dto.incorrect_answers.map { decodeHtml(it) }

        // Yhdistää ja sekoittaa vastausvaihtoehdot.
        val allAnswers = (decodedIncorrect + decodedCorrect).shuffled()

        return Question(
            question = decodedQuestion,
            correctAnswer = decodedCorrect,
            answers = allAnswers
        )
    }

    // Apufunktio HTML-merkkijonojen dekoodaamiseen.
    private fun decodeHtml(value: String): String {
        return HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}
