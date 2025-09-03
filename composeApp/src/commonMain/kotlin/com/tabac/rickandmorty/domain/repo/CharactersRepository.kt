package com.tabac.rickandmorty.domain.repo

import com.tabac.rickandmorty.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}