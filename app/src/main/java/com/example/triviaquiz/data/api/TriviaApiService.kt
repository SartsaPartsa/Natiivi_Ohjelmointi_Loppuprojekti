package com.example.triviaquiz.data.api

import com.example.triviaquiz.data.model.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Tämä rajapinta määrittelee, miten API-kutsuja tehdään Retrofit-kirjaston avulla.
interface TriviaApiService {

    // Määrittelee GET-pyynnön "api.php"-päätepisteeseen.
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 10, // Kysymysten määrä, oletus 10.
        @Query("type") type: String = "multiple" // Kysymysten tyyppi, oletus "multiple".
    ): TriviaResponse // Palauttaa TriviaResponse-olion.
}
