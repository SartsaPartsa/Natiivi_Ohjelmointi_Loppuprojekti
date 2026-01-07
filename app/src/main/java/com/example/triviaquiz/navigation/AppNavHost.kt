package com.example.triviaquiz.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triviaquiz.ui.QuizViewModel
import com.example.triviaquiz.ui.screens.InfoScreen
import com.example.triviaquiz.ui.screens.QuizScreen
import com.example.triviaquiz.ui.screens.StartScreen

// Määrittelee sovelluksen kaikki navigaatioreitit ja niiden välisen logiikan.
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    // NavHost toimii säilönä kaikille navigoitaville näkymille.
    NavHost(navController = navController, startDestination = "start") {
        // Aloitusnäyttö.
        composable("start") {
            StartScreen(
                onStartGameClick = { navController.navigate("quiz") }, // Siirtyy pelinäkymään.
                onInfoClick = { navController.navigate("info") }      // Siirtyy info-näkymään.
            )
        }

        // Pelinäkymä.
        composable("quiz") {
            val viewModel: QuizViewModel = viewModel()
            QuizScreen(
                viewModel = viewModel,
                onNavigateToInfo = {
                    navController.navigate("info") // Siirtyy info-näkymään.
                },
                onNavigateToMenu = { // Toiminto päävalikkoon palaamiseksi.
                    // Poistaa kaikki näkymät pinosta aloitusnäkymään asti.
                    navController.popBackStack(
                        route = "start",
                        inclusive = false
                    )
                }
            )
        }

        // Info-näkymä.
        composable("info") {
            InfoScreen(
                onBackClick = { navController.popBackStack() } // Palaa edelliseen näkymään.
            )
        }
    }
}
