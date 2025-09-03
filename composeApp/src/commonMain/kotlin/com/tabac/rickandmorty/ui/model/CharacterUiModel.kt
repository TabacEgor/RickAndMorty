package com.tabac.rickandmorty.ui.model

import com.tabac.rickandmorty.domain.model.Character

data class CharacterUiModel(
    val name: String,
)

fun Character.toCharacterUiModel(): CharacterUiModel {
    return CharacterUiModel(
        name = this.name
    )
}
