package com.tabac.rickandmorty.di

import com.tabac.rickandmorty.data.CharactersRepositoryImpl
import com.tabac.rickandmorty.data.remote.CharactersRemoteDataSource
import com.tabac.rickandmorty.data.remote.CharactersRemoteDataSourceImpl
import com.tabac.rickandmorty.domain.repo.CharactersRepository
import com.tabac.rickandmorty.domain.usecase.GetCharactersUseCase
import com.tabac.rickandmorty.domain.usecase.GetCharactersUseCaseImpl
import com.tabac.rickandmorty.network.NetworkService
import com.tabac.rickandmorty.presentation.CharactersViewModel
import com.tabac.rickandmorty.utils.AppLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {
    single { AppLogger() }
    singleOf(::NetworkService) { bind<NetworkService>() }
    singleOf(::CharactersRepositoryImpl) { bind<CharactersRepository>() }
    singleOf(::CharactersRemoteDataSourceImpl) { bind<CharactersRemoteDataSource>() }
    singleOf(::GetCharactersUseCaseImpl) { bind<GetCharactersUseCase>() }
}

val viewModelModule = module {
    viewModelOf(::CharactersViewModel)
}

fun initKoin() {
    startKoin {
        logger(AppLogger())
        modules(
            dataModule,
            viewModelModule
        )
    }
}