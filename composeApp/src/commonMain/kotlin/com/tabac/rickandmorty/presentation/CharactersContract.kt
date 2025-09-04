package com.tabac.rickandmorty.presentation

import com.tabac.rickandmorty.ui.model.CharacterUiModel
import com.tabac.rickandmorty.utils.Effect
import com.tabac.rickandmorty.utils.UserIntent
import com.tabac.rickandmorty.utils.ViewState

sealed class CharactersViewState : ViewState {
    data object Idle : CharactersViewState()
    data object Loading : CharactersViewState()
    data class Success(val characters: List<CharacterUiModel>) : CharactersViewState()
    data class Error(val message: String) : CharactersViewState()
}

sealed interface CharactersIntent : UserIntent {
    data object EnterScreen : CharactersIntent
    data object RetryLoadCharacters : CharactersIntent
}

sealed interface CharactersEffect : Effect {

}