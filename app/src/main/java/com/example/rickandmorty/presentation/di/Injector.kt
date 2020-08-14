package com.example.rickandmorty.presentation.di

import com.example.rickandmorty.data.CharactersRepositoryImpl
import com.example.rickandmorty.data.ICharactersRemote
import com.example.rickandmorty.data.remote.CharactersRemoteImpl
import com.example.rickandmorty.data.remote.service.ServiceFactory
import com.example.rickandmorty.presentation.ICharactersRepository
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import com.example.rickandmorty.presentation.viewmodel.SingleCharacterViewModel
import com.example.rickandmorty.ui.CharactersFragment
import com.example.rickandmorty.ui.SingleCharacterFragment
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Injector {

    val appModule: Module = module {
        single { ServiceFactory() }
        single<ICharactersRemote> { CharactersRemoteImpl(get()) }
        single<ICharactersRepository> { CharactersRepositoryImpl(get()) }
        viewModel { CharactersViewModel(get()) }
        viewModel { SingleCharacterViewModel(get()) }
    }
}
