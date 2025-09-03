package com.tabac.rickandmorty.presentation

import com.tabac.rickandmorty.ui.model.CharacterUiModel

sealed class CharactersViewState {
    data object Idle : CharactersViewState()
    data object Loading : CharactersViewState()
    data class Success(val characters: List<CharacterUiModel>) : CharactersViewState()
    data class Error(val message: String) : CharactersViewState()
}