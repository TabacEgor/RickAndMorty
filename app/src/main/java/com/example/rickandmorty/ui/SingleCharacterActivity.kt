package com.example.rickandmorty.ui

import android.os.Bundle
import com.example.rickandmorty.BaseActivity
import com.example.rickandmorty.BaseFragment

class SingleCharacterActivity : BaseActivity() {

    override var fragment: BaseFragment = SingleCharacterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}