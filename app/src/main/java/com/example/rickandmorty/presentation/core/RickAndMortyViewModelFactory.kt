package com.example.rickandmorty.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.cloud.RetrofitInstance
import com.example.rickandmorty.data.repository.RepositoryImpl
import com.example.rickandmorty.presentation.main.MainViewModel

class RickAndMortyViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(RepositoryImpl(RetrofitInstance.retrofit)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}