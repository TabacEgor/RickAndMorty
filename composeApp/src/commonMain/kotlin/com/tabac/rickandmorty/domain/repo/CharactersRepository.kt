package com.tabac.rickandmorty.domain.repo

import com.tabac.rickandmorty.data.model.CharactersResponse
import com.tabac.rickandmorty.network.RequestResult
import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.network.NetworkError

interface CharactersRepository {
    suspend fun getCharacters() : RequestResult<CharactersResponse, NetworkError>
}