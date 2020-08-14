package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.BaseFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.ui.adapter.CharacterItemDecorator
import com.example.rickandmorty.utils.DeviceUtil
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : BaseFragment() {
    companion object {
        const val TAG = "CharactersFragment"
    }

    override val layoutId: Int = R.layout.fragment_characters

    private val charactersViewModel: CharactersViewModel by viewModel()

    private lateinit var rvCharacters: RecyclerView
    private val characterAdapter = CharacterAdapter()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersViewModel.getAllCharacters()

        rvCharacters = view.findViewById<RecyclerView>(R.id.rvCharacters).apply {
            setHasFixedSize(true)
            addItemDecoration(CharacterItemDecorator(DeviceUtil.dpToPx(16)))
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

        charactersViewModel.charactersList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            characterAdapter.submitList(it)
        })

        characterAdapter.setOnClick { any, view ->
            when (view.id) {
                R.id.characterContainer -> {
                    val characterIntent = Intent(context, SingleCharacterActivity::class.java)
                    (any as? Results)?.let {
                        characterIntent.putExtra("characterName", it.name)
                        characterIntent.putExtra("location", it.location.name)
                        characterIntent.putExtra("origin", it.origin.name)
                        characterIntent.putExtra("status", it.status)
                        characterIntent.putExtra("photo", it.image)
                    }
                    startActivity(characterIntent)
                }
            }
        }
        val disposable = CompositeDisposable()
    }
}