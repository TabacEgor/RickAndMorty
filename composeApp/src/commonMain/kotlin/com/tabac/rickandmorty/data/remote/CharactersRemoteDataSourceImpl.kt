package com.tabac.rickandmorty.data.remote

import com.tabac.rickandmorty.data.model.CharacterDTO
import com.tabac.rickandmorty.data.model.CharactersResponse
import com.tabac.rickandmorty.domain.repo.CharactersRepository
import com.tabac.rickandmorty.network.NetworkService
import com.tabac.rickandmorty.utils.AppLogger
import com.tabac.rickandmorty.utils.BASE_URL
import com.tabac.rickandmorty.utils.CHARACTER_ENDPOINT
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.logger.Level

class CharactersRemoteDataSourceImpl(
    private val service: NetworkService,
    private val logger: AppLogger,
) : CharactersRemoteDataSource {
    override suspend fun getAllCharacters(): CharactersResponse {
        val response = service.client.get(BASE_URL + CHARACTER_ENDPOINT)
        logger.display(Level.DEBUG, "Response: ${response.bodyAsText()}")
        return response.body()
    }
}