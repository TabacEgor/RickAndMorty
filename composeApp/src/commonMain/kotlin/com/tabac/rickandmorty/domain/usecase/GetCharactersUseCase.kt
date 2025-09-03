package com.tabac.rickandmorty.domain.usecase

import com.tabac.rickandmorty.domain.model.Character

interface GetCharactersUseCase {
    suspend fun getAllCharacters(): List<Character>
}