package com.tabac.rickandmorty.data

import com.tabac.rickandmorty.data.remote.CharactersRemoteDataSource
import com.tabac.rickandmorty.domain.mappers.toCharacter
import com.tabac.rickandmorty.domain.model.Character
import com.tabac.rickandmorty.domain.repo.CharactersRepository

class CharactersRepositoryImpl(private val charactersRemoteDataSourceImpl: CharactersRemoteDataSource) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        return charactersRemoteDataSourceImpl.getAllCharacters().results.map {
            it.toCharacter()
        }
    }
}