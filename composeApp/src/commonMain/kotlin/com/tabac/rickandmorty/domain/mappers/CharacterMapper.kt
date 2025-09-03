package com.tabac.rickandmorty.domain.mappers

import com.tabac.rickandmorty.data.model.CharacterDTO
import com.tabac.rickandmorty.domain.model.Character

fun CharacterDTO.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name
    )
}