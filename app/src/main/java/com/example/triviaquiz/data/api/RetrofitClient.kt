package com.example.triviaquiz.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Tämä objekti luo ja hallinnoi Retrofit-instanssia API-kutsuja varten.
object RetrofitClient {

    // API:n base URL, josta data haetaan.
    private const val BASE_URL = "https://opentdb.com/"

    // Luodaan Retrofit-instanssi laiskasti (lazy initialization).
    val api: TriviaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Asetetaan base URL.
            .addConverterFactory(GsonConverterFactory.create()) // Lisätään Gson-muunnin JSON-datan käsittelyyn.
            .build()
            .create(TriviaApiService::class.java) // Luodaan API-palvelun toteutus.
    }
}
