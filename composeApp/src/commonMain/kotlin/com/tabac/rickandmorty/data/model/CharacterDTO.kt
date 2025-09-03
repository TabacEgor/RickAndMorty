package com.tabac.rickandmorty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDTO,
    val location: LocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class OriginDTO(
    val name: String,
    val url: String
)

@Serializable
data class LocationDTO(
    val name: String,
    val url: String
)
