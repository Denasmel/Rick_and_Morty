package com.example.rickandmorty.data.repository

interface Repository {

    suspend fun getListOfCharacters(page: Int): List<com.example.rickandmorty.data.model.Character>

}