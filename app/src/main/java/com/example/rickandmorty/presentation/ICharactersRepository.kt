package com.example.rickandmorty.presentation

import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.data.model.LocationData
import com.example.rickandmorty.data.model.Results
import io.reactivex.Observable
import okhttp3.ResponseBody

interface ICharactersRepository {

    fun getAllCharacters(): Observable<Characters>

    fun getLocationCharacters(locationName: String): Observable<LocationData>

    fun getSingleCharacter(characterUrl: String): Observable<Results>
}