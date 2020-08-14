package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class LocationData(
    val info: Info,
    @SerializedName("results")
    val locationResults: List<LocationResults>
)