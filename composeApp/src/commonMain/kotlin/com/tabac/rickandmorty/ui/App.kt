package com.tabac.rickandmorty.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.tabac.rickandmorty.di.dataModule
import com.tabac.rickandmorty.di.viewModelModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.logger.Level

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        printLogger(Level.DEBUG)
        modules(dataModule, viewModelModule)
    }) {
        MaterialTheme {
            CharactersScreen()
        }
    }
}