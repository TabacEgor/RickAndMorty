package com.tabac.rickandmorty.domain.usecase

import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.domain.model.CharactersDataModel

interface GetCharactersUseCase {
    suspend fun getAllCharacters(): CharactersDataModel
}