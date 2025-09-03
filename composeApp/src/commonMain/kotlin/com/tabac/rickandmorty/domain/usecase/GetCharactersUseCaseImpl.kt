package com.tabac.rickandmorty.domain.usecase

import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.domain.repo.CharactersRepository

class GetCharactersUseCaseImpl(private val charactersRepo: CharactersRepository) : GetCharactersUseCase {
    override suspend fun getAllCharacters(): List<Character> {
        return charactersRepo.getCharacters()
    }
}