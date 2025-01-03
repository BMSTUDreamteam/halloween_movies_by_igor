package com.ba.halloweenmovies.ui.screens.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ba.halloweenmovies.data.repository.FilmsRepository
import com.ba.halloweenmovies.ui.screens.FilmListScreen
import com.ba.halloweenmovies.ui.screens.MainScreensBase
import com.ba.halloweenmovies.ui.screens.Screen


class FavouritesListViewModelFactory(private val films: FilmsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouritesViewModel(films) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun FavouritesScreen(screen: Screen, navController: NavHostController, films: FilmsRepository) {
    val viewModel: FavouritesViewModel = viewModel(
        factory = FavouritesListViewModelFactory(films)
    )
    val screenState = viewModel.screenState.collectAsState()

    MainScreensBase(
        screen = screen,
        navController = navController
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            FilmListScreen(viewModel, screenState, false)
        }
    }
}
