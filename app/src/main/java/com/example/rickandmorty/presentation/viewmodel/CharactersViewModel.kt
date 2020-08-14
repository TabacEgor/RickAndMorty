package com.example.rickandmorty.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.BaseViewModel
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.presentation.ICharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CharactersViewModel constructor(
    private val charactersRepository: ICharactersRepository
) : BaseViewModel() {

    companion object {
        const val TAG = "CharactersViewModel"
    }

    var charactersList: MutableLiveData<List<Results>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
          charactersRepository.getAllCharacters()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({
                 charactersList.value = it.results
             }, {
                 Log.d(Companion.TAG, it.message)
             })
    }

    override fun onCleared() {
        super.onCleared()
    }
}