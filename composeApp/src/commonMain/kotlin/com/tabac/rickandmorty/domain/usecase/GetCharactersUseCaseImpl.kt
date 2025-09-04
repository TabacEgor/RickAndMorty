package com.tabac.rickandmorty.domain.usecase

import com.tabac.rickandmorty.domain.mappers.toCharacter
import com.tabac.rickandmorty.network.RequestResult
import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.domain.model.CharactersDataModel
import com.tabac.rickandmorty.domain.repo.CharactersRepository
import com.tabac.rickandmorty.network.DataStatus

class GetCharactersUseCaseImpl(private val charactersRepo: CharactersRepository) : GetCharactersUseCase {
    override suspend fun getAllCharacters(): CharactersDataModel {
        val result = charactersRepo.getCharacters()
        return when(val data = result) {
            is RequestResult.Error -> {
                emitError(data.message)
            }
            is RequestResult.Success -> {
                val characters = result.data.results.map { it.toCharacter() }
                emitSuccess(characters)
            }
        }
    }

    private fun emitSuccess(characters: List<Character>): CharactersDataModel {
        return CharactersDataModel(
            dataStatus = DataStatus.Success,
            characters = characters
        )
    }

    private fun emitError(message: String): CharactersDataModel {
        return CharactersDataModel(dataStatus = DataStatus.Error)
    }

}