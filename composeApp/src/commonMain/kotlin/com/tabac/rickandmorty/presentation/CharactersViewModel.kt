package com.tabac.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import com.tabac.rickandmorty.domain.usecase.GetCharactersUseCase
import com.tabac.rickandmorty.ui.model.toCharacterUiModel
import com.tabac.rickandmorty.utils.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val appLogger: AppLogger,
): ViewModel() {

    private val _uiState = MutableStateFlow<CharactersViewState>(CharactersViewState.Idle)
    val uiState = _uiState.asStateFlow()

    fun getCharacters() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val characters = getCharactersUseCase.getAllCharacters().map { it.toCharacterUiModel() }
            _uiState.update { CharactersViewState.Success(characters) }
        }
    }
}