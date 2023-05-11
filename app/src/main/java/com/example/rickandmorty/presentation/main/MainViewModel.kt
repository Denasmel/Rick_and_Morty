package com.example.rickandmorty.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val characterLiveData = MutableLiveData<List<com.example.rickandmorty.data.model.Character>>()

    private var pageNumber: Int = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            pageNumber++
            characterLiveData.postValue(repository.getListOfCharacters(pageNumber))
        }
    }

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            pageNumber++
            characterLiveData.postValue(repository.getListOfCharacters(pageNumber))
        }
    }

}