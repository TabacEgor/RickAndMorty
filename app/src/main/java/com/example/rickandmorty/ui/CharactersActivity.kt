package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmorty.BaseActivity
import com.example.rickandmorty.BaseFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity() {

    override var fragment: BaseFragment = CharactersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}