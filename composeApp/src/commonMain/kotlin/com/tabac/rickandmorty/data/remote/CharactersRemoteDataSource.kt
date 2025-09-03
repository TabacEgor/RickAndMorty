package com.tabac.rickandmorty.data.remote

import com.tabac.rickandmorty.data.model.CharacterDTO
import com.tabac.rickandmorty.data.model.CharactersResponse

interface CharactersRemoteDataSource {
    suspend fun getAllCharacters(): CharactersResponse
}