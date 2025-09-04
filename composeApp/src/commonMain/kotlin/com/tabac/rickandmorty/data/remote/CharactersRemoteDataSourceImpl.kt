package com.tabac.rickandmorty.data.remote

import com.tabac.rickandmorty.data.model.CharactersResponse
import com.tabac.rickandmorty.network.NetworkError
import com.tabac.rickandmorty.network.NetworkService
import com.tabac.rickandmorty.network.RequestResult
import com.tabac.rickandmorty.network.safeGet
import com.tabac.rickandmorty.utils.AppLogger
import com.tabac.rickandmorty.utils.BASE_URL
import com.tabac.rickandmorty.utils.CHARACTER_ENDPOINT
import org.koin.core.logger.Level

class CharactersRemoteDataSourceImpl(
    private val service: NetworkService,
    private val logger: AppLogger,
) : CharactersRemoteDataSource {
    override suspend fun getAllCharacters(): RequestResult<CharactersResponse, NetworkError> {
        val response = service.client.safeGet<CharactersResponse>(BASE_URL + CHARACTER_ENDPOINT)
        logger.display(Level.DEBUG, "Response: ${response}")
        return response
    }
}