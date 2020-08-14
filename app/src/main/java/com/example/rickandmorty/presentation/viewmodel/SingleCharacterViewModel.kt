package com.example.rickandmorty.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.BaseViewModel
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.presentation.ICharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SingleCharacterViewModel constructor(
    private val charactersRepository: ICharactersRepository
) : BaseViewModel() {

    var charactersByLocationList: MutableLiveData<MutableList<Results>> = MutableLiveData()
    val characters: MutableList<Results> = ArrayList<Results>()
    var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getLocationCharacters(locationName: String) {
        charactersRepository.getLocationCharacters(locationName)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { it.locationResults.flatMap { locationResults ->
                    locationResults.residents.map { characterUrl ->
                        charactersRepository.getSingleCharacter(characterUrl)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe (
                                {
                                    characters.add(it)
                                    charactersByLocationList.value = characters
                                },
                                { Log.d("TAG", it.message.toString()) },
                                {  }
                        )
                    }
                }
            }, {
                Log.d(CharactersViewModel.TAG, it.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}