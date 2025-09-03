package com.tabac.rickandmorty.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tabac.rickandmorty.di.dataModule
import com.tabac.rickandmorty.di.viewModelModule
import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.presentation.CharactersViewModel
import com.tabac.rickandmorty.presentation.CharactersViewState
import com.tabac.rickandmorty.ui.model.CharacterUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.logger.Level

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        printLogger(Level.DEBUG)
        modules(dataModule, viewModelModule)
    }) {
        val charactersViewModel: CharactersViewModel = koinViewModel()
        MaterialTheme {
            val state = charactersViewModel.uiState.collectAsState()

            LaunchedEffect(true) {
                charactersViewModel.getCharacters()
            }

            when (val s = state.value) {
                is CharactersViewState.Idle -> {}
                is CharactersViewState.Loading -> {}
                is CharactersViewState.Success -> {
                    Column(
                        modifier = Modifier
                            .safeContentPadding()
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LazyColumn {
                            items(s.characters.size) { index ->
                                CharacterItemView(character = s.characters[index])
                            }
                        }

                    }
                }
                is CharactersViewState.Error -> {}
            }
        }
    }
}

@Composable
private fun CharacterItemView(character: CharacterUiModel) {
    Text(text = character.name)
}