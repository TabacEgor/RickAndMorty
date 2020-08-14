package com.example.rickandmorty.ui.adapter

data class AdapterItem<out T>(val value: T?, val viewType: Int)