package com.example.rickandmorty.data

import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.data.model.LocationData
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.presentation.ICharactersRepository
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.koin.dsl.module

class CharactersRepositoryImpl constructor(
    private val charactersRemote: ICharactersRemote
) : ICharactersRepository {

    override fun getAllCharacters(): Observable<Characters> {
        return charactersRemote.getAllCharacters()
    }

    override fun getLocationCharacters(locationName: String): Observable<LocationData> {
        return charactersRemote.getLocationCharacters(locationName)
    }

    override fun getSingleCharacter(characterUrl: String): Observable<Results> {
        return charactersRemote.getSingleCharacter(characterUrl)
    }
}