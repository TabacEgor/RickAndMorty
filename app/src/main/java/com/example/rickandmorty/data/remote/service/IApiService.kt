package com.example.rickandmorty.data.remote.service

import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.data.model.LocationData
import com.example.rickandmorty.data.model.Results
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IApiService {
    companion object {
        const val GET_ALL_CHARACTERS = "character"
        const val GET_LOCATION_CHARACTERS = "location"
    }

    @GET(GET_ALL_CHARACTERS)
    fun getAllCharacters(): Observable<Characters>

    @GET(GET_LOCATION_CHARACTERS)
    fun getLocationCharacters(@Query("name") locationName: String): Observable<LocationData>

    @GET
    fun getSingleCharacter(@Url characterUrl: String): Observable<Results>

}