package com.tabac.rickandmorty.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tabac.rickandmorty.ui.model.CharacterUiModel

@Composable
fun CharacterItemView(character: CharacterUiModel) {
    Text(text = character.name)
}