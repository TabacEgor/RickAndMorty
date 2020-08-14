package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.BaseFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.presentation.viewmodel.SingleCharacterViewModel
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.ui.adapter.CharacterItemDecorator
import com.example.rickandmorty.ui.adapter.CharactersAlsoFromAdapter
import com.example.rickandmorty.utils.DeviceUtil
import com.example.rickandmorty.utils.GlideHelper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_single_character.*
import org.koin.android.viewmodel.ext.android.viewModel

class SingleCharacterFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_single_character

    private val singleCharacterViewModel: SingleCharacterViewModel by viewModel()

    private lateinit var characterAdapter: CharactersAlsoFromAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.intent?.run {
            val characterName = getStringExtra("characterName")
            val location = getStringExtra("location")
            val origin = getStringExtra("origin")
            val status = getStringExtra("status")
            val photo = getStringExtra("photo")
            val singleCharacterData = mapOf<String, String>(
                "characterName" to characterName,
                "location" to location,
                "origin" to origin,
                "status" to status,
                "photo" to photo
            )
            characterAdapter = CharactersAlsoFromAdapter(singleCharacterData)
//            tvCharacterName.text = characterName
//            tvLastLocation.text = location
//            tvFirstSeen.text = origin
//            tvStatus.text = status
//            context?.let { GlideHelper.loadImageSingleCharacter(it, photo, ivCharacterPhoto) }
//            if (status == "Alive") {
//                ivStatus.setImageDrawable(resources.getDrawable(R.drawable.status_alive, null))
//            } else {
//                ivStatus.setImageDrawable(resources.getDrawable(R.drawable.status_dead, null))
//            }

            singleCharacterViewModel.getLocationCharacters(location)
            view.findViewById<RecyclerView>(R.id.rvCharactersSameLocation).apply {
                setHasFixedSize(true)
                addItemDecoration(CharacterItemDecorator(DeviceUtil.dpToPx(16)))
                layoutManager = LinearLayoutManager(context)
                adapter = characterAdapter
            }
            singleCharacterViewModel.charactersByLocationList.observe(viewLifecycleOwner, Observer {
                characterAdapter.submitList(it)
            })
        }
    }
}