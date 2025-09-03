package com.tabac.rickandmorty.data.model

import com.tabac.rickandmorty.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    val results: List<CharacterDTO>
)

@Serializable
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)