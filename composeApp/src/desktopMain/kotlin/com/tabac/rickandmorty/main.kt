package com.tabac.rickandmorty

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.tabac.rickandmorty.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RickAndMorty",
    ) {
        App()
    }
}