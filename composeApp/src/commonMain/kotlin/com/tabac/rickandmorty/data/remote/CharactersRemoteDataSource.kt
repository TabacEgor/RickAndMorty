package com.tabac.rickandmorty.data.remote

import com.tabac.rickandmorty.network.RequestResult
import com.tabac.rickandmorty.data.model.CharactersResponse
import com.tabac.rickandmorty.network.NetworkError

interface CharactersRemoteDataSource {
    suspend fun getAllCharacters(): RequestResult<CharactersResponse, NetworkError>
}