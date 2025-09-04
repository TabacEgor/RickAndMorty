package com.tabac.rickandmorty.domain.model

import com.tabac.rickandmorty.network.DataStatus

data class CharactersDataModel(
    val dataStatus: DataStatus = DataStatus.Empty,
    val characters: List<Character> = emptyList(),
) {
    companion object {
        fun empty() = CharactersDataModel(
            dataStatus = DataStatus.Empty,
            characters = emptyList()
        )
    }
}