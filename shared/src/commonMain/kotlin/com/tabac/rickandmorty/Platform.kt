package com.tabac.rickandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform