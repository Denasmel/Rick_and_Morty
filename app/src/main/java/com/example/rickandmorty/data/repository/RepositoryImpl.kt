package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.cloud.RickAndMortyApi

class RepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi
) : Repository {

    override suspend fun getListOfCharacters(page: Int): List<com.example.rickandmorty.data.model.Character> {
        return try {
            val data = rickAndMortyApi.getRickAndMortyServerData(page = page)
            data.characters
        } catch (e: Exception) {
            emptyList()
        }
    }
}