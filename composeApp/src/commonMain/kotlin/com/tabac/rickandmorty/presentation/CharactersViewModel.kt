package com.tabac.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import com.tabac.rickandmorty.domain.model.CharactersDataModel
import com.tabac.rickandmorty.domain.usecase.GetCharactersUseCase
import com.tabac.rickandmorty.network.DataStatus
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
            val charactersDataModel = getCharactersUseCase.getAllCharacters()
            parseAndEmitCharactersData(charactersDataModel)
        }
    }

    private fun parseAndEmitCharactersData(charactersDataModel: CharactersDataModel) {
        when(charactersDataModel.dataStatus) {
            DataStatus.Error -> {
                _uiState.update { CharactersViewState.Error("Error fetching characters") }
            }
            DataStatus.Success -> {
                val characters = charactersDataModel.characters.map { it.toCharacterUiModel() }
                _uiState.update { CharactersViewState.Success(characters) }
            }
            else -> {}
        }
    }
}