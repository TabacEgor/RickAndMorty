package com.tabac.rickandmorty.data

import com.tabac.rickandmorty.data.model.CharactersResponse
import com.tabac.rickandmorty.data.remote.CharactersRemoteDataSource
import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.domain.repo.CharactersRepository
import com.tabac.rickandmorty.network.NetworkError
import com.tabac.rickandmorty.network.RequestResult

class CharactersRepositoryImpl(
    private val charactersRemoteDataSourceImpl: CharactersRemoteDataSource
) : CharactersRepository {

    override suspend fun getCharacters(): RequestResult<CharactersResponse, NetworkError> {
        return charactersRemoteDataSourceImpl.getAllCharacters()
    }
}