package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.ICharactersRemote
import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.data.model.LocationData
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.data.remote.service.ServiceFactory
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.koin.dsl.module

class CharactersRemoteImpl constructor(
    private val serviceFactory: ServiceFactory
) : ICharactersRemote {

    override fun getAllCharacters(): Observable<Characters> {
        return serviceFactory.createService(true).getAllCharacters()
    }

    override fun getLocationCharacters(locationName: String): Observable<LocationData> {
        return serviceFactory.createService(true).getLocationCharacters(locationName)
    }

    override fun getSingleCharacter(characterUrl: String): Observable<Results> =
        serviceFactory.createService(true).getSingleCharacter(characterUrl)
}